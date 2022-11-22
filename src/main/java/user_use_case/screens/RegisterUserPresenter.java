package user_use_case.screens;

import user_use_case.RegisterUserResponseModel;

public interface RegisterUserPresenter {
    RegisterUserResponseModel prepareRegisterSuccessView();

    RegisterUserResponseModel prepareRegisterFailView(String error);
}
