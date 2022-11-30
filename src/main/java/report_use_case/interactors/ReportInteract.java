package report_use_case.interactors;

import report_use_case.gateways.reportDsGateway;
import report_use_case.interfaces.BanTool;
import report_use_case.interfaces.reportInputBoundary;
import report_use_case.screens.ReportPresenter;
import report_use_case.screens.ReportResponseModel;
import review_use_case.interfaces.ReviewGatewayInterface;
import review_use_case.gateways.ReviewGateway;
import entities.Report;
import entities.ReportFactory;
import entities.Review;
import entities.User;
import user_use_case.gateways.UserGateway;
import user_use_case.interfaces.UserGatewayInterface;

import java.io.IOException;
import java.time.LocalDateTime;

public class ReportInteract implements reportInputBoundary {

    //Is it an issue in terms of Hard Dependency? as Interactor initialized concrete classes.
    private static final ReviewGatewayInterface gateway = new ReviewGateway();

    private static final UserGatewayInterface userGateway = new UserGateway();

    final report_use_case.gateways.reportDsGateway reportDsGateway;

    final ReportFactory reportFactory;

    final BanTool banTool;

    final ReportPresenter presenter;


    /**
     *
     * @param reportDsGateway: reportDsGateway
     * @param reportFactory: ReportFactory
     * @param banTool: Excalibur
     * @param presenter: ReportPresenter
     *
     * initialize report interactor
     */
    public ReportInteract(reportDsGateway reportDsGateway, ReportFactory reportFactory,
                          BanTool banTool, ReportPresenter presenter) {
        this.reportDsGateway = reportDsGateway;
        this.banTool = banTool;
        this.reportFactory = reportFactory;
        this.presenter = presenter;
    }

    @Override
    public ReportResponseModel create(ReportRequestModel reportRequestModel) throws IOException {

        //if report already exists
        if(reportDsGateway.existsReportByReporterAndReview(reportRequestModel.getReporter().getUsername(),
                reportRequestModel.getReview().getID())){
            return presenter.prepareFailView("Report already exists.");

            // check if the reporter is banned
        } else if (reportRequestModel.getReporter().isBanned()) {
            return presenter.prepareFailView("You are banned.");
        }

        //Is it an issue in terms of Hard Dependency? But since reportFactory is used to create a report, does it
        //count as a use of Dependency injection?
        Report report = reportFactory.create(reportRequestModel.getReason(), reportRequestModel.getReview(),
                reportRequestModel.getReporter().getUsername());

        LocalDateTime now = LocalDateTime.now();

        //It is a hard dependency, however I want to KEEP it here because
        // no other class should be able to change/create the ReportDsRequestModel
        ReportDsRequestModel reportDsRequestModel =new ReportDsRequestModel(report.getReason(),
                report.getReviewContent(), report.getReviewId(), report.getReporterUsername(), now.toString());

        reportDsGateway.save(reportDsRequestModel);

        //initialize the user object
        String targetedUsername = reportRequestModel.getReview().getUser();
        try{
        User targetedUser = userGateway.getUser(targetedUsername);
            targetedUser.addReport();}catch (Exception e){return presenter.prepareFailView("Database Error: Couldn't find reviewer");}

        //add report to targeted review
        reportRequestModel.getReview().addReport();

        //save the changes to the targeted user and review
        Review updatedRevivew = banTool.checkAndBanReview();
        User updatedUser = banTool.checkAndBanUser();


        try{
            gateway.updateReview(updatedRevivew);
        } catch(Exception e){
            return presenter.prepareFailView("Updating Error: Couldn't update review");
        }

        try{
            userGateway.updateUser(updatedUser);} catch(Exception e){return presenter.prepareFailView("Updating Error: Couldn't update reviewer");}

        ReportResponseModel reportResponseModel = new ReportResponseModel(report.getReporterUsername(),
                report.getReviewId(), now.toString());

        //report is created and saved successfully at this point
        return presenter.prepareSuccessView(reportResponseModel);


    }
}
