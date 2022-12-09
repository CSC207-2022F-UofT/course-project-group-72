package login_register_tests.UserGateway;

import entities.UserFactory;
import org.junit.jupiter.api.Test;
import user_feature.gateways.UserGateway;
import user_feature.interactors.*;
import user_feature.screens.LoginUserResponseModel;
import user_feature.screens.RegisterUserResponseModel;

import java.util.Random;

public class RegisterLogInTests {
    // Register Log In Test
     @Test
    void test_log_interactor() {
         LoginUserRequestModel model = new LoginUserRequestModel("TimsOwner", "123");

         String database = "src/test/java/login_register_tests/UserGateway/TestUserDatabase.csv";

         String temp_database = "src/test/java/login_register_tests/UserGateway/TempTestUserDatabase.csv";

         UserGateway gateway = new UserGateway(database, temp_database);

         UserFactory factory = new UserFactory();

         LoginUserInteractor interactor = new LoginUserInteractor(factory, gateway);

         LoginUserResponseModel result = interactor.loginUser(model);

         assert result.wasSuccess();
     }

    @Test
    void test_register_interactor() {
        int random_user_num = new Random().nextInt(100) + 500;

        String username = "TestUser" + random_user_num;

        RegisterUserRequestModel model = new RegisterUserRequestModel(username, "123", "123");

        String database = "src/test/java/login_register_tests/UserGateway/TestUserDatabase.csv";

        String temp_database = "src/test/java/login_register_tests/UserGateway/TempTestUserDatabase.csv";

        UserGateway gateway = new UserGateway(database, temp_database);

        UserFactory factory = new UserFactory();

        RegisterUserInteractor interactor = new RegisterUserInteractor(factory, gateway);

        RegisterUserResponseModel result = interactor.CreateUser(model);

        gateway.removeUser(username);

        assert result.wasSuccess();
    }
}
