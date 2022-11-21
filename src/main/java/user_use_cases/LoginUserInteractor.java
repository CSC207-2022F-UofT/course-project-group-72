package user_use_cases;

import entities.User;
import entities.UserFactory;

public class LoginUserInteractor {

    private final UserFactory factory;
    private final UserGateway gateway;
    private final LoginUserPresenter presenter;

    public LoginUserInteractor(UserFactory factory, UserGateway gateway, LoginUserPresenter presenter) {
        this.factory = factory;
        this.presenter = presenter;
        this.gateway = gateway;
    }

    public LoginUserResponseModel loginUser(LoginUserRequestModel requestModel) {
        if (!gateway.userExists(requestModel.getUsername())) {
            return presenter.loginUserDNEView();
        }

        if (!gateway.getPassword(requestModel.getUsername()).equals(requestModel.getPassword())) {
            return presenter.loginWrongPasswordView();
        }

        User logged_in_user = factory.CreateUserObject(requestModel.getUsername(), requestModel.getPassword());

        LoginUserResponseModel successLoginModel = new LoginUserResponseModel(logged_in_user);

        return presenter.loginSuccessView(successLoginModel);

    }
}
