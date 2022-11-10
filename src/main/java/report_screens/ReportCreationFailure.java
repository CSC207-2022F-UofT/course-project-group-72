package report_screens;

public class ReportCreationFailure extends RuntimeException {
    public ReportCreationFailure(String error) {
        super(error);
    }
}
