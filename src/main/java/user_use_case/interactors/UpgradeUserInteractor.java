package user_use_case.interactors;

import entities.OwnerFactory;
import entities.OwnerUser;
import user_use_case.gateways.UserGateway;

import java.util.ArrayList;

public class UpgradeUserInteractor implements UpgradeUserInputBoundary{
    private final OwnerFactory factory;

    private final UserGateway gateway;

    public UpgradeUserInteractor(OwnerFactory factory, UserGateway gateway) {
        this.factory = factory;
        this.gateway = gateway;
    }

    /*
    Method to change a User to an OwnerUser while keeping existing data.
     */
    @Override
    public UpgradeUserResponseModel UpgradeUser(UpgradeUserRequestModel requestModel) {
//        Get required variables from existing user.
        String username = requestModel.getUsername();
        String password = requestModel.getPassword();
        ArrayList<String> reviews = requestModel.getPastReviews();
        ArrayList<String> liked_reviews = requestModel.getLikedReviews();
        int received_reports = requestModel.getReceivedReports();
        boolean banned = requestModel.isBanned();
        ArrayList<String> ownedRestaurants = new ArrayList<>();

//        Create new OwnerUser and return it.

        OwnerUser ownerUser = this.factory.reintialize(username, password, reviews, liked_reviews, received_reports, banned, true, ownedRestaurants);

        this.gateway.updateUser(ownerUser);

        return new UpgradeUserResponseModel(ownerUser, true);
    }
}
