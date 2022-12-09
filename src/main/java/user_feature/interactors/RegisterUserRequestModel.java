package user_feature.interactors;

public class RegisterUserRequestModel {
    /**
     * Inputted Username from WelcomeUserView
     */
    private String username;
    /**
     * Inputted First Password from WelcomeUserView
     */
    private String password1;
    /**
     * Inputted 'confirm' password from WelcomeUserView
     */
    private String password2;

    public RegisterUserRequestModel( String username, String password1, String password2) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
    }

    public String getUsername() { return this.username; }
    public String getPassword1() { return this.password1; }
    public String getPassword2() { return this.password2; }
}
