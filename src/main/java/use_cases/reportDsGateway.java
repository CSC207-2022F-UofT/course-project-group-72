package use_cases;

public interface reportDsGateway {

    void save(ReportDsRequestModel reportdsRequestModel);

    boolean existsReportByReporterAndReview(String reporter_username, String review_id);
}
