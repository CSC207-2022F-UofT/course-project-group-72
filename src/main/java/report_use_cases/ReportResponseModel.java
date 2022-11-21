package report_use_cases;

public class ReportResponseModel {

    String reporter_name;
    String review_id;
    String creation_time;


    /**
     *
     * @param reporter_name
     * @param review_id
     * @param creation_time
     */
    public ReportResponseModel(String reporter_name, String review_id, String creation_time){
        this.reporter_name = reporter_name;
        this.review_id = review_id;
        this.creation_time = creation_time;
    }


    public String getReporter_name() {
        return this.reporter_name;
    }

    public String getReview_id() {
        return this.review_id;
    }

    public String getCreation_time() {
        return this.creation_time;
    }


}
