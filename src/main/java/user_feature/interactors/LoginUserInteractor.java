package user_feature.interactors;

import entities.User;
import entities.UserFactory;
import user_feature.gateways.UserGateway;
import user_feature.screens.LoginUserResponseModel;

public class LoginUserInteractor implements LoginUserInputBoundary {

    private final UserFactory factory;
    private final UserGateway gateway;

    public LoginUserInteractor(UserFactory factory, UserGateway gateway) {
        this.factory = factory;
        this.gateway = gateway;
    }

    /**
     *
     * @param requestModel LoginUserRequestModel containing inputted info from WelcomeUserView
     * @return LoginUserRequestModel
     */
    @Override
    public LoginUserResponseModel loginUser(LoginUserRequestModel requestModel) {

        if (!gateway.userExists(requestModel.getUsername())) {
            return new LoginUserResponseModel(false, "Username Not Found");
        }

        if (!gateway.getPassword(requestModel.getUsername()).equals(requestModel.getPassword())) {
            return new LoginUserResponseModel(false, "Incorrect Password");
        }

        //User currUser = gateway.getUser(requestModel.getPassword());
        User currUser = gateway.getUser(requestModel.getUsername());
        // User currUser = factory.CreateUserObject(
        //        requestModel.getUsername(),
        //        requestModel.getPassword()
        //);

        //gateway.addUser(newUser.getUsername(), newUser.getPassword());

        //User current_user = gateway.getUser(requestModel.getUsername());

        LoginUserResponseModel successLoginResponse =
                new LoginUserResponseModel(true, currUser);

        return successLoginResponse;

    }
}
