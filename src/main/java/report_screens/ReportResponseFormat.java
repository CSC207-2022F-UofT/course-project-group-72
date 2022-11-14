package report_screens;

import report_use_cases.ReportPresenter;
import report_use_cases.ReportResponseModel;

public class ReportResponseFormat implements ReportPresenter {

    @Override
    public ReportResponseModel prepareSuccessView(ReportResponseModel report) {
        return report;
    }

    @Override
    public ReportResponseModel prepareFailView(String error) {
        throw new ReportCreationFailure(error);

    }

}
