package report_use_case.interfaces;

import report_use_case.screens.ReportResponseModel;
import report_use_case.interactors.ReportRequestModel;

import java.io.IOException;

public interface reportInputBoundary {

    ReportResponseModel create(ReportRequestModel reportRequestModel) throws IOException;
}
