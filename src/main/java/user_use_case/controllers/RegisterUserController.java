package user_use_case.controllers;

import user_use_case.interactors.RegisterUserInputBoundary;
import user_use_case.interactors.RegisterUserRequestModel;
import user_use_case.screens.RegisterUserResponseModel;

public class RegisterUserController {

    private final RegisterUserInputBoundary registeruserinputboundary;

    public RegisterUserController(RegisterUserInputBoundary registerInputBoundary){
        this.registeruserinputboundary = registerInputBoundary;
    }

    public RegisterUserResponseModel CreateUserObject(RegisterUserRequestModel requestModel) {
        return this.registeruserinputboundary.CreateUser(requestModel);
    }
}
