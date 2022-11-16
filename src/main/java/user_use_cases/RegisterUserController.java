package user_use_cases;
public class RegisterUserController {

    private final RegisterUserInputBoundary registeruserinputboundary;

    public RegisterUserController(RegisterUserInputBoundary registerInputBoundary){
        this.registeruserinputboundary = registerInputBoundary;
    }

    public RegisterUserResponseModel CreateUser(RegisterUserRequestModel requestModel) {
        return this.registeruserinputboundary.CreateUser(requestModel);
    }
}
