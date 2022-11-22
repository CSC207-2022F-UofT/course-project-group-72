package user_use_cases;

import entities.User;
import entities.UserFactory;
import user_screens.RegisterUserPresenter;

public class RegisterUserInteractor implements RegisterUserInputBoundary{

    private final UserFactory factory;
    private final UserGateway gateway;

    public RegisterUserInteractor(UserFactory factory, UserGateway gateway) {
        this.factory = factory;
        this.gateway = gateway;
    }
    @Override

    public RegisterUserResponseModel CreateUser(RegisterUserRequestModel requestModel) {

        if (gateway.userExists(requestModel.getUsername())) {
            return new RegisterUserResponseModel(false, "Username Taken");
        }

        if (!(requestModel.getPassword1().equals(requestModel.getPassword2()))) {
            return new RegisterUserResponseModel(false, "Passwords don't Match");
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
