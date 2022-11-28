package restaurant_tests;

import entities.OwnerFactory;
import entities.Restaurant;
import entities.RestaurantFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import restaurant_use_case.gateways.RestaurantDSGateway;
import restaurant_use_case.interactors.*;
import restaurant_use_case.interfaces.RestaurantDeleteInputBoundary;
import restaurant_use_case.screens.RestaurantDeletePresenter;
import restaurant_use_case.screens.RestaurantResponseModel;
import user_use_case.gateways.UserGateway;
import user_use_case.interfaces.UserGatewayInterface;

import java.io.IOException;

public class RestaurantDeleteInteractorTest {

    final RestaurantDSGateway restaurantGateway = new FileRestaurant("src/test/java/restaurant_tests/temptest.csv");
    final RestaurantFactory factory = new RestaurantFactory();
    final UserGatewayInterface userGateway = new UserGateway();

    public RestaurantDeleteInteractorTest() throws IOException {
    }

    @Test
    void simulation() {
        // Temporary test presenter
        RestaurantDeletePresenter presenter = new RestaurantDeletePresenter() {
            @Override
            public RestaurantResponseModel prepareSuccessView(String message, String restaurant, String deletionTime) {
                assertEquals("newRestaurant", restaurant);
                assertFalse(restaurantGateway.existsByLocation("123456"));
                return null;
            }

            @Override
            public RestaurantResponseModel prepareFailView(String error) {
                fail(error);
                return null;
            }
        };
        // Setup the interactor
        RestaurantDeleteInputBoundary interactor = new DeleteRestaurantInteractor(restaurantGateway, userGateway, presenter);
        OwnerFactory userFactory = new OwnerFactory();
        // Setup the input data that would normally be done by the controller
        Restaurant newRestaurant = factory.create("0000",
                "newRestaurant",
                "123456",
                "bbq",
                3);
        restaurantGateway.save(newRestaurant);
        RestaurantDeleteRequestModel inputData = new RestaurantDeleteRequestModel(
                userFactory.CreateUserObject("0000","1234"),
                newRestaurant
        );


        interactor.delete(inputData);

    }
}
