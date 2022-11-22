package report_use_case;

//Report Gateway interface
public interface reportDsGateway {

    void save(ReportDsRequestModel reportdsRequestModel);

    boolean existsReportByReporterAndReview(String reporter_username, String review_id);
}
