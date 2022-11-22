package profile_screens;

import ReviewScreens.RestaurantView;
import entities.Restaurant;
import entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRestaurantActionListener implements ActionListener {

    private final User user;

    private final Restaurant restaurant;
    public ViewRestaurantActionListener(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        RestaurantView restaurantView = new RestaurantView(this.user, this.restaurant);
    }
}
