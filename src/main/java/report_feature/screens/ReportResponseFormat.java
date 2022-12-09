package report_feature.screens;

public class ReportResponseFormat implements ReportPresenter {

    /**
     *
     * @param report response model
     * @return a responsemodel
     */
    @Override
    public ReportResponseModel prepareSuccessView(ReportResponseModel report) {
        return report;
    }

    /**
     *
     * @param error String
     * @return
     *
     *
     * This method is used to raise an exception with given error message
     *
     * Later on the ReportScreen will catch the exception
     */
    @Override
    public ReportResponseModel prepareFailView(String error) throws RuntimeException{
        throw new ReportCreationFailure(error);

    }

}


