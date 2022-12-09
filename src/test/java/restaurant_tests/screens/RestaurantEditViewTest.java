package restaurant_tests.screens;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import restaurant_use_case.interactors.FileRestaurant;
import restaurant_use_case.screens.EditRestaurantView;
import entities.RestaurantFactory;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.junit.Assert.fail;

public class RestaurantEditViewTest {

    RestaurantFactory factory = new RestaurantFactory();

    EditRestaurantView editView = new EditRestaurantView(
            null,
            factory.create("0000", "temp", "A1B 2C3", "bbq", 1),
            null,
            new FileRestaurant("src/test/java/restaurant_tests/temptest.csv")
    );

    public RestaurantEditViewTest() throws IOException {
    }

    //@Test
    public void actionPerformedTest() {
        ActionEvent event = new ActionEvent("Confirm", 0, "Confirm");

        try {
            editView.actionPerformed(event);
        } catch (Exception ignored) {
            Assertions.fail();
        }
    }
}
