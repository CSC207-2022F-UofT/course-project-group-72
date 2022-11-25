package report_use_case.screens;


import entities.Review;
import report_use_case.interactors.ReportDsRequestModel;
import report_use_case.gateways.reportDsGateway;
import review_use_case.gateways.ReviewNotFoundException;

import java.io.*;
import java.util.*;

public class FileReportHistory implements reportDsGateway {

    private final String csvFileName;

    private static final int REVIEW_ID_INDEX = 0;
    private static final int REPORTER_USERNAME_INDEX = 1;
    private static final int REASON_INDEX = 2;
    private static final int CONTENT_INDEX = 3;
    private static final int CREATION_TIME_INDEX = 4;

    String DELIMITER = ",";

    /**
     *
     * @param csvName
     * @throws IOException
     */
    public FileReportHistory(String csvName) throws IOException {
        this.csvFileName = csvName;
    }


    @Override // save report to file
    public void save(ReportDsRequestModel reportdsRequestModel) throws IOException {
        File file = new File(this.csvFileName);
        FileWriter writer = new FileWriter(file, true);
        String review_id = reportdsRequestModel.getReview_id();
        String reporter_username = reportdsRequestModel.getReporter_username();
        String reason = reportdsRequestModel.getReason();
        String content = reportdsRequestModel.getContent();
        String creation_time = reportdsRequestModel.getCreation_time();
        String line = String.join(DELIMITER, review_id, reporter_username, reason,
                content, creation_time);
        writer.append(line);
        writer.append("\n");
        writer.close();
    }

    public ArrayList<String> getCorrespondingReporter(String review_id) throws FileNotFoundException {
        File file = new File(this.csvFileName);
        Scanner scanner = new Scanner(file);
        String line;

        ArrayList<String> reports = new ArrayList<>();
        while(scanner.hasNext()){
            //scanner over
            line = scanner.nextLine();
            String[] pieces = line.split(DELIMITER);

            //return corresponding username index
            if(pieces[REVIEW_ID_INDEX].equals(review_id)){
                reports.add(pieces[REPORTER_USERNAME_INDEX]);
            }

        }

        return reports;
    }

    // check if a report exists
    @Override
    public boolean existsReportByReporterAndReview(String reporter_username, String review_id)
            throws FileNotFoundException {

            ArrayList<String> reportTocheck = this.getCorrespondingReporter(review_id);

            if (reportTocheck.contains(reporter_username)){
                return true;
            } else {
                return false;
            }

    }

}

