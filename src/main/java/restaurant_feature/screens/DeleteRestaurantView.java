package restaurant_feature.screens;

import entities.OwnerUser;
import entities.Restaurant;
import global.IFrame;
import restaurant_feature.interfaces.RestaurantDSGateway;
import restaurant_feature.interactors.DeleteRestaurantInteractor;
import restaurant_feature.interfaces.RestaurantDeleteInputBoundary;
import user_feature.interfaces.UserGatewayInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DeleteRestaurantView extends JFrame implements ActionListener {
    /**
     * The restaurant Gateway that manages the restaurant database
     */
    RestaurantDSGateway restaurantGateway;
    /**
     * The User Gateway that manages the user database
     */
    UserGatewayInterface userGateway;
    /**
     * The current restaurant
     */
    Restaurant restaurant;
    /**
     * The current active User
     * NOTE: the owner must own the restaurant to access this view, this check is done before call
     */
    OwnerUser owner;
    /**
     * The previous frame
     */
    IFrame previousFrame;


    /**
     *
     * @param restaurant the current restaurant that was clicked
     * @param owner the current active OwnerUser
     * @param restaurantGateway the Restaurant Gateway that manages the Restaurant database
     * @param userGateway the User Gateway that manages the User database
     * @param previousFrame the previous frame that initialized this one
     */
    public DeleteRestaurantView(Restaurant restaurant, OwnerUser owner, RestaurantDSGateway restaurantGateway,
                                UserGatewayInterface userGateway, IFrame previousFrame) {
        this.previousFrame = previousFrame;
        this.restaurantGateway = restaurantGateway;
        this.userGateway = userGateway;
        this.restaurant = restaurant;
        this.owner = owner;

        // Label Creation
        JLabel label = new JLabel("Are you sure you'd like to delete your restaurant?");
        label.setFont(label.getFont().deriveFont(16F));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button Creation
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(confirm);
        confirm.setBackground(Color.GREEN);
        buttons.add(cancel);
        cancel.setBackground(Color.RED);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        confirm.addActionListener(this);
        cancel.addActionListener(this);

        // Create the main JPanel
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(label);
        main.add(buttons);

        this.setContentPane(main);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 100));
        this.pack();
        this.setLocationRelativeTo(null);

        // Make the frame appear
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // initialize the presenter, controller and use case interactor
            RestaurantDeletePresenter presenter = new RestaurantDeleteResponseFormatter(previousFrame);
            RestaurantDeleteInputBoundary interactor = new DeleteRestaurantInteractor(restaurantGateway, userGateway, presenter);
            RestaurantDeleteController restaurantController = new RestaurantDeleteController(interactor);
            // Check if the person clicked confirm, if so interact
            if (Objects.equals(e.getActionCommand(), "Confirm")) {
                RestaurantResponseModel result = restaurantController.delete(owner, restaurant);
                JOptionPane.showMessageDialog(this, result.getOperation());
            }

            // Disposes the screen if confirm has run and the Restaurant Deleted, or
            // cancel is clicked
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }
}
