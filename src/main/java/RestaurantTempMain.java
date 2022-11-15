import restaurant_screens.CreateRestaurantView;
import restaurant_screens.RestaurantController;
import restaurant_screens.RestaurantPresenter;
import restaurant_screens.RestaurantResponseFormatter;
import restaurant_use_case.*;
import entities.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RestaurantTempMain {
    public static void main(String[] args) {

        // Build the main window
        JFrame application = new JFrame("Test");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        RestaurantDSGateway restaurant;
        try {
            restaurant = new FileRestaurant("./temp.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }

        RestaurantPresenter presenter = new RestaurantResponseFormatter();
        RestaurantFactory factory = new RestaurantFactory();
        createRestaurant interactor = new createRestaurant(factory, restaurant, presenter);
        RestaurantController controller = new RestaurantController(interactor);

        CreateRestaurantView resView = new CreateRestaurantView(controller,
                new OwnerUser("testuser", "123"));
        screens.add(resView);
        cardLayout.show(screens, "test");
        application.pack();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);

    }
}
