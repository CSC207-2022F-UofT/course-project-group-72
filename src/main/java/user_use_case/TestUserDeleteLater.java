package user_use_case;

import entities.UserFactory;
import user_use_case.screens.WelcomeUserView;

public class TestUserDeleteLater {

    UserFactory factory = new UserFactory();

    public static void main(String[] args) {
        /*
        UserGateway gateway = new UserGateway();

        User user1 = new UserFactory().CreateUserObject("Henry", "testpassword");
        gateway.addUser(user1.getUsername(), user1.getPassword());
        */
        // Test Welcome View
        WelcomeUserView weclomeview = new WelcomeUserView();

    }

}
