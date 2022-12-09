package restaurant_tests.screens;

import restaurant_feature.gateways.FileRestaurant;
import restaurant_feature.screens.DeleteRestaurantView;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.junit.Assert.fail;

public class RestaurantDeleteViewTest {
    DeleteRestaurantView deleteView = new DeleteRestaurantView(
            null,
            null,
            new FileRestaurant("src/test/java/restaurant_tests/temptest.csv"),
            null,
            null
            );

    public RestaurantDeleteViewTest() throws IOException {
    }

    //@Test
    public void actionPerformedTest() {
        ActionEvent event = new ActionEvent("Confirm", 0, "Confirm");

        try {
            deleteView.actionPerformed(event);
        } catch (Exception ignored) {
            fail();
        }
    }
}
