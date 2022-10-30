package use_cases;

public class ReportDsRequestModel {

    String reason;
    String content;
    String review_id;
    String reporter_id;
    String creation_time;

    public ReportDsRequestModel(String reason, String content, String review_id, String reporter_id, String creation_time) {
        this.reason = reason;
        this.content = content;
        this.review_id = review_id;
        this.reporter_id = reporter_id;
        this.creation_time = creation_time;
    }

    public String getReason() {
        return this.reason;
    }

    public String getContent() {
        return this.content;
    }

    public String getReview_id() {
        return this.review_id;
    }

    public String getReporter_id() {
        return this.reporter_id;
    }

    public String getCreation_time() {
        return this.creation_time;
    }

}
