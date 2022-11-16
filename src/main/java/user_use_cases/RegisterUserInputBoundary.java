package user_use_cases;

import user_use_cases.RegisterUserRequestModel;
import user_use_cases.RegisterUserResponseModel;

public interface RegisterUserInputBoundary {

    public RegisterUserResponseModel CreateUser(RegisterUserRequestModel requestModel);
}
