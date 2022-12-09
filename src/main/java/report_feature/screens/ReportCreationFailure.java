package report_feature.screens;


// Class used to raise a RuntimeException with given specified "error message"
public class ReportCreationFailure extends RuntimeException {
    /**
     *
     * @param error
     * raise exception with given error message
     */
    public ReportCreationFailure(String error) {
        super(error);
    }
}
