package report_feature.interactors;

import report_feature.gateways.reportDsGateway;
import report_feature.interfaces.BanningAlgorithm;
import report_feature.interfaces.reportInputBoundary;
import report_feature.screens.ReportPresenter;
import report_feature.screens.ReportResponseModel;
import review_feature.interfaces.ReviewGatewayInterface;
import review_feature.gateways.ReviewGateway;
import entities.Report;
import entities.ReportFactory;
import entities.Review;
import entities.User;
import user_feature.gateways.UserGateway;
import user_feature.interfaces.UserGatewayInterface;

import java.io.IOException;
import java.time.LocalDateTime;

public class ReportInteract implements reportInputBoundary {

    private static final ReviewGatewayInterface gateway = new ReviewGateway();

    private static final UserGatewayInterface userGateway = new UserGateway();

    private final report_feature.gateways.reportDsGateway reportDsGateway;

    private final ReportFactory reportFactory;

    private final BanningAlgorithm banningAlgorithm;

    private final ReportPresenter presenter;


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
