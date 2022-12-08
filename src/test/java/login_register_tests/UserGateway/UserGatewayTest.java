package login_register_tests.UserGateway;

import entities.OwnerFactory;
import entities.OwnerFactory;
import entities.OwnerUser;
import org.junit.jupiter.api.Test;

import entities.User;
import user_use_case.gateways.UserGateway;

import java.util.ArrayList;
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

        gateway.removeUser(test_username);

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

        gateway1.removeUser(test_username1);

        assert actual_status.equals(result_status);
    }

    @Test
    void test_update_owner() {
        // Tests Pass, If Fail, Confirm UserDatabase Values are stored correctly
        int random_user_num2 = new Random().nextInt(100) + 300;

        OwnerFactory own_fact = new OwnerFactory();

        UserGateway gateway2 = new UserGateway();

        String test_own_username = "testusername" + random_user_num2;

        ArrayList<String> past_reviews = new ArrayList<>();

        ArrayList<String> liked_reviews = new ArrayList<>();

        ArrayList<String> owned_restaurants = new ArrayList<>();

        owned_restaurants.add("M2C 2P3");

        owned_restaurants.add("LLL 111");

        OwnerUser test_own_user = own_fact.CreateUserObject(test_own_username, "testpassword106");

        gateway2.addUser(test_own_user.getUsername(), test_own_user.getPassword());

        test_own_user = own_fact.reintialize(test_own_username, "testpassword106", past_reviews,
                liked_reviews, 0, false, true, owned_restaurants);


        gateway2.updateUser(test_own_user);

        String actual_pass = test_own_user.getPassword();

        User result_user = gateway2.getUser(test_own_username);

        gateway2.removeUser(test_own_username);

        assert actual_pass.equals(result_user.getPassword());

    }

}
