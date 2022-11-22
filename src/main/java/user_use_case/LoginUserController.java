package user_use_case;

public class LoginUserController {

    private final LoginUserInputBoundary loginuserinputboundary;

    public LoginUserController(LoginUserInputBoundary loginuserInputBoundary){
        this.loginuserinputboundary = loginuserInputBoundary;
    }

    public LoginUserResponseModel loginUser(LoginUserRequestModel requestModel) {
        return this.loginuserinputboundary.loginUser(requestModel);
    }

}
