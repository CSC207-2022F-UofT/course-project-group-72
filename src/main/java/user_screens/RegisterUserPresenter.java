package user_screens;

import user_use_cases.RegisterUserResponseModel;

public interface RegisterUserPresenter {
    RegisterUserResponseModel prepareRegisterSuccessView();

    RegisterUserResponseModel prepareRegisterFailView(String error);
}
