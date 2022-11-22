package global;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.screens.EditRestaurantView;
import restaurant_use_case.gateways.RestaurantDSGateway;

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
