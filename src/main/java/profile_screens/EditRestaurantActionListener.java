package profile_screens;

import entities.OwnerUser;
import entities.Restaurant;
import entities.User;
import restaurant_screens.EditRestaurantView;
import restaurant_screens.IFrame;
import restaurant_use_case.RestaurantDSGateway;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditRestaurantActionListener implements ActionListener {

    private final IFrame parent;

    private final OwnerUser owner;

    private final Restaurant restaurant;

    private final RestaurantDSGateway restaurantDSGateway;

    public EditRestaurantActionListener(IFrame parent, OwnerUser owner, Restaurant restaurant, RestaurantDSGateway restaurantDSGateway) {
        this.parent = parent;
        this.owner = owner;
        this.restaurant = restaurant;
        this.restaurantDSGateway = restaurantDSGateway;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditRestaurantView editRestaurantView = new EditRestaurantView(this.owner, this.restaurant, this.parent, this.restaurantDSGateway);
    }
}
