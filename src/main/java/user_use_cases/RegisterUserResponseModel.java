package user_use_cases;

import entities.User;
import user_screens.RegisterUserResponseFormatter;

public class RegisterUserResponseModel {

    User user;
    private String error;

    public RegisterUserResponseModel(User user) {
        this.user = user;
        this.error = null;
    }

    public RegisterUserResponseModel(String error) {
        this.user = null;
        this.error = error;
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public String getError() {
        return this.error;
    }
}
