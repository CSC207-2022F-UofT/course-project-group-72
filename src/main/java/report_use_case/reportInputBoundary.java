package report_use_case;

import java.io.IOException;

public interface reportInputBoundary {

    ReportResponseModel create(ReportRequestModel reportRequestModel) throws IOException;
}
