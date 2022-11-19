package user_use_cases;

import entities.User;

public class LoginUserResponseModel {
    User user;

    public LoginUserResponseModel(User user) {
        this.user = user;
    }


}
