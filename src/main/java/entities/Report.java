package entities;

//Entity layer

//Report Class
public class Report {

    private final String reason;

    private final Review review;

    private final String reporter_username;

    Report(String reason, Review review, String reporter_id){
        this.reason = reason;
        this.review = review;
        this.reporter_username = reporter_id;
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

    public String getReporter_username() {
        return reporter_username;
    }

    //later modify get method based on the actual implementation of Review;
    public User getReportedUserName() {
        return review.getUserName();
    }

    //later modify get method based on the actual implementation of Review;
    public String getReviewContent () {
        return review.getText();
    }




}
