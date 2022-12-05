package report_use_case.interactors;

import report_use_case.gateways.reportDsGateway;
import report_use_case.interfaces.BanningAlgorithm;
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

    private static final ReviewGatewayInterface gateway = new ReviewGateway();

    private static final UserGatewayInterface userGateway = new UserGateway();

    final report_use_case.gateways.reportDsGateway reportDsGateway;

    final ReportFactory reportFactory;

    final BanningAlgorithm banningAlgorithm;

    final ReportPresenter presenter;


    /**
     *
     * @param reportDsGateway: reportDsGateway
     * @param reportFactory: ReportFactory
     * @param banningAlgorithm: Banning Strategy
     * @param presenter: ReportPresenter
     *
     * initialize report interactor
     */
    public ReportInteract(reportDsGateway reportDsGateway, ReportFactory reportFactory,
                          BanningAlgorithm banningAlgorithm, ReportPresenter presenter) {
        this.reportDsGateway = reportDsGateway;
        this.banningAlgorithm = banningAlgorithm;
        this.reportFactory = reportFactory;
        this.presenter = presenter;
    }

    @Override
    public ReportResponseModel create(ReportRequestModel reportRequestModel) throws IOException {

        //if report already exists
        if(reportDsGateway.existsReportByReporterAndReview(reportRequestModel.getReporter().getUsername(),
                reportRequestModel.getReview().getID())){
            return presenter.prepareFailView("Failed: Report already exists.");

            // check if the reporter is banned
        } else if (reportRequestModel.getReporter().isBanned()) {
            return presenter.prepareFailView("Failed: You are banned.");
        }

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

        //if the user object isn't found in database, return error message (not usually happen)
        try{
        User targetedUser = userGateway.getUser(targetedUsername);
            targetedUser.addReport();}catch (Exception e){return presenter.prepareFailView("Database Error: Couldn't find reviewer");}

        //add report to targeted review
        reportRequestModel.getReview().addReport();

        //Ban review and user using BanningAlgorithm, banning strategy is specified in the input BanningAlgorithm object
        Review updatedRevivew = banningAlgorithm.checkAndBanReview();
        User updatedUser = banningAlgorithm.checkAndBanUser();

        //update review and user in database
        try{
            gateway.updateReview(updatedRevivew);
        } catch(Exception e){
            return presenter.prepareFailView("Updating Error: Couldn't update review");
        }

        try{
            userGateway.updateUser(updatedUser);}
        catch(Exception e){return presenter.prepareFailView("Updating Error: Couldn't update reviewer");}

        //create response model
        ReportResponseModel reportResponseModel = new ReportResponseModel(report.getReporterUsername(),
                report.getReviewId(), now.toString());

        //report is created and saved successfully at this point
        return presenter.prepareSuccessView(reportResponseModel);


    }
}
