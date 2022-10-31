package screens;

import entities.Review;
import entities.User;
import use_cases.ReportInteractor;
import use_cases.ReportRequestModel;
import use_cases.reportInputBoundary;

public class ReportController {

    final reportInputBoundary input;

    public ReportController(reportInputBoundary input) {
        this.input = input;
    }

    public void create(String reason, Review review, User reporter) {

        ReportRequestModel reportRequestModel = new ReportRequestModel(reason, review, reporter);
        input.Create(reportRequestModel);
    }
}
