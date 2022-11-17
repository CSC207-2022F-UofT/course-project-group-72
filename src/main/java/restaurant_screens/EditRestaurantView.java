package restaurant_screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.RestaurantEditRequestModel;
import restaurant_use_case.RestaurantResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class EditRestaurantView extends JFrame implements ActionListener{
    /**
     * The restaurant name
     */
    JTextField name;
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

    /**
     *
     * @param restaurantController the RestaurantController corresponding to the edit use case
     * @param owner the current active user that must be an OwnerUser
     * @param restaurant the current Restaurant
     */
    public EditRestaurantView(RestaurantController restaurantController, OwnerUser owner, Restaurant restaurant) {

        this.restaurantController = restaurantController;
        this.owner = owner;
        this.restaurant = restaurant;

        // Title Label Creation
        JLabel title = new JLabel("Edit Restaurant");

        // Text Field Creation
        JPanel nameInfo = new JPanel();
        nameInfo.add(new JLabel("Restaurant Name"));
        name = new JTextField(restaurant.getName());
        nameInfo.add(name);
        // Choice of implementations
        cuisineType = new JTextField(restaurant.getCuisineType());
        LabelTextPanel cuisineInfo = new LabelTextPanel(
                new JLabel("Restaurant Cuisine"), cuisineType);

        priceBucket = new JTextField(restaurant.getPriceBucket());
        LabelTextPanel priceInfo = new LabelTextPanel(
                new JLabel("Price Range"), priceBucket);

        // Button Creation
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(confirm);
        buttons.add(cancel);

        confirm.addActionListener(this);
        cancel.addActionListener(this);

        // Content panel creation
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(nameInfo);
        main.add(cuisineInfo);
        main.add(priceInfo);
        main.add(buttons);

        // Set Content Pane to Main
        this.setContentPane(main);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        this.pack();

        // Set Visible
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());

        try {
            if (Objects.equals(e.getActionCommand(), "Confirm")) {
                RestaurantResponseModel result = restaurantController.edit(
                        owner.getUsername(),
                        name.getText(),
                        restaurant.getLocation(),
                        cuisineType.getText(),
                        Integer.parseInt(priceBucket.getText()),
                        this.restaurant);

                JOptionPane.showMessageDialog(this, result.getOperation());
            }

            // If confirm then dispose after execution, if cancel then dispose immediately
            // Else display message and maintain
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }

    }
}
