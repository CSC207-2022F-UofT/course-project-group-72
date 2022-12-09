package user_use_case.interactors;

import entities.User;
import entities.UserFactory;
import user_use_case.gateways.UserGateway;
import user_use_case.screens.RegisterUserResponseModel;

public class RegisterUserInteractor implements RegisterUserInputBoundary {

    private final UserFactory factory;
    private final UserGateway gateway;

    public RegisterUserInteractor(UserFactory factory, UserGateway gateway) {
        this.factory = factory;
        this.gateway = gateway;
    }

    /**
     * Checks if Inputted Registration Inputs are valid
     * Username and Password must be non-empty Strings
     * Username must be unused in database
     * Passwords Must Match
     *
     * @param requestModel RegisterUserRequestModel containing inputted info from WelcomeUserView
     * @return RegisterUserResponseModel containing success or error.
     */
    @Override

    public RegisterUserResponseModel CreateUser(RegisterUserRequestModel requestModel) {

        if (gateway.userExists(requestModel.getUsername())) {
            return new RegisterUserResponseModel(false, "Username Taken");
        }

        if (!(requestModel.getPassword1().equals(requestModel.getPassword2()))) {
            return new RegisterUserResponseModel(false, "Passwords don't Match");
        }

        if (requestModel.getUsername().equals("")) {
            return new RegisterUserResponseModel(false, "Invalid Username");
        }

        if (requestModel.getPassword1().equals("")) {
            return new RegisterUserResponseModel(false, "Invalid Password");
        }

        User newUser = factory.CreateUserObject(
                requestModel.getUsername(),
                requestModel.getPassword1()
        );

        gateway.addUser(newUser.getUsername(), newUser.getPassword());

        RegisterUserResponseModel successRegisterResponse =
                new RegisterUserResponseModel(true, newUser);
        return successRegisterResponse;

    }

}
