package use_cases;

import entities.Report;
import entities.ReportFactory;
import screens.ReportCreationFailure;
import screens.ReportPresenter;

import java.time.LocalDateTime;

public class ReportInteractor implements reportInputBoundary {

    final reportDsGateway reportDsGateway;

    final ReportPresenter reportPresenter;

    final ReportFactory reportFactory;


    public ReportInteractor(reportDsGateway reportDsGateway, ReportPresenter reportPresenter, ReportFactory reportFactory) {
        this.reportDsGateway = reportDsGateway;
        this.reportPresenter = reportPresenter;
        this.reportFactory = reportFactory;
    }

    @Override
    public void Create(ReportRequestModel reportRequestModel) {

        //if report already exists
        if(true){
            throw new ReportCreationFailure("Report failed: You've already reported this review");

            // check if the reporter is banned
        } else if (false) {
            throw new ReportCreationFailure("Report failed: Your account is under investigation due to massive reports");
        }

        Report report = reportFactory.create(reportRequestModel.getReason(), reportRequestModel.getReview(), reportRequestModel.getReporter().getId());
        LocalDateTime now = LocalDateTime.now();
        ReportDsRequestModel reportDsRequestModel =new ReportDsRequestModel(report.getReason(), report.getReviewContent(), report.getReview_id(), report.getReporter_id(), now.toString());

        reportDsGateway.save(reportDsRequestModel);


    }
}
