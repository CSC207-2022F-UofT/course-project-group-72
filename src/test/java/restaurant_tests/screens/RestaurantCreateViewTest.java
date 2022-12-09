package restaurant_tests.screens;

import org.junit.jupiter.api.Test;
import restaurant_use_case.interactors.FileRestaurant;
import restaurant_use_case.screens.CreateRestaurantView;
import user_use_case.gateways.UserGateway;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.junit.Assert.fail;

public class RestaurantCreateViewTest {
    CreateRestaurantView createView = new CreateRestaurantView(new FileRestaurant("src/test/java/restaurant_tests/temptest.csv"),
            new UserGateway(),
            null,
            null);

    public RestaurantCreateViewTest() throws IOException {
    }

    //@Test
    public void actionPerformedTest() {
        ActionEvent event = new ActionEvent("Confirm", 0, "Confirm");

        try {
            createView.actionPerformed(event);
        } catch (Exception ignored) {
            fail();
        }
    }
}
