package user_use_case.controllers;

import user_use_case.interactors.LoginUserInputBoundary;
import user_use_case.interactors.LoginUserRequestModel;
import user_use_case.screens.LoginUserResponseModel;

public class LoginUserController {

    private final LoginUserInputBoundary loginuserinputboundary;

    public LoginUserController(LoginUserInputBoundary loginuserInputBoundary){
        this.loginuserinputboundary = loginuserInputBoundary;
    }

    public LoginUserResponseModel loginUser(LoginUserRequestModel requestModel) {
        return this.loginuserinputboundary.loginUser(requestModel);
    }

}
