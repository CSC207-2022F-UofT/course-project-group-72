package user_feature.interactors;

import user_feature.screens.LoginUserResponseModel;

public interface LoginUserInputBoundary {

    public LoginUserResponseModel loginUser(LoginUserRequestModel requestModel);
}
