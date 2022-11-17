package report_use_cases;

import Interfaces.ReviewGatewayInterface;
import entities.Report;
import entities.ReportFactory;
import entities.Review;
import entities.User;
import java.time.LocalDateTime;

public class ReportInteract implements reportInputBoundary {

    private static final ReviewGatewayInterface gateway = new Gateways.ReviewGateway();

    final reportDsGateway reportDsGateway;

    final ReportFactory reportFactory;

    final Excalibur excalibur;

    final ReportPresenter presenter;



    public ReportInteract(reportDsGateway reportDsGateway, ReportFactory reportFactory,
                          Excalibur excalibur, ReportPresenter presenter) {
        this.reportDsGateway = reportDsGateway;
        this.excalibur = excalibur;
        this.reportFactory = reportFactory;
        this.presenter = presenter;
    }

    @Override
    public ReportResponseModel create(ReportRequestModel reportRequestModel){

        //if report already exists
        if(reportDsGateway.existsReportByReporterAndReview(reportRequestModel.getReporter().getUsername(),
                reportRequestModel.getReview().getID())){
            return presenter.prepareFailView("Report already exists.");

            // check if the reporter is banned
        } else if (reportRequestModel.getReporter().isBanned()) {
            return presenter.prepareFailView("You are banned.");
        }

        Report report = reportFactory.create(reportRequestModel.getReason(), reportRequestModel.getReview(),
                reportRequestModel.getReporter().getUsername());
        LocalDateTime now = LocalDateTime.now();
        ReportDsRequestModel reportDsRequestModel =new ReportDsRequestModel(report.getReason(),
                report.getReviewContent(), report.getReview_id(), report.getReporter_username(), now.toString());
        reportDsGateway.save(reportDsRequestModel);

        //add report to targeted user
        //reportRequestModel.getReview().getUser() IT RETURNS AN USERNAME, USE THE USERNAME TO FIND AND INITIALIZE THE USER FROM THE DATA BASE
        //THEN .addReport()

        //add report to targeted review
        reportRequestModel.getReview().addReport();

        //save the changes to the targeted user and review
        Review updated_revivew = excalibur.execute_review();
        User updated_user = excalibur.execute_user();


        try{
            gateway.updateReview(updated_revivew);
        } catch(Exception e){
            System.out.println("Error updating review");
        }

        ReportResponseModel reportResponseModel = new ReportResponseModel(report.getReporter_username(),
                report.getReview_id(), now.toString());
        return presenter.prepareSuccessView(reportResponseModel);


    }
}
