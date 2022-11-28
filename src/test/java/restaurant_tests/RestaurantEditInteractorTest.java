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

public class RestaurantEditInteractorTest {

    final RestaurantDSGateway restaurantGateway = new FileRestaurant("src/test/java/restaurant_tests/temptest.csv");
    final RestaurantFactory factory = new RestaurantFactory();
    final UserGatewayInterface userGateway = new UserGateway();

    public RestaurantEditInteractorTest() throws IOException {
    }

    @Test
    void simulation() {
        // Temporary test presenter
        RestaurantPresenter presenter = new RestaurantPresenter() {
            @Override
            public RestaurantResponseModel prepareSuccessView(RestaurantResponseModel responseModel) {
                Restaurant editedRestaurant = responseModel.getRestaurant();
                assertEquals("newRestaurant", editedRestaurant.getName());
                assertEquals("fast food", editedRestaurant.getCuisineType());
                assertEquals("123456", editedRestaurant.getLocation());
                assertTrue(restaurantGateway.existsByLocation("123456"));
                restaurantGateway.deleteRestaurant("123456");
                return null;
            }

            @Override
            public RestaurantResponseModel prepareFailView(String error) {
                // Shouldn't reach fail
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
