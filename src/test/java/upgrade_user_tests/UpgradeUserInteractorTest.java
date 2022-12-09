package upgrade_user_tests;

import entities.OwnerFactory;
import org.junit.jupiter.api.Test;

import entities.User;
import user_feature.gateways.UserGateway;
import user_feature.interactors.UpgradeUserInteractor;
import user_feature.interactors.UpgradeUserRequestModel;
import user_feature.interactors.UpgradeUserResponseModel;

class UpgradeUserInteractorTest {

    @Test
    void test_upgrade_user() {
        // Test the upgrade user functionality

        User user = new User("Username", "Password");

//        Create necessary factory and gateway

        OwnerFactory factory = new OwnerFactory();

        UserGateway gateway = new UserGateway();

        UpgradeUserInteractor upgradeUserInteractor = new UpgradeUserInteractor(factory, gateway);

        UpgradeUserRequestModel requestModel = new UpgradeUserRequestModel(user);

        UpgradeUserResponseModel responseModel = upgradeUserInteractor.UpgradeUser(requestModel);

//        Check that username and password of new user are as expected and that status is correct.
        assert responseModel.getOwnerUser().getPassword().equals("Password");
        assert responseModel.getOwnerUser().getUsername().equals("Username");
        assert responseModel.getStatus();

    }

}
