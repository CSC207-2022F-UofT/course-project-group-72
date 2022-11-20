package entities;

//Entity layer

//ReportFactory later used in ReportInteractor to create Report object

/**
 * returns a new Report object with given info
 */
public class ReportFactory {
    public Report create(String reason, Review review, String reporter_id) {
        return new Report(reason, review, reporter_id);
    }
}
