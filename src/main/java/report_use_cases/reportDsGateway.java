package report_use_cases;

//Report Gateway interface
public interface reportDsGateway {

    void save(ReportDsRequestModel reportdsRequestModel);

    boolean existsReportByReporterAndReview(String reporter_username, String review_id);
}
