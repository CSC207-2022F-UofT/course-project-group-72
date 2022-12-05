package restaurant_tests.screens;

import org.junit.jupiter.api.Test;
import restaurant_use_case.interactors.FileRestaurant;
import restaurant_use_case.screens.DeleteRestaurantView;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.junit.Assert.fail;

public class DeleteViewTest {
    DeleteRestaurantView deleteView = new DeleteRestaurantView(
            null,
            null,
            new FileRestaurant("src/test/java/restaurant_tests/temptest.csv"),
            null,
            null
            );

    public DeleteViewTest() throws IOException {
    }

    @Test
    public void actionPerformedTest() {
        ActionEvent event = new ActionEvent("Confirm", 0, "Confirm");

        try {
            deleteView.actionPerformed(event);
        } catch (Exception ignored) {
            fail();
        }
    }
}
