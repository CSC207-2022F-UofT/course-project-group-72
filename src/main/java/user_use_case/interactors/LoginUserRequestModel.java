package user_use_case.interactors;

public class LoginUserRequestModel {
    /**
     * Inputted Username from WelcomeUserView for User Login
     */
    private String username;
    /**
     * Inputted Password from WelcomeUserView for User Login
     */
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
