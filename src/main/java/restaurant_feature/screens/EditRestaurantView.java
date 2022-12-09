package restaurant_feature.screens;

import entities.OwnerUser;
import entities.Restaurant;
import global.IFrame;
import global.LabelTextPanel;
import restaurant_feature.interfaces.RestaurantDSGateway;
import restaurant_feature.interactors.EditRestaurantInteractor;
import restaurant_feature.interfaces.RestaurantInputBoundary;

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
     * The restaurant cuisine type
     */
    JTextField cuisineType;
    /**
     * The restaurant price range
     */
    JTextField priceBucket;
    /**
     * The Restaurant gateway that manages the Restaurant Database
     */
    RestaurantDSGateway restaurantGateway;
    /**
     * The current user (which must be an owner user)
     */
    OwnerUser owner;
    /**
     * The current restaurant
     */
    Restaurant restaurant;
    /**
     * The previous frame
     */
    IFrame previousFrame;

    /**
     *
     * @param owner the current active user that must be an OwnerUser
     * @param restaurant the current Restaurant
     * @param previousFrame the frame that initialized this one
     */
    public EditRestaurantView (OwnerUser owner, Restaurant restaurant, IFrame previousFrame,
                               RestaurantDSGateway restaurantGateway) {
        this.previousFrame = previousFrame;
        this.owner = owner;
        this.restaurant = restaurant;
        this.restaurantGateway = restaurantGateway;

        // Title Label Creation
        JLabel title = new JLabel("Edit Restaurant");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Text Field Creation
        JPanel nameInfo = new JPanel();
        nameInfo.add(new JLabel("Restaurant Name"));
        name = new JTextField(restaurant.getName(), 15);
        nameInfo.add(name);
        // Choice of implementations
        cuisineType = new JTextField(restaurant.getCuisineType(), 15);
        LabelTextPanel cuisineInfo = new LabelTextPanel(
                new JLabel("Restaurant Cuisine"), cuisineType);

        priceBucket = new JTextField(String.valueOf(restaurant.getPriceBucket()));
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
                // Initialize the controller, presenter, and use case interactor
                RestaurantPresenter presenter = new RestaurantResponseFormatter(previousFrame);
                RestaurantInputBoundary interactor = new EditRestaurantInteractor(restaurantGateway, presenter);
                RestaurantEditController restaurantController = new RestaurantEditController(interactor);
                // Interact
                RestaurantResponseModel result = restaurantController.edit(
                        owner,
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
