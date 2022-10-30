package entities;

//Entity layer

//Report Class
public class Report {

    private final String reason;

    private final Review review;

    private final String reporter_id;

    Report(String reason, Review review, String reporter_id){
        this.reason = reason;
        this.review = review;
        this.reporter_id = reporter_id;
    }

    public String getReason() {
        return reason;
    }

    public Review getReview() {
        return review;
    }

    public String getReview_id(){
        return review.getReview_id();
    }

    public String getReporter_id() {
        return reporter_id;
    }

    //later modify get method based on the actual implementation of Review;
    public User getReportedUser() {
        return review.getReviewer();
    }

    //later modify get method based on the actual implementation of Review;
    public String getReviewContent () {
        return review.getText();
    }




}
