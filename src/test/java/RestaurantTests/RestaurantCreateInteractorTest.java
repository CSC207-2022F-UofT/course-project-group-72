package RestaurantTests;

import entities.OwnerFactory;
import entities.Restaurant;
import entities.RestaurantFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import restaurant_use_case.screens.RestaurantPresenter;
import restaurant_use_case.*;
import user_use_case.UserGateway;
import user_use_case.UserGatewayInterface;

import java.io.IOException;

public class RestaurantCreateInteractorTest {

    final RestaurantDSGateway restaurantGateway = new FileRestaurant("./test/java/RestaurantTests/temptest.csv");
    final RestaurantFactory factory = new RestaurantFactory();
    final UserGatewayInterface userGateway = new UserGateway();

    public RestaurantCreateInteractorTest() throws IOException {
    }

    @Test
    void simulation() {
        // Temporary test presenter
        RestaurantPresenter presenter = new RestaurantPresenter() {
            @Override
            public RestaurantResponseModel prepareSuccessView(RestaurantResponseModel responseModel) {
                Restaurant newRestaurant = responseModel.getRestaurant();
                assertEquals("newRestaurant", newRestaurant.getName());
                assertEquals("123456", newRestaurant.getLocation());
                assertTrue(restaurantGateway.existsByLocation("123456"));
                return null;
            }

            @Override
            public RestaurantResponseModel prepareFailView(String error) {
                fail();
                return null;
            }
        };
        // Setup the interactor
        RestaurantInputBoundary interactor = new createRestaurant(factory, restaurantGateway, userGateway, presenter);
        OwnerFactory userFactory = new OwnerFactory();
        // Setup the input data that would normally be done by the controller
        RestaurantRequestModel inputData = new RestaurantRequestModel(
                userFactory.CreateUserObject("0000","1234"),
                "newRestaurant",
                "123456",
                "bbq",
                3
        );
        interactor.create(inputData);

    }
}
