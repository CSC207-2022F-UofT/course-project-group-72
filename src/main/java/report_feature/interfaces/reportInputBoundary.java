package report_feature.interfaces;

import report_feature.interactors.ReportRequestModel;
import report_feature.screens.ReportResponseModel;

import java.io.IOException;

public interface reportInputBoundary {

    ReportResponseModel create(ReportRequestModel reportRequestModel) throws IOException;
}
