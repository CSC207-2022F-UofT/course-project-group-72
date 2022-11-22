package user_use_case;
public class RegisterUserController {

    private final RegisterUserInputBoundary registeruserinputboundary;

    public RegisterUserController(RegisterUserInputBoundary registerInputBoundary){
        this.registeruserinputboundary = registerInputBoundary;
    }

    public RegisterUserResponseModel CreateUserObject(RegisterUserRequestModel requestModel) {
        return this.registeruserinputboundary.CreateUser(requestModel);
    }
}
