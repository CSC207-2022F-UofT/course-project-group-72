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

/**
 * Tests the RestaurantDeleteInteractor class for the correct response and action performed
 */
public class RestaurantDeleteInteractorTest {
    /**
     * The Restaurant gateway managing the temporary testing database
     */
    final RestaurantDSGateway restaurantGateway = new FileRestaurant("src/test/java/restaurant_tests/temptest.csv");
    /**
     * The Restaurant factory
     */
    final RestaurantFactory factory = new RestaurantFactory();
    /**
     * The User gateway
     */
    final UserGatewayInterface userGateway = new UserGateway();

    public RestaurantDeleteInteractorTest() throws IOException {
    }

    @Test
    void simulation() {
        // Temporary test presenter
        RestaurantDeletePresenter presenter = new RestaurantDeletePresenter() {
            @Override
            public RestaurantResponseModel prepareSuccessView(String message, String restaurant, String deletionTime) {
                // Check that the correct restaurant was operated on by the interactor
                assertEquals("newRestaurant", restaurant);
                // Checks that the restaurant has been successfully deleted
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
        // Simulate a controller creating a request model
        RestaurantDeleteRequestModel inputData = new RestaurantDeleteRequestModel(
                userFactory.CreateUserObject("0000","1234"),
                newRestaurant
        );

        // Simulate an interaction
        interactor.delete(inputData);

    }
}
