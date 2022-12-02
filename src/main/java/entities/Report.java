package entities;

//Entity layer

/**
 * reason: reason input from reporter
 * review: Review object that is chosen to be reported by reporter
 * reporter_username: Reporter's username
 */
public class Report {

    private final String reason;

    private final Review review;

    private final String reporterUsername;

    Report(String reason, Review review, String reporter_id){
        this.reason = reason;
        this.review = review;
        this.reporterUsername = reporter_id;
    }

    public String getReason() {
        return reason;
    }

    public Review getReview() {
        return review;
    }

    public String getReviewId(){
       return review.getID();
    }

    public String getReporterUsername() {
        return reporterUsername;
    }


    public String getReportedUser() {
        return review.getUser();
    }


    public String getReviewContent () {
        return review.getText();
    }




}
