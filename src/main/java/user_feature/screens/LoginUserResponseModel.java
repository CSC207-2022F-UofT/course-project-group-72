package user_feature.screens;

import entities.User;

public class LoginUserResponseModel {

    Boolean success;
    User user;
    private String error;

    public LoginUserResponseModel(Boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public LoginUserResponseModel(Boolean success, User user) {
        this.success = success;
        this.user = user;
    }

    public Boolean wasSuccess() {
        return this.success;
    }

    public User getUser() {
        return this.user;
    }

    public LoginUserResponseModel(String error) {
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
