package user_use_cases;

import entities.User;
import user_screens.RegisterUserResponseFormatter;

public class RegisterUserResponseModel {

    Boolean success;
    User user;
    private String error;

    public RegisterUserResponseModel(Boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public RegisterUserResponseModel(Boolean success, User user) {
        this.success = success;
        this.user = user;
    }

    public Boolean wasSuccess() {
        return this.success;
    }

    public User getUser() {
        return this.user;
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
