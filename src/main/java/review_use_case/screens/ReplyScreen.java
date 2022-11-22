//Class responsible for displaying the UI for an owner to reply to a review

package review_use_case.screens;

import review_use_case.interfaces.ReviewGatewayInterface;
import review_use_case.controllers.ReplyController;
import entities.Restaurant;
import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReplyScreen extends JDialog implements ActionListener {

    //We need a reference to the text area so the action listener method can get the text
    private final JTextArea textArea;
    //We need a reference to the RestaurantView so this dialog can be made modal in relation to it
    private final JFrame owner;
    //We need a controller to talk to the interactor
    private final ReplyController replyController;
    //We need to pass these to the controller
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;
    //We need these to re-launch RestaurantView after changes are made
    private final User user;
    private final Restaurant restaurant;

    public ReplyScreen(JFrame owner, ReplyController replyController, ReviewGatewayInterface reviewGateway,
                       Review review, User user, Restaurant restaurant){
        //Call super on owner so that this dialog is modal in relation to it
        super(owner, true);

        //Attach the given attributes to the screen so the action listener method can access them
        this.owner = owner;
        this.replyController = replyController;
        this.reviewGateway = reviewGateway;
        this.review = review;
        this.user =user;
        this.restaurant = restaurant;

        //Create a JLabel instructing the owner to write a response to the review
        JLabel label = new JLabel("What would you like to say to this reviewer?");
        label.setFont(label.getFont().deriveFont(16F));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Initialize the text area and set its properties
        this.textArea = new JTextArea();
        this.textArea.setLineWrap(true);
        this.textArea.setText(review.getResponse());
        this.textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.textArea.setPreferredSize(new Dimension(400, 200));
        this.textArea.setFont(textArea.getFont().deriveFont(16F));
        this.textArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Create a button for the owner to submit their reply and add the action listener
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Create a new JPanel to hold all the components
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        //Add all the other components
        main.add(label);
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
    The action listener method. If the owner has used the gateway delimiter, tell them to remove it. Otherwise, attempt
    to add this reply to the review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //If the text contains the delimiter, tell the owner to remove it
        if(this.textArea.getText().contains(ReviewGatewayInterface.getDelimiter())){
            JOptionPane.showMessageDialog(this,
                    "Reply text may not contain the following character: " +
                            ReviewGatewayInterface.getDelimiter() +
                            ". Please remove this to submit your reply.");
        //Otherwise, attempt to submit the reply and delete the window
        }else{
            ReviewResponseModel responseModel = this.replyController.interact(this.reviewGateway,
                    this.review, this.textArea.getText());
            if(responseModel.wasSuccessful()){
                JOptionPane.showMessageDialog(this, "Your reply has been " +
                        "successfully submitted!");
                //Close all old windows and re-launch RestaurantView with this reply added
                this.dispose();
                this.owner.dispose();
                new RestaurantView(this.user, this.restaurant);
            }else{
                JOptionPane.showMessageDialog(this, "An error has occurred. Please try " +
                        "again later.");
            }
            //Delete the window
            this.dispose();
        }
    }
}
