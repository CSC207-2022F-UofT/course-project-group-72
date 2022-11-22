package report_use_case.gateways;

import report_use_case.interactors.ReportDsRequestModel;

//Report Gateway interface
public interface reportDsGateway {

    void save(ReportDsRequestModel reportdsRequestModel);

    boolean existsReportByReporterAndReview(String reporter_username, String review_id);
}
