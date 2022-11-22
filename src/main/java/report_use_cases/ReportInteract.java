package report_use_cases;

import ReviewInterfaces.ReviewGatewayInterface;
import ReviewGateways.ReviewGateway;
import entities.Report;
import entities.ReportFactory;
import entities.Review;
import entities.User;
import user_use_cases.UserGateway;
import user_use_cases.UserGatewayInterface;

import javax.imageio.IIOException;
import java.time.LocalDateTime;

public class ReportInteract implements reportInputBoundary {

    private static final ReviewGatewayInterface gateway = new ReviewGateway();

    final reportDsGateway reportDsGateway;

    final ReportFactory reportFactory;

    final Excalibur excalibur;

    final ReportPresenter presenter;


    /**
     *
     * @param reportDsGateway
     * @param reportFactory
     * @param excalibur
     * @param presenter
     *
     * initialize report interactor
     */
    public ReportInteract(reportDsGateway reportDsGateway, ReportFactory reportFactory,
                          Excalibur excalibur, ReportPresenter presenter) {
        this.reportDsGateway = reportDsGateway;
        this.excalibur = excalibur;
        this.reportFactory = reportFactory;
        this.presenter = presenter;
    }

    @Override
    public ReportResponseModel create(ReportRequestModel reportRequestModel) throws IIOException {

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

        //initialize the user object
        String targeted_username = reportRequestModel.getReview().getUser();
        UserGatewayInterface userGateway = new UserGateway();
        User targeted_user = userGateway.getUser(targeted_username);
        //raise report
        targeted_user.addReport();

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

        userGateway.updateUser(updated_user);

        ReportResponseModel reportResponseModel = new ReportResponseModel(report.getReporter_username(),
                report.getReview_id(), now.toString());

        //report is created and saved successfully at this point
        return presenter.prepareSuccessView(reportResponseModel);


    }
}
