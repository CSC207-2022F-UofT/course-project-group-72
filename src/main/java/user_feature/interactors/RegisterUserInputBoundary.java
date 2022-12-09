package user_feature.interactors;

import user_feature.screens.RegisterUserResponseModel;

public interface RegisterUserInputBoundary {

    public RegisterUserResponseModel CreateUser(RegisterUserRequestModel requestModel);
}
