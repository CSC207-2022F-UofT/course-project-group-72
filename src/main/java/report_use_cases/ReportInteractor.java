package report_use_cases;

import entities.Report;
import entities.ReportFactory;
import report_screens.ReportCreationFailure;

import java.time.LocalDateTime;

public class ReportInteractor implements reportInputBoundary {

    final reportDsGateway reportDsGateway;

    final ReportFactory reportFactory;

    final Excalibur excalibur;


    public ReportInteractor(reportDsGateway reportDsGateway, ReportFactory reportFactory, Excalibur excalibur) {
        this.reportDsGateway = reportDsGateway;
        this.excalibur = excalibur;
        this.reportFactory = reportFactory;
    }

    @Override
    public void Create(ReportRequestModel reportRequestModel) {

        //if report already exists
        if(reportDsGateway.existsReportByReporterAndReview(reportRequestModel.getReporter().getUsername(), reportRequestModel.getReview().getID())){
            throw new ReportCreationFailure("Report failed: You've already reported this review");

            // check if the reporter is banned
        } else if (reportRequestModel.getReporter().isBanned()) {
            throw new ReportCreationFailure("Report failed: Your account is under investigation due to massive reports");
        }

        Report report = reportFactory.create(reportRequestModel.getReason(), reportRequestModel.getReview(), reportRequestModel.getReporter().getUsername());
        LocalDateTime now = LocalDateTime.now();
        ReportDsRequestModel reportDsRequestModel =new ReportDsRequestModel(report.getReason(), report.getReviewContent(), report.getReview_id(), report.getReporter_username(), now.toString());
        reportDsGateway.save(reportDsRequestModel);

        //add report to targeted user
        //reportRequestModel.getReview().getUser() IT RETURNS AN USERNAME, USE THE USERNAME TO FIND AND INITIALIZE THE USER FROM THE DATA BASE
        //THEN .addReport()

        //add report to targeted review
        reportRequestModel.getReview().addReport();

        //check if the targeted review and user should be banned; if so ban them
        excalibur.execute();

        //save the changes to the targeted user and review


    }
}
