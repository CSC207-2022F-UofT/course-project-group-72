package restaurant_tests.screens;

import global.ViewInterface;
import org.junit.jupiter.api.Test;
import restaurant_feature.screens.RestaurantDeleteResponseFormatter;
import restaurant_feature.screens.RestaurantInteractionFailed;
import restaurant_feature.screens.RestaurantResponseModel;
import static org.junit.jupiter.api.Assertions.*;


public class RestaurantDeletePresenterTest {
    RestaurantDeleteResponseFormatter formatter;

    RestaurantDeletePresenterTest() {
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
        formatter = new RestaurantDeleteResponseFormatter(temp);
    }

    @Test
    public void testPrepareSuccessView () {
        RestaurantResponseModel responseModel
                = formatter.prepareSuccessView("Tested", "TestRestaurant", "untimed");

        assertEquals("Your restaurant: TestRestaurant was successfully Tested", responseModel.getOperation());
        assertEquals("TestRestaurant", responseModel.getRestaurant());
    }

    @Test
    public void testFailSuccessView () {
        String error = "Successfully Failed";

        assertThrows(RestaurantInteractionFailed.class, () -> formatter.prepareFailView(error));
    }
}
