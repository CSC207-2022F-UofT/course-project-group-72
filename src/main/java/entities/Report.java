package entities;

//Entity layer

//Report Class
public class Report {

    private final String reason;

    private final Review review;

<<<<<<< HEAD
    private final String reporter_username;
=======
    private final String reporter_id;
>>>>>>> main

    Report(String reason, Review review, String reporter_id){
        this.reason = reason;
        this.review = review;
<<<<<<< HEAD
        this.reporter_username = reporter_id;
=======
        this.reporter_id = reporter_id;
>>>>>>> main
    }

    public String getReason() {
        return reason;
    }

    public Review getReview() {
        return review;
    }

<<<<<<< HEAD
    public String getReview_id(){
       return review.getReview_id();
    }

    public String getReporter_username() {
        return reporter_username;
    }

    //later modify get method based on the actual implementation of Review;
    public User getReportedUserName() {
        return review.getUserName();
=======
    //public String getReview_id(){
       // return review.getReview_id();
    //}

    public String getReporter_id() {
        return reporter_id;
    }

    //later modify get method based on the actual implementation of Review;
    public User getReportedUser() {
        return review.getUser();
>>>>>>> main
    }

    //later modify get method based on the actual implementation of Review;
    public String getReviewContent () {
        return review.getText();
    }




}
