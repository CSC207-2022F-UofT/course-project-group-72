package restaurant_tests;

import entities.OwnerFactory;
import entities.Restaurant;
import entities.RestaurantFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import restaurant_use_case.gateways.RestaurantDSGateway;
import restaurant_use_case.interactors.*;
import restaurant_use_case.interfaces.RestaurantInputBoundary;
import restaurant_use_case.screens.RestaurantPresenter;
import restaurant_use_case.screens.RestaurantResponseModel;
import user_use_case.gateways.UserGateway;
import user_use_case.interfaces.UserGatewayInterface;

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
                Restaurant editedRestaurant = responseModel.getRestaurant();
                // Check that the old values have been changed to the new values
                assertEquals("newRestaurant", editedRestaurant.getName());
                assertEquals("fast food", editedRestaurant.getCuisineType());
                // Check that the old values that have not been changed have been retained
                assertEquals(3, editedRestaurant.getPriceBucket());
                // Check that the unique location identifier has not been changed and the restaurant remains in database
                assertEquals("123456", editedRestaurant.getLocation());
                assertTrue(restaurantGateway.existsByLocation("123456"));
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
