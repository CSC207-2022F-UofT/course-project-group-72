//Class that displays the screen when you click on a restaurant in the search screen

package ReviewScreens;

import ReviewGateways.ReviewGateway;
import ReviewGateways.UserGateway;
import ReviewControllers.*;
import ReviewInteractors.*;
import ReviewInterfaces.*;
import entities.*;
import restaurant_use_case.FileRestaurant;
import restaurant_use_case.RestaurantDSGateway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class RestaurantView extends JFrame implements ActionListener {

    private static final String REPORT_DATABASE_NAME = "";
    private static final String RESTAURANT_DATABASE_NAME = "";
    private User user;
    Restaurant restaurant;
    ReviewGatewayInterface reviewGateway;
    RestaurantDSGateway restaurantGateway;

    public RestaurantView(User user, Restaurant restaurant){
        try {
            //Initialize all gateways that may be needed
            ReviewGatewayInterface reviewGateway = new ReviewGateway();
            RestaurantDSGateway restaurantGateway = new FileRestaurant(RESTAURANT_DATABASE_NAME);
            UserGatewayInterface userGateway = new UserGateway();

            //Initialize all interactors and controllers that may be needed, except report. That must be initialized on
            //a user-by-user basis
            WriteReviewInputBoundary writeReviewInteractor = new WriteReviewInteractor();
            WriteReviewController writeReviewController = new WriteReviewController(writeReviewInteractor);
            EditReviewInputBoundary editReviewInteractor = new EditReviewInteractor();
            EditReviewController editReviewController = new EditReviewController(editReviewInteractor);
            LikeReviewInputBoundary likeReviewInteractor = new LikeReviewInteractor();
            LikeReviewController likeReviewController = new LikeReviewController(likeReviewInteractor);
            DeleteReviewInputBoundary deleteReviewInteractor = new DeleteReviewInteractor();
            DeleteReviewController deletereviewController = new DeleteReviewController(deleteReviewInteractor);
            ReplyInputBoundary replyInteractor = new ReplyInteractor();
            ReplyController replyController = new ReplyController(replyInteractor);
            //reportDsGateway reportGateway = new FileReportHistory(REPORT_DATABASE_NAME);
            //ReportFactory reportFactory = new ReportFactory();
            //reportInputBoundary reportInteractor = new ReportInteract(new );
            //ReportController reportController = new ReportController(reportInteractor);

            //Attach these objects to the screen, so they can be used by actionPerformed
            this.user = user;
            this.restaurant = restaurant;

            //The ids we need to get the review objects
            ArrayList<String> ids = this.restaurant.getReviewIDs();
            ArrayList<Review> reviews = this.reviewGateway.getReviews(ids);

            //Create a label for the name of the restaurant
            JLabel restaurantName = new JLabel(this.restaurant.getName());
            restaurantName.setFont(restaurantName.getFont().deriveFont(24F));
            restaurantName.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Create a label for the location of the restaurant
            JLabel restaurantLocation = new JLabel(this.restaurant.getLocation());
            restaurantLocation.setFont(restaurantLocation.getFont().deriveFont(16F));
            restaurantLocation.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Create a label for the average stars
            String averageText = "Average Stars: " + this.restaurant.getAvgStars();
            JLabel averageStars = new JLabel(averageText);
            averageStars.setFont(averageStars.getFont().deriveFont(16F));
            averageStars.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Create a button to write or edit review. Only displayed if user is not an owner. Displays write if the
            //user has not reviewed this restaurant and displays edit if they have.
            JButton writeEdit = new JButton("Write a Review");
            writeEdit.setFont(writeEdit.getFont().deriveFont(16F));
            for(String reviewID : user.getPast_reviews()){
                if(!(user instanceof OwnerUser) && ids.contains(reviewID)){
                    writeEdit.setText("Edit your Review");
                    writeEdit.setActionCommand("Edit your Review");
                    break;
                }
            }

            //Create a scroll pane for each review panel to be placed into
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setLayout(new BoxLayout(scrollPane, BoxLayout.Y_AXIS));

            //Initialize the components and the panel to hold them
            JLabel usernameLabel;
            JTextArea reviewText;
            JButton likeButton;
            JButton deleteButton;
            JButton replyButton;
            JButton reportButton;
            JPanel buttonPanel;
            JPanel panel;

            //Create the panels for the reviews. Use different buttons depending on whether the user is an owner user
            //or not
            for(Review review : reviews){
                usernameLabel = new JLabel("User: " + user.getUsername());
                usernameLabel.setFont(usernameLabel.getFont().deriveFont(16F));
                usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                reviewText = new JTextArea(review.getText());
                reviewText.setLineWrap(true);
                reviewText.setEditable(false);
                reviewText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                reviewText.setFont(reviewText.getFont().deriveFont(16F));
                reviewText.setAlignmentX(Component.LEFT_ALIGNMENT);

                if(!(user instanceof OwnerUser)){
                    likeButton = new JButton("Like");
                    likeButton.setBackground(Color.WHITE);
                    likeButton.setOpaque(true);
                    likeButton.addActionListener(new LikeActionListener(this, likeButton, likeReviewController,
                            reviewGateway, review, userGateway.getUser(review.getUser())));
                }
            }

        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "There was an error loading the restaurant's" +
                    "information. Please try again later.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
