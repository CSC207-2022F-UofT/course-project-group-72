package user_screens;

import user_screens.LoginUserPresenter;
import user_use_cases.LoginUserResponseModel;

public class LoginUserResponseFormatter implements LoginUserPresenter {

    @Override
    public LoginUserResponseModel loginUserDNEView() {
        return null;
    }

    @Override
    public LoginUserResponseModel loginWrongPasswordView() {
        return null;
    }

    @Override
    public LoginUserResponseModel loginSuccessView(LoginUserResponseModel loginUserResponseModel) {
        return null;
    }



}
