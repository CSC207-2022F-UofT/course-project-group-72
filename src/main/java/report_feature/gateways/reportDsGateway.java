package report_feature.gateways;

import report_feature.interactors.ReportDsRequestModel;

import java.io.IOException;

//Report Gateway interface
public interface reportDsGateway {

    void save(ReportDsRequestModel reportdsRequestModel) throws IOException;

    boolean existsReportByReporterAndReview(String reporter_username, String review_id) throws IOException;
}
