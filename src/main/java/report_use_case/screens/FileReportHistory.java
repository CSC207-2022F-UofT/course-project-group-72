package report_use_case.screens;


import report_use_case.interactors.ReportDsRequestModel;
import report_use_case.gateways.reportDsGateway;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileReportHistory implements reportDsGateway {

    private final File csvFile;

    // Map of header name to column index
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    // Map of review_id to ReportDsRequestModel, USED to save to file (like a temporary cache)
    private final MultiMap<String, ReportDsRequestModel> saveReports = new MultiMap<>();

    // Map of review_id to reporter_username, USED to check if a report exists
    private final MultiMap<String, String> checkReports = new MultiMap<>();

    /**
     *
     * @param csvName
     * @throws IOException
     */
    public FileReportHistory(String csvName) throws IOException {
        csvFile = new File(csvName);
        headers.put("review_id", 0);
        headers.put("reporter_username", 1);
        headers.put("reason", 2);
        headers.put("content", 3);
        headers.put("creation_time", 4);

        if (csvFile.length() == 0) {
            save();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine();

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String review_id = col[headers.get("review_id")];
                String reporter_username = col[headers.get("reporter_username")];
                String reason = col[headers.get("reason")];
                String content = col[headers.get("content")];
                String creation_time = col[headers.get("creation_time")];

                checkReports.put(review_id, reporter_username);

                ReportDsRequestModel report = new ReportDsRequestModel(reason, content, review_id, reporter_username, creation_time);
                saveReports.put(review_id, report);

            }

            reader.close();
        }

    }


    @Override // save report to file (first put into hashmap, then save to file)
    public void save(ReportDsRequestModel reportdsRequestModel) {
        saveReports.put(reportdsRequestModel.getReviewId(), reportdsRequestModel);
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

            for (String review_id : saveReports.keySet()) {
                for (ReportDsRequestModel report : saveReports.get(review_id)) {
                    writer.write(String.join(",", review_id, report.getReporterUsername(), report.getReason(), report.getContent(), report.getCreationTime()));
                    writer.newLine();
                }
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // check if a report exists
    @Override
    public boolean existsReportByReporterAndReview(String reporter_username, String review_id) {
        for (String review_id_in_map : checkReports.keySet()) {
            if (review_id_in_map.equals(review_id)) {
                for (String reporter_username_in_map : checkReports.get(review_id_in_map)) {
                    if (reporter_username_in_map.equals(reporter_username)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
