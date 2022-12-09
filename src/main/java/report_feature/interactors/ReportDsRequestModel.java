package report_feature.interactors;

public class ReportDsRequestModel {

    private final String reason;
    private final String content;
    private final String reviewId;
    private final String reporterUsername;
    private final String creationTime;

    /**
     *
     * @param reason: String, reason the reporter entered
     * @param content: String, the content corresponding to the reivew
     * @param review_id: String
     * @param reporterUsername: String the reporter's Username
     * @param creationTime: Local time
     */

    public ReportDsRequestModel(String reason, String content, String review_id, String reporterUsername, String creationTime) {
        this.reason = reason;
        this.content = content;
        this.reviewId = review_id;
        this.reporterUsername = reporterUsername;
        this.creationTime = creationTime;
    }

    public String getReason() {
        return this.reason;
    }

    public String getContent() {
        return this.content;
    }

    public String getReviewId() {
        return this.reviewId;
    }

    public String getReporterUsername() {
        return this.reporterUsername;
    }

    public String getCreationTime() {
        return this.creationTime;
    }

}
