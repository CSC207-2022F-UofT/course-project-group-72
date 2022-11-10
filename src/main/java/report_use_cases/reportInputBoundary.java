package report_use_cases;

import java.io.IOException;

public interface reportInputBoundary {

    void Create(ReportRequestModel reportRequestModel) throws IOException;
}
