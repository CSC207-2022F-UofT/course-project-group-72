package report_use_case.screens;

public class ReportResponseModel {

    private final String reporterName;
    private final String reviewId;
    private final String creationTime;


    /**
     *
     * @param reporterName: String, reporter's username
     * @param reviewId: String, reviewId of the review being reported
     * @param creationTime: local time
     */
    public ReportResponseModel(String reporterName, String reviewId, String creationTime){
        this.reporterName = reporterName;
        this.reviewId = reviewId;
        this.creationTime = creationTime;
    }


    public String getReporterName() {
        return this.reporterName;
    }

    public String getReviewId() {
        return this.reviewId;
    }

    public String getCreationTime() {
        return this.creationTime;
    }


}
