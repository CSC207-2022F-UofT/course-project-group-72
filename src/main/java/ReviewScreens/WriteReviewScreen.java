//Class responsible for displaying the UI for a user to write a review

package ReviewScreens;

import ReviewInterfaces.ReviewGatewayInterface;
import ReviewInterfaces.UserGatewayInterface;
import restaurant_use_case.RestaurantDSGateway;
import ReviewControllers.WriteReviewController;
import entities.User;
import entities.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WriteReviewScreen extends JDialog implements ActionListener {
    //Track which star button the user has clicked most recently. Default is -1
    private int stars;
    //We need a reference to the text area to get the user's text
    private final JTextArea textArea;
    //We need a reference to the buttons to change their colour based off of what the user clicks
    private final JButton oneStar;
    private final JButton twoStar;
    private final JButton threeStar;
    private final JButton fourStar;
    private final JButton fiveStar;
    //We need a reference to the restaurantView, so we can reload it and make this dialog modal in relation to it
    private final JFrame owner;
    //We need a controller to talk to the interactor
    private final WriteReviewController writeReviewController;
    //We need to pass these to the controller
    private final ReviewGatewayInterface reviewGateway;
    private final UserGatewayInterface userGateway;
    private final RestaurantDSGateway restaurantGateway;
    private final User user;
    private final Restaurant restaurant;

    public WriteReviewScreen(JFrame owner, WriteReviewController writeReviewController,
                             ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                             RestaurantDSGateway restaurantGateway, User user, Restaurant restaurant){
        //Call super on owner so that this dialog must be closed before the user can click on other windows
        super(owner, true);

        //Attach the given objects to the screen object so that that action listener can use them
        //Additionally, set stars to -1 to track if the user has selected a number of stars yet
        this.stars = -1;
        this.owner = owner;
        this.writeReviewController = writeReviewController;
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
        this.restaurantGateway = restaurantGateway;
        this.user = user;
        this.restaurant = restaurant;

        //Create a label instructing the user to choose a number of stars for the restaurant
        JLabel starLabel = new JLabel("How many stars would you give this restaurant?");
        starLabel.setFont(starLabel.getFont().deriveFont(16F));
        starLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Create the buttons for each star and set default colour to white
        this.oneStar = new JButton("1");
        this.oneStar.setBackground(Color.WHITE);
        this.oneStar.setOpaque(true);
        this.twoStar = new JButton("2");
        this.twoStar.setBackground(Color.WHITE);
        this.twoStar.setOpaque(true);
        this.threeStar = new JButton("3");
        this.threeStar.setBackground(Color.WHITE);
        this.threeStar.setOpaque(true);
        this.fourStar = new JButton("4");
        this.fourStar.setBackground(Color.WHITE);
        this.fourStar.setOpaque(true);
        this.fiveStar = new JButton("5");
        this.fiveStar.setBackground(Color.WHITE);
        this.fiveStar.setOpaque(true);

        //Add the action listener to the star buttons. The action listener changes the colour of each star button up to
        // the button clicked to yellow, change the buttons afterward to white and change stars to reflect the value of
        // the button clicked
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

        //Create a label suggesting the user add some text to the review
        JLabel textLabel = new JLabel("Feel free to provide additional details below.");
        textLabel.setFont(textLabel.getFont().deriveFont(16F));
        textLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Create a text area for the uer to enter their text
        this.textArea = new JTextArea();
        this.textArea.setLineWrap(true);
        this.textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.textArea.setPreferredSize(new Dimension(400, 200));
        this.textArea.setFont(textArea.getFont().deriveFont(16F));
        this.textArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Create a button for the user to submit their review and attach an action listener
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
    The action performed method. Checks which button was pressed and acts accordingly. Star buttons change stars and
    submit calls the controller's interact method so long as the user has selected a number of stars and has not used
    the review gateway's delimiter
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
                //If the user has not selected any stars, tell them to do so
                if (this.stars == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a number of stars.");
                //If they have used the delimiter, tell them to remove it
                } else if (this.textArea.getText().contains(ReviewGatewayInterface.getDelimiter())) {
                    JOptionPane.showMessageDialog(this,
                            "Review text may not contain the following character: " +
                                    ReviewGatewayInterface.getDelimiter() +
                                    ". Please remove this to submit your review.");
                //Otherwise, send information to controller and interact
                } else {
                    ReviewResponseModel response = this.writeReviewController.interact(this.reviewGateway,
                            this.userGateway, this.restaurantGateway, this.user, this.restaurant, this.stars,
                            this.textArea.getText());
                    //Tell the user if it was successful or not
                    if(response.wasSuccessful()){
                        JOptionPane.showMessageDialog(this, "Your review has been successfully" +
                                " submitted!");
                        //Close all old windows and re-launch RestaurantView with the new review added
                        this.dispose();
                        this.owner.dispose();
                        new RestaurantView(this.user, this.restaurant);
                    }else{
                        JOptionPane.showMessageDialog(this, "An error has occurred. Please try" +
                                " again later.");
                    }
                    //Close the window
                    this.dispose();
                }
                break;
        }
    }
}
