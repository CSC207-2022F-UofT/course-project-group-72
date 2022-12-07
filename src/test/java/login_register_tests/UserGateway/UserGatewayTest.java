package login_register_tests.UserGateway;

import org.junit.jupiter.api.Test;

import entities.User;
import user_use_case.gateways.UserGateway;

import java.util.Random;


class UserGatewayTest {

    @Test
    void test_get_add_user() {
        // Make sure username does not exist in database

        UserGateway gateway = new UserGateway();

        int random_user_num = new Random().nextInt(100) + 100;

        String test_username = "testusername" + random_user_num;

        User test_user = new User(test_username, "testpassword52");

        gateway.addUser(test_user.getUsername(), test_user.getPassword());

        String actual_password = test_user.getPassword();

        String result_password = gateway.getUser(test_username).getPassword();

        assert actual_password.equals(result_password);
    }


    @Test
    void test_update_user() {
        // Tests Pass, If Fail, Confirm UserDatabase Values are stored correctly
        int random_user_num1 = new Random().nextInt(100) + 200;

        UserGateway gateway1 = new UserGateway();

        String test_username1 = "testusername" + random_user_num1;

        User test_user1 = new User(test_username1, "testpassword101");

        gateway1.addUser(test_user1.getUsername(), test_user1.getPassword());

        test_user1.setBanned();

        gateway1.updateUser(test_user1);

        Boolean actual_status = test_user1.isBanned();

        Boolean result_status = gateway1.getUser(test_username1).isBanned();

        assert actual_status.equals(result_status);
    }

}
