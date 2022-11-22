package report_use_case.interactors;

public class ReportDsRequestModel {

    String reason;
    String content;
    String review_id;
    String reporter_username;
    String creation_time;

    /**
     *
     * @param reason
     * @param content
     * @param review_id
     * @param reporter_username
     * @param creation_time
     */

    public ReportDsRequestModel(String reason, String content, String review_id, String reporter_username, String creation_time) {
        this.reason = reason;
        this.content = content;
        this.review_id = review_id;
        this.reporter_username = reporter_username;
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

    public String getReporter_username() {
        return this.reporter_username;
    }

    public String getCreation_time() {
        return this.creation_time;
    }

}
