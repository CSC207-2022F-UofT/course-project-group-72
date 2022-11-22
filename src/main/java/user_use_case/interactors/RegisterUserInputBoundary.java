package user_use_case.interactors;

import user_use_case.interactors.RegisterUserRequestModel;
import user_use_case.screens.RegisterUserResponseModel;

public interface RegisterUserInputBoundary {

    public RegisterUserResponseModel CreateUser(RegisterUserRequestModel requestModel);
}
