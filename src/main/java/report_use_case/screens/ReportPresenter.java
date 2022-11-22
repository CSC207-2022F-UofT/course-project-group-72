package report_use_case.screens;

public interface ReportPresenter {

    ReportResponseModel prepareSuccessView(ReportResponseModel report);

    ReportResponseModel prepareFailView(String error) throws RuntimeException;

}

