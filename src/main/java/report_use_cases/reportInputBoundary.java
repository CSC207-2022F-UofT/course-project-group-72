package report_use_cases;

import java.io.IOException;

public interface reportInputBoundary {

    ReportResponseModel create(ReportRequestModel reportRequestModel) throws IOException;
}
