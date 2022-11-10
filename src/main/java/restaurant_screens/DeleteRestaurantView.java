package restaurant_screens;

import entities.OwnerUser;
import entities.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeleteRestaurantView extends JPanel implements ActionListener {
    /**
     * The controller
     */
    RestaurantDeleteController restaurantController;
    /**
     * The current restaurant
     */
    Restaurant restaurant;
    //NOTE: the owner must own the restaurant to access this view.

    DeleteRestaurantView(RestaurantDeleteController restaurantDeleteController, Restaurant restaurant) {

        this.restaurantController = restaurantDeleteController;
        this.restaurant = restaurant;

        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(confirm);
        buttons.add(cancel);

        confirm.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);

        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (Objects.equals(e.getActionCommand(), "Confirm")) {
                restaurantController.delete(restaurant.getLocation());
            }
            //TODO revert to previous view
            TimeUnit.SECONDS.sleep(5);
            this.setVisible(false);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
