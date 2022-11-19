package user_screens;

import user_use_cases.LoginUserResponseModel;

public interface LoginUserPresenter {
    LoginUserResponseModel loginUserDNEView();

    LoginUserResponseModel loginWrongPasswordView();

    LoginUserResponseModel loginSuccessView(LoginUserResponseModel loginUserResponseModel);
}
