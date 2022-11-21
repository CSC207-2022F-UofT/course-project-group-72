package restaurant_screens;

import entities.OwnerUser;
import entities.Restaurant;
import restaurant_use_case.RestaurantResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeleteRestaurantView extends JFrame implements ActionListener {
    /**
     * The controller
     */
    RestaurantDeleteController restaurantController;
    /**
     * The current restaurant
     */
    Restaurant restaurant;

    /**
     * The current active User
     * NOTE: the owner must own the restaurant to access this view, this check is done before call
     */
    OwnerUser owner;

    public DeleteRestaurantView(RestaurantDeleteController restaurantDeleteController,
                                Restaurant restaurant, OwnerUser owner) {

        this.restaurantController = restaurantDeleteController;
        this.restaurant = restaurant;
        this.owner = owner;

        // Label Creation
        JLabel label = new JLabel("Are you sure you'd like to delete your restaurant?");
        label.setFont(label.getFont().deriveFont(16F));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Button Creation
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(confirm);
        confirm.setBackground(Color.GREEN);
        buttons.add(cancel);
        confirm.setBackground(Color.RED);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        confirm.addActionListener(this);
        cancel.addActionListener(this);

        // Create the main JPanel
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(label);
        main.add(buttons);

        this.setContentPane(main);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(300, 200));
        this.pack();
//        this.setLocationRelativeTo(null);

        // Make the frame appear
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (Objects.equals(e.getActionCommand(), "Confirm")) {
                RestaurantResponseModel result = restaurantController.delete(owner, restaurant);
                JOptionPane.showMessageDialog(this, result.getOperation());
            }
//            Container parentPanel = this.getParent();
//            parentPanel.remove(this);
//            parentPanel.revalidate();
//            parentPanel.repaint();

            // Disposes the screen if confirm has run and the Restaurant Deleted, or
            // cancel is clicked
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }
}
