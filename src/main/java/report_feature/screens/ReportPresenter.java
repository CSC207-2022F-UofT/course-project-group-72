package report_feature.screens;

public interface ReportPresenter {

    ReportResponseModel prepareSuccessView(ReportResponseModel report);

    ReportResponseModel prepareFailView(String error) throws RuntimeException;

}

