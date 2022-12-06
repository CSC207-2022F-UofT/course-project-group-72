package report_use_case.gateways;

import report_use_case.interactors.ReportDsRequestModel;

import java.io.FileNotFoundException;
import java.io.IOException;

//Report Gateway interface
public interface reportDsGateway {

    void save(ReportDsRequestModel reportdsRequestModel) throws IOException;

    boolean existsReportByReporterAndReview(String reporter_username, String review_id) throws IOException;
}
