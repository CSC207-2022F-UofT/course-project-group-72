package global;

import review_use_case.screens.RestaurantView;
import entities.Restaurant;
import entities.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRestaurantActionListener implements ActionListener {

    private final IFrame previousFrame;
    private final User user;
    private final Restaurant restaurant;
    
    public ViewRestaurantActionListener(IFrame previousFrame, User user, Restaurant restaurant) {
        this.previousFrame = previousFrame;
        this.user = user;
        this.restaurant = restaurant;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        RestaurantView restaurantView = new RestaurantView(this.previousFrame, this.user, this.restaurant);
    }
}
