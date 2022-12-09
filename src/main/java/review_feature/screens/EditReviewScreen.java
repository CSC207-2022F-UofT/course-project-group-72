//Class responsible for displaying the UI for a user to edit a review

package review_feature.screens;

import entities.Restaurant;
import restaurant_feature.interfaces.RestaurantDSGateway;
import review_feature.interfaces.ReviewGatewayInterface;
import review_feature.controllers.EditReviewController;
import entities.Review;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditReviewScreen extends JDialog implements ActionListener {

    //We need this to track which button the user has clicked
    private int stars;
    //We need a reference to the text area to get the user's text
    private final JTextArea textArea;
    //We need a reference to the buttons to change their colour based off of what the user clicks
    private final JButton oneStar;
    private final JButton twoStar;
    private final JButton threeStar;
    private final JButton fourStar;
    private final JButton fiveStar;
    //We need a reference to the RestaurantView so this dialog can be made modal in relation to it
    private final RestaurantView restaurantView;
    //We need a controller to talk to the interactor
    private final EditReviewController editReviewController;
    //We need to pass these to the controller
    private final ReviewGatewayInterface reviewGateway;
    private final RestaurantDSGateway restaurantGateway;
    private final Review review;
    private final Restaurant restaurant;

    public EditReviewScreen(RestaurantView restaurantView, EditReviewController editReviewController,
                            ReviewGatewayInterface reviewGateway, RestaurantDSGateway restaurantGateway,
                            Review review, Restaurant restaurant){
        //Call super on owner so that this dialog is modal in relation to it
        super(restaurantView, true);

        //Attach these to the screen so that the action listener may use them
        this.restaurantView = restaurantView;
        this.editReviewController = editReviewController;
        this.reviewGateway = reviewGateway;
        this.restaurantGateway = restaurantGateway;
        this.review = review;
        this.restaurant = restaurant;

        //Store the review's old stars, so we do not need to keep calling review methods
        this.stars = this.review.getStars();

        //Create a label asking user if they would like to change the number of stars
        JLabel starLabel = new JLabel("Edit number of stars:");
        starLabel.setFont(starLabel.getFont().deriveFont(16F));
        starLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Create the buttons for each star and make them opaque
        this.oneStar = new JButton("1");
        this.oneStar.setOpaque(true);
        this.twoStar = new JButton("2");
        this.twoStar.setOpaque(true);
        this.threeStar = new JButton("3");
        this.threeStar.setOpaque(true);
        this.fourStar = new JButton("4");
        this.fourStar.setOpaque(true);
        this.fiveStar = new JButton("5");
        this.fiveStar.setOpaque(true);

        //Change the colour of the buttons depending on what the previous number of stars was
        switch(this.stars){
            case(1):
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.WHITE);
                this.threeStar.setBackground(Color.WHITE);
                this.fourStar.setBackground(Color.WHITE);
                this.fiveStar.setBackground(Color.WHITE);
                break;
            case(2):
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.YELLOW);
                this.threeStar.setBackground(Color.WHITE);
                this.fourStar.setBackground(Color.WHITE);
                this.fiveStar.setBackground(Color.WHITE);
                break;
            case(3):
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.YELLOW);
                this.threeStar.setBackground(Color.YELLOW);
                this.fourStar.setBackground(Color.WHITE);
                this.fiveStar.setBackground(Color.WHITE);
                break;
            case(4):
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.YELLOW);
                this.threeStar.setBackground(Color.YELLOW);
                this.fourStar.setBackground(Color.YELLOW);
                this.fiveStar.setBackground(Color.WHITE);
                break;
            case(5):
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.YELLOW);
                this.threeStar.setBackground(Color.YELLOW);
                this.fourStar.setBackground(Color.YELLOW);
                this.fiveStar.setBackground(Color.YELLOW);
                break;
            default:
                this.oneStar.setBackground(Color.WHITE);
                this.twoStar.setBackground(Color.WHITE);
                this.threeStar.setBackground(Color.WHITE);
                this.fourStar.setBackground(Color.WHITE);
                this.fiveStar.setBackground(Color.WHITE);
                break;
        }

        //Add action listeners to the star buttons. These action listeners change the colour of each star button up to
        //the button clicked to yellow, change the buttons afterward to white and change stars to reflect the value of
        //the button clicked
        this.oneStar.addActionListener(this);
        this.twoStar.addActionListener(this);
        this.threeStar.addActionListener(this);
        this.fourStar.addActionListener(this);
        this.fiveStar.addActionListener(this);

        //Create a panel to hold the star buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(this.oneStar);
        buttons.add(this.twoStar);
        buttons.add(this.threeStar);
        buttons.add(this.fourStar);
        buttons.add(this.fiveStar);
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Create a label telling the user they can edt the text
        JLabel textLabel = new JLabel("Edit text:");
        textLabel.setFont(textLabel.getFont().deriveFont(16F));
        textLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Set characteristics of the text area and set the default text to the review's old text
        this.textArea = new JTextArea();
        this.textArea.setLineWrap(true);
        this.textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.textArea.setPreferredSize(new Dimension(400, 200));
        this.textArea.setFont(textArea.getFont().deriveFont(16F));
        this.textArea.setText(this.review.getText());
        this.textArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Create a button for the user to submit their review and attach the action listener
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Create the main JPanel that holds all the other components together
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        //Add all the other components
        main.add(starLabel);
        main.add(buttons);
        main.add(textLabel);
        main.add(this.textArea);
        main.add(submit);

        //Set this instance's content pane to main and set its behaviour
        this.setContentPane(main);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        this.pack();
        this.setLocationRelativeTo(null);

        //Make the JFrame appear
        this.setVisible(true);
    }

    /*
    The action listener method. If the user has used the ReviewGateway delimiter, tell them to remove it. Otherwise, try
    to pass the necessary info to the controller
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();

        switch (buttonPressed) {
            case "1":
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.WHITE);
                this.threeStar.setBackground(Color.WHITE);
                this.fourStar.setBackground(Color.WHITE);
                this.fiveStar.setBackground(Color.WHITE);
                this.stars = 1;
                break;
            case "2":
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.YELLOW);
                this.threeStar.setBackground(Color.WHITE);
                this.fourStar.setBackground(Color.WHITE);
                this.fiveStar.setBackground(Color.WHITE);
                this.stars = 2;
                break;
            case "3":
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.YELLOW);
                this.threeStar.setBackground(Color.YELLOW);
                this.fourStar.setBackground(Color.WHITE);
                this.fiveStar.setBackground(Color.WHITE);
                this.stars = 3;
                break;
            case "4":
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.YELLOW);
                this.threeStar.setBackground(Color.YELLOW);
                this.fourStar.setBackground(Color.YELLOW);
                this.fiveStar.setBackground(Color.WHITE);
                this.stars = 4;
                break;
            case "5":
                this.oneStar.setBackground(Color.YELLOW);
                this.twoStar.setBackground(Color.YELLOW);
                this.threeStar.setBackground(Color.YELLOW);
                this.fourStar.setBackground(Color.YELLOW);
                this.fiveStar.setBackground(Color.YELLOW);
                this.stars = 5;
                break;
            default:
                //If the text contains the delimiter, tell the user to remove it
                if (this.textArea.getText().contains(ReviewGatewayInterface.getDelimiter())) {
                    JOptionPane.showMessageDialog(this,
                            "Review text may not contain the following character: " +
                                    ReviewGatewayInterface.getDelimiter() +
                                    ". Please remove this to submit your review.");
                //Otherwise, send the information to the controller and interact
                } else {
                    ReviewResponseModel response = this.editReviewController.interact(this.reviewGateway,
                            this.restaurantGateway, this.review, this.restaurant, this.stars, this.textArea.getText());
                    //Tell the user if they were successful
                    if(response.wasSuccessful()){
                        JOptionPane.showMessageDialog(this, "Your review has been successfully" +
                                " edited!");
                        //Close all old windows and re-launch RestaurantView with this review removed
                        this.dispose();
                        this.restaurantView.refresh();
                    }else{
                        JOptionPane.showMessageDialog(this, "An error has occurred. Please try" +
                                " again later.");
                    }
                    //Delete the window
                    this.dispose();
                }
                break;
        }
    }
}
