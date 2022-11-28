package report_use_case.screens;

public class ReportResponseModel {

    String reporterName;
    String reviewId;
    String creationTime;


    /**
     *
     * @param reporterName
     * @param reviewId
     * @param creationTime
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
