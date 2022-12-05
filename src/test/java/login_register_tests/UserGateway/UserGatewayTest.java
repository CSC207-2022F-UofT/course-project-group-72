package login_register_tests.UserGateway;

import entities.UserFactory;
import org.junit.jupiter.api.Test;

import entities.Review;
import entities.User;
import user_use_case.gateways.UserGateway;
import user_use_case.interfaces.UserGatewayInterface;
import org.junit.jupiter.api.Test;
import user_use_case.screens.WelcomeUserView;


class UserGatewayTest {

    /*

        @Test
        void test_get_user() {
            // Make sure username does not exist in database & delete from database after test

            UserGateway gateway = new UserGateway();


            String test_username = "testusername59";

            User test_user = new User(test_username, "testpassword52");

            gateway.addUser(test_user.getUsername(), test_user.getPassword());

            assert test_user.getPassword().equals(gateway.getUser(test_username).getPassword());

        }*/

    public static void main(String[] args) {
        /*
        UserGateway gateway = new UserGateway();

        User user1 = new UserFactory().CreateUserObject("Henry", "testpassword");
        gateway.addUser(user1.getUsername(), user1.getPassword());
        */
        // Test Welcome View
        UserGateway gateway = new UserGateway();


        String test_username = "testusername384";

        User test_user = new User(test_username, "testpassword52");

        gateway.addUser(test_user.getUsername(), test_user.getPassword());

        test_user.setBanned();

        gateway.updateUser(test_user);

        System.out.print(gateway.getUser(test_username).isBanned());

    }

        //@Test
        //public void test_update_user() {
            // Make sure username does not exist in database & delete from database after test


        //}


}
