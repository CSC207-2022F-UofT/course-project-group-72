package restaurant_tests.screens;

import global.ViewInterface;
import org.junit.jupiter.api.Test;
import restaurant_use_case.screens.RestaurantInteractionFailed;
import restaurant_use_case.screens.RestaurantResponseFormatter;
import restaurant_use_case.screens.RestaurantResponseModel;
import static org.junit.jupiter.api.Assertions.*;


public class RestaurantPresenterTest {
    RestaurantResponseFormatter formatter;

    RestaurantPresenterTest() {
        ViewInterface temp = new ViewInterface() {
            @Override
            public void refresh() {
                // Do nothing
            }

            @Override
            public void back() {
                // Do nothing
            }
        };
        formatter = new RestaurantResponseFormatter(temp);
    }

    @Test
    public void testPrepareSuccessView () {
        RestaurantResponseModel inputResponse
                = new RestaurantResponseModel("TestRestaurant", "nottimed", "Tested");
        RestaurantResponseModel responseModel
                = formatter.prepareSuccessView(inputResponse);

        assertEquals("Successfully Tested TestRestaurant", responseModel.getOperation());
        assertEquals("TestRestaurant", responseModel.getRestaurant());
    }

    @Test
    public void testFailSuccessView () {
        String error = "Successfully Failed";

        assertThrows(RestaurantInteractionFailed.class, () -> formatter.prepareFailView(error));
    }
}
