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

        RestaurantFactory factory = new RestaurantFactory();
        restaurant.save(factory.create("123", "tim", "123 temp ave", "bbq", 3));



    }
}
