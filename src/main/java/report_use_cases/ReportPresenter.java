package report_use_cases;

public interface ReportPresenter {

    ReportResponseModel prepareSuccessView(ReportResponseModel report);

    ReportResponseModel prepareFailView(String error);

}

