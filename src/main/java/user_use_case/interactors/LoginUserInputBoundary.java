package user_use_case.interactors;

import user_use_case.interactors.LoginUserRequestModel;
import user_use_case.screens.LoginUserResponseModel;

public interface LoginUserInputBoundary {

    public LoginUserResponseModel loginUser(LoginUserRequestModel requestModel);
}
