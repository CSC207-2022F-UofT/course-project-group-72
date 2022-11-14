//Class responsible for displaying the UI required for a user to delete a review

package ReviewScreens;

import ReviewInterfaces.ReviewGatewayInterface;
import ReviewInterfaces.UserGatewayInterface;
import restaurant_use_case.RestaurantDSGateway;
import ReviewControllers.DeleteReviewController;
import entities.Restaurant;
import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteReviewScreen extends JFrame implements ActionListener {

    private final DeleteReviewController deleteReviewController;
    private final ReviewGatewayInterface reviewGateway;
    private final UserGatewayInterface userGateway;
    private final RestaurantDSGateway restaurantGateway;
    private final Review review;
    private final User user;
    private final Restaurant restaurant;

    public DeleteReviewScreen(DeleteReviewController deleteReviewController, ReviewGatewayInterface reviewGateway,
                              UserGatewayInterface userGateway, RestaurantDSGateway restaurantGateway,
                              Review review, User user, Restaurant restaurant){

        //Attach attributes to the screen, so they can be used by the action listeners
        this.deleteReviewController = deleteReviewController;
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
        this.restaurantGateway = restaurantGateway;
        this.review = review;
        this.user = user;
        this.restaurant = restaurant;

        //Create a label asking the user if they really want to delete the review
        JLabel label = new JLabel("Are you sure you'd like to delete this review?");
        label.setFont(label.getFont().deriveFont(16F));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Create the buttons for the user to delete the review or cancel. Make the delete button red
        JButton delete = new JButton("Delete");
        delete.setBackground(Color.RED);
        delete.setOpaque(true);
        JButton cancel = new JButton("Cancel");

        //Add the action listener to each button
        delete.addActionListener(this);
        cancel.addActionListener(this);

        //Create a panel to hold the buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(cancel);
        buttons.add(delete);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Create the main JPanel that holds all the other components together
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        //Add the other components
        main.add(label);
        main.add(buttons);

        //Set this instance's content pane to main and set its behaviour
        this.setContentPane(main);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(300, 200));
        this.pack();
        this.setLocationRelativeTo(null);

        //Make the JFrame appear
        this.setVisible(true);

    }

    /*
    Action listener method. If the user clicked delete, send the information to the controller to delete the review.
    If they clicked cancel, do nothing. Regardless, close view afterwards
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();

        //If the user clicks delete, call the controller and interact
        if(buttonPressed.equals("Delete")) {
            ReviewResponseModel response = this.deleteReviewController.interact(this.reviewGateway, this.userGateway,
                    this.restaurantGateway, this.review, this.user, this.restaurant);
            //Tell the user if they were successful
            if (response.wasSuccessful()) {
                JOptionPane.showMessageDialog(this, "Your review has been successfully deleted!");
            } else {
                JOptionPane.showMessageDialog(this, "An error has occurred. " +
                        "Please try again later.");
            }
        }
        //Regardless, dispose of the window
        this.dispose();
    }
}
