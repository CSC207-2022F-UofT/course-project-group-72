package user_use_cases;

import entities.User;

public class LoginUserRequestModel {

    private String username;
    private String password;

    public LoginUserRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
