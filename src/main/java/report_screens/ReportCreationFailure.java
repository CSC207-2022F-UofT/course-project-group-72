package report_screens;


// Class used to raise a RuntimeException with given specified "error message"
public class ReportCreationFailure extends RuntimeException {
    public ReportCreationFailure(String error) {
        super(error);
    }
}
