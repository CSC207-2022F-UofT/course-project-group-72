package user_use_cases;

import entities.User;
import entities.UserFactory;
import user_screens.RegisterUserPresenter;

public class RegisterUserInteractor{

    private final UserFactory factory;
    private final UserDatabaseGateway gateway;
    private final RegisterUserPresenter presenter;

    public RegisterUserInteractor(UserFactory factory, UserDatabaseGateway gateway,
                                  RegisterUserPresenter presenter) {
        this.factory = factory;
        this.gateway = gateway;
        this.presenter = presenter;
    }

    public RegisterUserResponseModel CreateUser(RegisterUserRequestModel requestModel) {

        if (gateway.userExists(requestModel.getUsername())) {
            return presenter.prepareRegisterFailView("Username is Taken");
        }

        if (!requestModel.getPassword1().equals(requestModel.getPassword2())) {
            return presenter.prepareRegisterFailView("Passwords don't Match");
        }

        User newUser = factory.CreateUserObject(
                requestModel.getUsername(),
                requestModel.getPassword1()
        );

        gateway.addUser(newUser.getUsername(), newUser.getPassword());

        RegisterUserResponseModel successRegisterResponse =
                new RegisterUserResponseModel(newUser);
        return presenter.prepareRegisterSuccessView(successRegisterResponse);

    }

}
