package user_use_case.screens;

public interface RegisterUserPresenter {
    RegisterUserResponseModel prepareRegisterSuccessView();

    RegisterUserResponseModel prepareRegisterFailView(String error);
}
