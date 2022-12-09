package user_feature.controllers;

import user_feature.interactors.LoginUserInputBoundary;
import user_feature.interactors.LoginUserRequestModel;
import user_feature.screens.LoginUserResponseModel;

public class LoginUserController {

    private final LoginUserInputBoundary loginuserinputboundary;

    public LoginUserController(LoginUserInputBoundary loginuserInputBoundary){
        this.loginuserinputboundary = loginuserInputBoundary;
    }

    public LoginUserResponseModel loginUser(LoginUserRequestModel requestModel) {
        return this.loginuserinputboundary.loginUser(requestModel);
    }

}
