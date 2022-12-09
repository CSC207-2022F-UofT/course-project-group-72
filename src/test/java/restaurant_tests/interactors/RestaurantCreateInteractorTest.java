package restaurant_tests.interactors;

import entities.OwnerFactory;
import entities.RestaurantFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import restaurant_feature.interfaces.RestaurantDSGateway;
import restaurant_feature.gateways.FileRestaurant;
import restaurant_feature.interactors.RestaurantRequestModel;
import restaurant_feature.interactors.CreateRestaurantInteractor;
import restaurant_feature.interfaces.RestaurantInputBoundary;
import restaurant_feature.screens.RestaurantPresenter;
import restaurant_feature.screens.RestaurantResponseModel;
import user_feature.gateways.UserGateway;
import user_feature.interfaces.UserGatewayInterface;

import java.io.IOException;


/**
 * Tests the RestaurantCreateInteractor for the correct response and action performed
 */
public class RestaurantCreateInteractorTest {
    /**
     * The testing file that will be written and read from within the Restaurant tests
     */
    final RestaurantDSGateway restaurantGateway = new FileRestaurant("src/test/java/restaurant_tests/temptest.csv");
    /**
     * The Restaurant factory
     */
    final RestaurantFactory factory = new RestaurantFactory();
    /**
     * The User database gateway
     */
    final UserGatewayInterface userGateway = new UserGateway();

    public RestaurantCreateInteractorTest() throws IOException {
    }

    /**
     * Simulates a Restaurant Presenter that receives the information that the Interact creates
     */
    @Test
    void successfulSimulation() {
        // Temporary test presenter
        RestaurantPresenter presenter = new RestaurantPresenter() {
            @Override
            public RestaurantResponseModel prepareSuccessView(RestaurantResponseModel responseModel) {
                // Checks the Restaurant returned by the interactor
                String newRestaurant = responseModel.getRestaurant();
                assertEquals("newRestaurant", newRestaurant);
                // Check that the restaurant has been successfully stored in the database
                assertTrue(restaurantGateway.existsByLocation("A1B 2C3"));
                restaurantGateway.deleteRestaurant("A1B 2C3");
                // Checks the operation returned by the interactor
                assertEquals(responseModel.getOperation(), "created");
                // Checks mutability of operation
                String actual = "restaurant " + responseModel.getOperation() + " test successful";
                responseModel.setOperation(actual);
                assertEquals(responseModel.getOperation(), actual);
                return null;
            }

            @Override
            public RestaurantResponseModel prepareFailView(String error) {
                // Should not reach this branch
                fail(error);
                return null;
            }
        };
        // Setup the interactor
        RestaurantInputBoundary interactor = new CreateRestaurantInteractor(factory, restaurantGateway, userGateway, presenter);
        OwnerFactory userFactory = new OwnerFactory();
        // Setup the input data that would normally be done by the controller
        RestaurantRequestModel inputData = new RestaurantRequestModel(
                userFactory.CreateUserObject("0000","1234"),
                "newRestaurant",
                "A1B 2C3",
                "bbq",
                3
        );
        try {
            interactor.create(inputData);
        } catch(Exception ignored) {
            // Catches and ignores the error caused by trying to updated no existent
            //user database
        }
    }

    @Test
    void failingSimulation() {
        // Temporary test presenter
        RestaurantPresenter presenter = new RestaurantPresenter() {
            @Override
            public RestaurantResponseModel prepareSuccessView(RestaurantResponseModel responseModel) {
                // should not reach this branch
                fail();
                return null;
            }

            @Override
            public RestaurantResponseModel prepareFailView(String error) {
                // Should catch the incorrect location type
                assertEquals(error, "Please fill in your location as your postal code in the form: A1B 2C3");
                return null;
            }
        };
        // Setup the interactor
        RestaurantInputBoundary interactor = new CreateRestaurantInteractor(factory, restaurantGateway, userGateway, presenter);
        OwnerFactory userFactory = new OwnerFactory();
        // Setup the input data that would normally be done by the controller
        RestaurantRequestModel inputData = new RestaurantRequestModel(
                userFactory.CreateUserObject("0000","1234"),
                "newRestaurant",
                "123 fake st",
                "bbq",
                3
        );
        try {
            interactor.create(inputData);
        } catch(Exception ignored) {
            // Catches and ignores the error caused by trying to updated non existent
            //user database
        }
    }
}
