package restaurant_screens;

import entities.OwnerUser;
import entities.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class EditRestaurantView extends JFrame implements ActionListener{
    /**
     * The restaurant name
     */
    JTextField name;
    /**
     * The restaurant location
     */
    JTextField location;
    /**
     * The restaurant cuisine type
     */
    JTextField cuisineType;
    /**
     * The restaurant price range
     */
    JTextField priceBucket;
    /**
     * The controller
     */
    RestaurantController restaurantController;
    /**
     * The current user (which must be an owner user)
     */
    OwnerUser owner;
    /**
     * The current restaurant
     */
    Restaurant restaurant;



    public EditRestaurantView(RestaurantController restaurantController, OwnerUser owner, Restaurant restaurant) {

        this.restaurantController = restaurantController;
        this.owner = owner;
        this.restaurant = restaurant;

        JLabel title = new JLabel("Edit Restaurant");

        JPanel nameInfo = new JPanel();
        nameInfo.add(new JLabel("Restaurant Name"));
        name = new JTextField(restaurant.getName());
        nameInfo.add(name);
        // Choice of implementations
        location = new JTextField(restaurant.getLocation());
        LabelTextPanel locationInfo = new LabelTextPanel(
                new JLabel("Restaurant Location"), location);

        cuisineType = new JTextField(restaurant.getCuisineType());
        LabelTextPanel cuisineInfo = new LabelTextPanel(
                new JLabel("Restaurant Cuisine"), cuisineType);

        priceBucket = new JTextField(restaurant.getPriceBucket());
        LabelTextPanel priceInfo = new LabelTextPanel(
                new JLabel("Price Range"), priceBucket);

        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(confirm);
        buttons.add(cancel);

        confirm.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(nameInfo);
        this.add(locationInfo);
        this.add(cuisineInfo);
        this.add(priceInfo);
        this.add(buttons);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());

        try {
            if (Objects.equals(e.getActionCommand(), "Confirm")) {
                restaurantController.create(
                        owner.getUsername(),
                        name.getText(),
                        location.getText(),
                        cuisineType.getText(),
                        Integer.parseInt(priceBucket.getText()),
                        restaurant.getAvgStars(),
                        restaurant.getReviewIDs()
                        );
            } else if (Objects.equals(e.getActionCommand(), "Cancel")) {
                // TODO revert back to previous view
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
