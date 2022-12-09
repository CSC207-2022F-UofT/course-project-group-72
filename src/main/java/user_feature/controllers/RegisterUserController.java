package user_feature.controllers;

import user_feature.interactors.RegisterUserInputBoundary;
import user_feature.interactors.RegisterUserRequestModel;
import user_feature.screens.RegisterUserResponseModel;

public class RegisterUserController {

    private final RegisterUserInputBoundary registeruserinputboundary;

    public RegisterUserController(RegisterUserInputBoundary registerInputBoundary){
        this.registeruserinputboundary = registerInputBoundary;
    }

    public RegisterUserResponseModel CreateUserObject(RegisterUserRequestModel requestModel) {
        return this.registeruserinputboundary.CreateUser(requestModel);
    }
}
