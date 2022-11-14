package restaurant_screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.RestaurantResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class EditRestaurantView extends JPanel implements ActionListener{
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
//    /**
//     * The previous frame
//     */
//    JFrame previousFrame;



    public EditRestaurantView(RestaurantController restaurantController, OwnerUser owner, Restaurant restaurant) {

        this.restaurantController = restaurantController;
        this.owner = owner;
        this.restaurant = restaurant;
//        this.previousFrame = previousFrame;

        JLabel title = new JLabel("Edit Restaurant");

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
        this.add(cuisineInfo);
        this.add(priceInfo);
        this.add(buttons);

        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());

        try {
            if (Objects.equals(e.getActionCommand(), "Confirm")) {
                RestaurantResponseModel result = restaurantController.create(
                        owner.getUsername(),
                        name.getText(),
                        restaurant.getLocation(),
                        cuisineType.getText(),
                        Integer.parseInt(priceBucket.getText()),
                        restaurant.getAvgStars(),
                        restaurant.getReviewIDs()
                        );

                JOptionPane.showMessageDialog(this, result.getOperation());
            }
            // TODO revert back to previous view
            Container parentPanel = this.getParent();
            parentPanel.remove(this);
            parentPanel.revalidate();
            parentPanel.repaint();

//
//            TimeUnit.SECONDS.sleep(5);
//            this.setVisible(false);
//            this.dispose();
//            this.previousFrame.setVisible(true);


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }

    }
}
