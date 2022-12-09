package restaurant_tests.interactors;

import entities.OwnerFactory;
import entities.Restaurant;
import entities.RestaurantFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import restaurant_feature.gateways.FileRestaurant;
import restaurant_feature.interfaces.RestaurantDSGateway;
import restaurant_feature.interactors.*;
import restaurant_feature.interfaces.RestaurantInputBoundary;
import restaurant_feature.screens.RestaurantPresenter;
import restaurant_feature.screens.RestaurantResponseModel;
import user_feature.gateways.UserGateway;
import user_feature.interfaces.UserGatewayInterface;

import java.io.IOException;

/**
 * Tests the RestaurantEditInteractor for correct response and action performed
 */
public class RestaurantEditInteractorTest {
    /**
     * The Restaurant gateway responsible for managing the temporary test database
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

    public RestaurantEditInteractorTest() throws IOException {
    }

    @Test
    void simulation() {
        // Temporary test presenter
        RestaurantPresenter presenter = new RestaurantPresenter() {
            @Override
            public RestaurantResponseModel prepareSuccessView(RestaurantResponseModel responseModel) {
                // Get the Restaurant edited by the interactor
                String editedRestaurant = responseModel.getRestaurant();
                // Check that the old values have been changed to the new values
                assertEquals("newRestaurant", editedRestaurant);
                // Check that the unique location identifier has not been changed and the restaurant remains in database
                assertTrue(restaurantGateway.existsByLocation("123456"));
                Restaurant restaurant = restaurantGateway.retrieveRestaurant("123456");
                assertEquals("123456", restaurant.getLocation());
                assertEquals("fast food", restaurant.getCuisineType());
                // Check that the old values that have not been changed have been retained
                assertEquals(3, restaurant.getPriceBucket());
                // Reset simulation database
                restaurantGateway.deleteRestaurant("123456");
                return null;
            }

            @Override
            public RestaurantResponseModel prepareFailView(String error) {
                // Shouldn't reach this branch
                fail(error);
                return null;
            }
        };
        // Setup the interactor
        RestaurantInputBoundary interactor = new EditRestaurantInteractor(restaurantGateway, presenter);
        OwnerFactory userFactory = new OwnerFactory();
        // The "old restaurant" we want to edit
        Restaurant oldRestaurant = factory.create("0000",
                "oldRestaurant",
                "123456",
                "bbq",
                3);
        // Setup the input data that would normally be done by the controller
        RestaurantEditRequestModel inputData = new RestaurantEditRequestModel(
                userFactory.CreateUserObject("0000","1234"),
                // The new information, changed Restaurant Name, cuisine type
                "newRestaurant",
                "123456",
                "fast food",
                3,
                oldRestaurant
        );
        // Make the Restaurant exist
        restaurantGateway.save(oldRestaurant);
        // Edit
        interactor.create(inputData);

    }
}
