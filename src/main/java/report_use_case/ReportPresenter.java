package report_use_case;

public interface ReportPresenter {

    ReportResponseModel prepareSuccessView(ReportResponseModel report);

    ReportResponseModel prepareFailView(String error) throws RuntimeException;

}

