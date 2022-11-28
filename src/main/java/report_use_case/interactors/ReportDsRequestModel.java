package report_use_case.interactors;

public class ReportDsRequestModel {

    String reason;
    String content;
    String reviewId;
    String reporterUsername;
    String creationTime;

    /**
     *
     * @param reason
     * @param content
     * @param review_id
     * @param reporterUsername
     * @param creationTime
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
