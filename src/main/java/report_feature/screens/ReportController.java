package report_feature.screens;

import entities.Review;
import entities.User;
import report_feature.interactors.ReportRequestModel;
import report_feature.interfaces.reportInputBoundary;

public class ReportController {

    private final reportInputBoundary input;

    public ReportController(reportInputBoundary input) {
        this.input = input;
    }

    /**
     *
     * @param reason: String, reason the user passed in
     * @param review: Review, the review being reported
     * @param reporter: User, the reporter
     * @return ReportResponseModel
     * @throws Exception: the Exception being raised if any
     */
    public ReportResponseModel create(String reason, Review review, User reporter) throws Exception {

        // create a ReportRequestModel object
        ReportRequestModel reportRequestModel = new ReportRequestModel(reason, review, reporter);
        // pass reportrequestmodel to input.create method which returns a reportreponsemodel
        return input.create(reportRequestModel);
    }
}
