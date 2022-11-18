package report_screens;


import report_use_cases.ReportDsRequestModel;
import report_use_cases.reportDsGateway;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FileReportHistory implements reportDsGateway {

    private final File csvFile;

    // Map of header name to column index
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    // Map of review_id to ReportDsRequestModel, USED to save to file (like a temporary cache)
    private final Map<String, ReportDsRequestModel> save_reports = new HashMap<>();

    // Map of review_id to reporter_username, USED to check if a report exists
    private final Map<String, String> check_reports = new HashMap<>();

    public FileReportHistory(String csvName) throws IOException {
        csvFile = new File(csvName);
        headers.put("review_id", 0);
        headers.put("reporter_username", 1);
        headers.put("reason", 3);
        headers.put("content", 4);
        headers.put("creation_time", 5);

        if (csvFile.length() == 0) {
            save();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine();

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String review_id = String.valueOf(col[headers.get("review_id")]);
                String reporter_username = String.valueOf(col[headers.get("reporter_username")]);
                String reason = String.valueOf(col[headers.get("reason")]);
                String content = String.valueOf(col[headers.get("content")]);
                String creation_time = String.valueOf(col[headers.get("creation_time")]);
                check_reports.put(review_id, reporter_username);
                ReportDsRequestModel report = new ReportDsRequestModel(reason, content, review_id, reporter_username, creation_time);
                save_reports.put(review_id, report);

            }

            reader.close();
        }

    }


    @Override // save report to file (first put into hashmap, then save to file)
    public void save(ReportDsRequestModel reportdsRequestModel) {
            save_reports.put(reportdsRequestModel.getReview_id(), reportdsRequestModel);
            save();
    }


    //default save method of FileReportHistory, put all reports in hashmap into file,
    // in the format of ""review_id","reporter_username","reason","content","creation_time"
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (ReportDsRequestModel report : save_reports.values()) {
                String review_id = report.getReview_id();
                String reporter_username = report.getReporter_username();
                String reason = report.getReason();
                String content = report.getContent();
                String creation_time = report.getCreation_time();

                writer.write(review_id);
                writer.write(",");
                writer.write(reporter_username);
                writer.write(",");
                writer.write(reason);
                writer.write(",");
                writer.write(content);
                writer.write(",");
                writer.write(creation_time);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // check if a report exists
    @Override
    public boolean existsReportByReporterAndReview(String reporter_username, String review_id) {
        String corresponding_reporter = check_reports.get(review_id);
        return Objects.equals(corresponding_reporter, reporter_username);
    }

    // general getMethod
    public Map<String, ReportDsRequestModel> getSave_reports() {
        return save_reports;
    }

    // general getMethod
    public Map<String, String> getCheck_reports() {
        return check_reports;
    }
}

