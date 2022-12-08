//Class that displays the screen when you click on a restaurant in the search screen

package review_use_case.screens;

import entities.*;
import report_use_case.gateways.reportDsGateway;
import report_use_case.interactors.Excalibur;
import report_use_case.interactors.ReportInteract;
import report_use_case.interfaces.BanningAlgorithm;
import report_use_case.interfaces.reportInputBoundary;
import report_use_case.screens.ReportPresenter;
import restaurant_use_case.gateways.RestaurantDSGateway;
import restaurant_use_case.interactors.FileRestaurant;
import review_use_case.controllers.*;
import review_use_case.interactors.*;
import report_use_case.screens.FileReportHistory;
import report_use_case.screens.ReportController;
import report_use_case.screens.ReportResponseFormat;
import review_use_case.interfaces.*;
import restaurant_use_case.screens.DeleteRestaurantView;
import restaurant_use_case.screens.EditRestaurantView;
import global.IFrame;
import review_use_case.gateways.ReviewGateway;
import user_use_case.interfaces.UserGatewayInterface;
import user_use_case.gateways.UserGateway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class RestaurantView extends IFrame implements ActionListener {

    private static final String REPORT_DATABASE_NAME = "src/main/java/Databases/ReportDatabase.csv";
    private static final String RESTAURANT_DATABASE_NAME = "src/main/java/Databases/RestaurantDatabase.csv";
    private IFrame previousFrame;
    private User user;
    private Restaurant restaurant;
    private ReviewGatewayInterface reviewGateway;
    private RestaurantDSGateway restaurantGateway;
    private UserGatewayInterface userGateway;

    public RestaurantView(IFrame previousFrame, User user, Restaurant restaurant){
        try {
            //Initialize all gateways that may be needed
            this.reviewGateway = new ReviewGateway();
            this.restaurantGateway = new FileRestaurant(RESTAURANT_DATABASE_NAME);
            this.userGateway = new UserGateway();

            //Initialize all interactors and controllers that may be needed, except report. That must be initialized on
            //a user-by-user basis. Attach write and edit controllers to the screen object so the action performed
            //method can access them
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

            //Attach these objects to the screen, so they can be used by actionPerformed
            this.previousFrame = previousFrame;
            this.user = user;
            this.restaurant = restaurant;

            //The ids we need to get the review objects
            ArrayList<String> ids = this.restaurant.getReviewIDs();
            ArrayList<Review> reviews = this.reviewGateway.getReviews(ids);

            //Create the back and home buttons
            JButton backButton = new JButton("Back");
            backButton.setFont(backButton.getFont().deriveFont(12F));
            backButton.setOpaque(true);
            backButton.addActionListener(this);

            JButton homeButton = new JButton("Home");
            homeButton.setFont(homeButton.getFont().deriveFont(12F));
            homeButton.setOpaque(true);
            homeButton.addActionListener(this);

            //Create a panel to hold the back and home buttons, then add those buttons
            JPanel IPanel = new JPanel();
            IPanel.setLayout(new BoxLayout(IPanel, BoxLayout.X_AXIS));
            IPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            IPanel.setVisible(true);
            IPanel.add(backButton);
            IPanel.add(homeButton);

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

            //Create a button to write a review. Only displayed if user is not an owner
            JButton writeButton = new JButton("Write a Review");
            writeButton.setFont(writeButton.getFont().deriveFont(16F));
            writeButton.setOpaque(true);
            writeButton.addActionListener(new WriteReviewActionListener(this, writeReviewController,
                    this.reviewGateway, this.userGateway, this.restaurantGateway, this.user, this.restaurant));

            //Create a panel to hold the buttons the owner sees when they click on the restaurant. Only visible to
            //restaurant owner, not all owners
            JPanel ownerPanel = new JPanel();
            ownerPanel.setLayout(new BoxLayout(ownerPanel, BoxLayout.X_AXIS));
            ownerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            ownerPanel.setVisible(true);

            //Create the buttons for the owner to edit or delete their restaurant, then add them to owner panel
            JButton ownerEditButton = new JButton("Edit your Restaurant");
            ownerEditButton.setFont(ownerEditButton.getFont().deriveFont(16F));
            ownerEditButton.setOpaque(true);
            ownerEditButton.addActionListener(this);

            JButton ownerDeleteButton = new JButton("Delete Your Restaurant");
            ownerDeleteButton.setFont(ownerDeleteButton.getFont().deriveFont(16F));
            ownerDeleteButton.setOpaque(true);
            ownerDeleteButton.addActionListener(this);

            ownerPanel.add(ownerEditButton);
            ownerPanel.add(ownerDeleteButton);

            //Create a label informing the user that they are banned. Only appears when the user is banned
            JLabel banned = new JLabel("You have been banned. Your contributions have been removed and you will " +
                    "not be able to participate in this website going forward.");
            banned.setFont(banned.getFont().deriveFont(16F));
            banned.setAlignmentX(Component.LEFT_ALIGNMENT);

            //The scroll pane may only hold a single panel, so we initialize a "master panel" to hold each review panel
            JPanel masterPanel = new JPanel();
            masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
            masterPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            masterPanel.setVisible(true);

            //Create a scroll pane for each review panel to be placed into
            JScrollPane scrollPane = new JScrollPane(masterPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setLayout(new ScrollPaneLayout());
            scrollPane.setVisible(true);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Initialize the components of each review and a panel to hold them
            JLabel usernameLabel;
            JLabel starLabel;
            JTextArea reviewText;
            JPanel buttonPanel;
            JButton likeButton;
            JButton editButton;
            JButton deleteButton;
            JButton replyButton;
            JButton reportButton;
            JPanel panel;

            //Create the panels for the reviews. Use different buttons depending on whether the user is an owner user
            //or not
            for(Review review : reviews){
                //If the review has not been deleted or removed due to reports
                if(review.isVisible()) {
                    //Create the label for the username
                    usernameLabel = new JLabel("User: " + review.getUser());
                    usernameLabel.setFont(usernameLabel.getFont().deriveFont(16F));
                    usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    //Create the label for the average stars
                    starLabel = new JLabel("Stars Given: " + review.getStars());
                    starLabel.setFont(starLabel.getFont().deriveFont(16F));
                    starLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    //Create the text area to hold the review's text and, if applicable, the owner's response
                    String text;
                    if (!(review.getResponse().equals(""))) {
                        text = review.getText() + "\n\n" + "Owner Response: " + review.getResponse();
                    } else {
                        text = review.getText();
                    }
                    reviewText = new JTextArea(text);
                    reviewText.setLineWrap(true);
                    reviewText.setEditable(false);
                    reviewText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    reviewText.setFont(reviewText.getFont().deriveFont(16F));
                    reviewText.setAlignmentX(Component.LEFT_ALIGNMENT);

                    //Create the panel to hold the buttons
                    buttonPanel = new JPanel();
                    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
                    buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    //If the user does not own this restaurant and they are not banned
                    if (!(this.user.getUsername().equals(this.restaurant.getOwnerID())) && !(this.user.isBanned())) {
                        //Create a like button
                        likeButton = new JButton("Likes: " + review.getLikes());
                        if (this.user.getLikedReviews().contains(review.getID())) {
                            likeButton.setBackground(Color.CYAN);
                        } else {
                            likeButton.setBackground(Color.WHITE);
                        }
                        likeButton.setOpaque(true);
                        likeButton.addActionListener(new LikeActionListener(this, likeButton, likeReviewController,
                                this.reviewGateway, review, this.user));
                        buttonPanel.add(likeButton);

                        //If the current user wrote the review
                        if (review.getUser().equals(user.getUsername())) {
                            //Create edit and delete buttons
                            editButton = new JButton("Edit");
                            editButton.setBackground(Color.GREEN);
                            editButton.setOpaque(true);
                            editButton.addActionListener(new EditActionListener(this, editReviewController,
                                    this.reviewGateway, review));
                            buttonPanel.add(editButton);

                            deleteButton = new JButton("Delete");
                            deleteButton.setBackground(Color.RED);
                            deleteButton.setOpaque(true);
                            deleteButton.addActionListener(new DeleteActionListener(this, deletereviewController,
                                    this.reviewGateway, this.userGateway, this.restaurantGateway,
                                    review, this.user, this.restaurant));
                            buttonPanel.add(deleteButton);
                        } else {
                            //Create a report button
                            reportDsGateway reportGateway = new FileReportHistory(REPORT_DATABASE_NAME);
                            ReportFactory reportFactory = new ReportFactory();
                            BanningAlgorithm excalibur = new Excalibur(userGateway.getUser(review.getUser()), review);
                            ReportPresenter reportPresenter = new ReportResponseFormat();
                            reportInputBoundary reportInteractor = new ReportInteract(reportGateway, reportFactory,
                                    excalibur, reportPresenter);
                            ReportController reportController = new ReportController(reportInteractor);
                            reportButton = new JButton("Report");
                            reportButton.setOpaque(true);
                            reportButton.addActionListener(new ReportActionListener(this, reportController,
                                    review, user));
                            buttonPanel.add(reportButton);
                        }
                    //If the user owns the restaurant and they are not banned
                    } else if(this.user.getUsername().equals(this.restaurant.getOwnerID()) &&
                            !(this.user.isBanned())) {
                        //Create a reply button
                        replyButton = new JButton("Reply");
                        replyButton.setOpaque(true);
                        replyButton.addActionListener(new ReplyActionListener(this, replyController,
                                this.reviewGateway, review));
                        buttonPanel.add(replyButton);
                    }

                    //Add all the previous components in the for loop, except those already added to buttonPanel,
                    //to a new panel
                    panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    panel.setPreferredSize(new Dimension(800, 200));
                    panel.setVisible(true);

                    panel.add(usernameLabel);
                    panel.add(starLabel);
                    panel.add(reviewText);
                    panel.add(buttonPanel);

                    //Add this new panel to the scroll pane
                    masterPanel.add(panel);
                }
            }

            //Now that all the components are made, create the main JPanel
            JPanel main = new JPanel();
            main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
            main.add(IPanel);
            main.add(restaurantName);
            main.add(restaurantLocation);
            main.add(averageStars);
            if(!(this.user.getUsername().equals(this.restaurant.getOwnerID())) && !(this.user.isBanned())) {
                main.add(writeButton);
            }else if(this.user.getUsername().equals(this.restaurant.getOwnerID()) && !(this.user.isBanned())){
                main.add(ownerPanel);
            }else{
                main.add(banned);
            }
            main.add(scrollPane);

            //Set this instance's content pane to main and set its behaviour
            this.setContentPane(main);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.pack();
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setLocationRelativeTo(null);

            //Make the JFrame appear
            this.setVisible(true);

        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "There was an error loading the restaurant's" +
                    "information. Please try again later.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Edit your Restaurant")){
            //Try to launch the appropriate view
            new EditRestaurantView((OwnerUser) this.user, this.restaurant, this, this.restaurantGateway);
        //If an owner clicked delete
        }else if(e.getActionCommand().equals("Delete Your Restaurant")) {
            //Try to launch the appropriate view
            new DeleteRestaurantView(this.restaurant, (OwnerUser) this.user, this.restaurantGateway,
                    this.userGateway, this);
        }else if(e.getActionCommand().equals("Back")){
            this.back();
        }else if(e.getActionCommand().equals("Home")){
            this.home(this.user);
        }
    }

    @Override
    public void refresh() {
        // Update the User and Restaurant
        User updatedUser = userGateway.getUser(this.user.getUsername());
        Restaurant updatedRestaurant = restaurantGateway.retrieveRestaurant(this.restaurant.getLocation());
        // Reload the view
        new RestaurantView(this.previousFrame, updatedUser, updatedRestaurant);
        // Dispose of the current iteration of view
        this.dispose();
    }

    @Override
    public void back() {
        // Refresh and therefore reload the previous screen
        this.previousFrame.refresh();
        // Dipose of the current screen
        this.dispose();
    }
}
