package user_use_cases;

import entities.User;

public class RegisterUserResponseModel {

    User user;

    public RegisterUserResponseModel(User user) {
        this.user = user;
    }

    public String getUsername() {
        return this.user.getUsername();
    }
}
