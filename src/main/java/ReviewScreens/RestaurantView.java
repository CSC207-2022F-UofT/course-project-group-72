//Class that displays the screen when you click on a restaurant in the search screen

package ReviewScreens;

import entities.*;
import ReviewControllers.*;
import ReviewInteractors.*;
import report_screens.FileReportHistory;
import report_screens.ReportController;
import report_screens.ReportResponseFormat;
import report_use_cases.*;
import ReviewInterfaces.*;
import ReviewGateways.ReviewGateway;
import restaurant_screens.*;
import restaurant_use_case.*;
import user_use_cases.UserGatewayInterface;
import user_use_cases.UserGateway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class RestaurantView extends JFrame implements ActionListener, IFrame {

    private static final String REPORT_DATABASE_NAME = "src/main/java/Databases/ReportDatabase.csv";
    private static final String RESTAURANT_DATABASE_NAME = "src/main/java/Databases/RestaurantDatabase.csv";
    private User user;
    private Restaurant restaurant;
    private ReviewGatewayInterface reviewGateway;
    private RestaurantDSGateway restaurantGateway;
    private UserGatewayInterface userGateway;
    private WriteReviewController writeReviewController;

    public RestaurantView(User user, Restaurant restaurant){
        try {
            //Initialize all gateways that may be needed
            this.reviewGateway = new ReviewGateway();
            this.restaurantGateway = new FileRestaurant(RESTAURANT_DATABASE_NAME);
            this.userGateway = new UserGateway();

            //Initialize all interactors and controllers that may be needed, except report. That must be initialized on
            //a user-by-user basis. Attach write and edit controllers to the screen object so the action performed
            //method can access them
            WriteReviewInputBoundary writeReviewInteractor = new WriteReviewInteractor();
            this.writeReviewController = new WriteReviewController(writeReviewInteractor);
            EditReviewInputBoundary editReviewInteractor = new EditReviewInteractor();
            EditReviewController editReviewController = new EditReviewController(editReviewInteractor);
            LikeReviewInputBoundary likeReviewInteractor = new LikeReviewInteractor();
            LikeReviewController likeReviewController = new LikeReviewController(likeReviewInteractor);
            DeleteReviewInputBoundary deleteReviewInteractor = new DeleteReviewInteractor();
            DeleteReviewController deletereviewController = new DeleteReviewController(deleteReviewInteractor);
            ReplyInputBoundary replyInteractor = new ReplyInteractor();
            ReplyController replyController = new ReplyController(replyInteractor);

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

            //Create a button to write a review. Only displayed if user is not an owner
            JButton writeButton = new JButton("Write a Review");
            writeButton.setFont(writeButton.getFont().deriveFont(16F));
            writeButton.setOpaque(true);
            writeButton.addActionListener(this);

            //Create a panel to hold the buttons the owner sees when they click on the restaurant. Only visible to
            //restaurant owner, not all owners
            JPanel ownerPanel = new JPanel();
            ownerPanel.setLayout(new BoxLayout(ownerPanel, BoxLayout.X_AXIS));
            ownerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            ownerPanel.setVisible(true);

            //Create the buttons for an owner to edit or delete their restaurant, then add them to owner panel
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
                if(!(review.getResponse().equals(""))){
                    text = review.getText() + "\n\n" + "Owner Response: " + review.getResponse();
                }else{
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

                if(!(this.user instanceof OwnerUser)){
                    likeButton = new JButton("Like");
                    if(this.user.getLikedReviews().contains(review.getID())){
                        likeButton.setBackground(Color.CYAN);
                    }else {
                        likeButton.setBackground(Color.WHITE);
                    }
                    likeButton.setOpaque(true);
                    likeButton.addActionListener(new LikeActionListener(this, likeButton, likeReviewController,
                            this.reviewGateway, review, this.user));
                    buttonPanel.add(likeButton);

                    if(review.getUser().equals(user.getUsername())){
                        editButton = new JButton("Edit");
                        editButton.setBackground(Color.GREEN);
                        editButton.setOpaque(true);
                        editButton.addActionListener(new EditActionListener(this, editReviewController,
                                this.reviewGateway, review, this.user, this.restaurant));
                        buttonPanel.add(editButton);

                        deleteButton = new JButton("Delete");
                        deleteButton.setBackground(Color.RED);
                        deleteButton.setOpaque(true);
                        deleteButton.addActionListener(new DeleteActionListener(this, deletereviewController,
                                this.reviewGateway, this.userGateway, this.restaurantGateway,
                                review, this.user, this.restaurant));
                        buttonPanel.add(deleteButton);
                    }else{
                        reportDsGateway reportGateway = new FileReportHistory(REPORT_DATABASE_NAME);
                        ReportFactory reportFactory = new ReportFactory();
                        Excalibur excalibur = new Excalibur(userGateway.getUser(review.getUser()), review);
                        ReportPresenter reportPresenter = new ReportResponseFormat();
                        reportInputBoundary reportInteractor = new ReportInteract(reportGateway, reportFactory,
                                excalibur, reportPresenter);
                        ReportController reportController = new ReportController(reportInteractor);
                        reportButton = new JButton("Report");
                        reportButton.setOpaque(true);
                        reportButton.addActionListener(new ReportActionListener(reportController, review, user));
                        buttonPanel.add(reportButton);
                    }
                }else if(this.restaurant.getOwnerID().equals(this.user.getUsername())){
                    replyButton = new JButton("Reply");
                    replyButton.setOpaque(true);
                    replyButton.addActionListener(new ReplyActionListener(this, replyController,
                            this.reviewGateway, review, this.user, this.restaurant));
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

            //Now that all the components are made, create the main JPanel
            JPanel main = new JPanel();
            main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
            main.add(restaurantName);
            main.add(restaurantLocation);
            main.add(averageStars);
            if(!(user instanceof OwnerUser)) {
                main.add(writeButton);
            }else if(this.restaurant.getOwnerID().equals(this.user.getUsername())){
                main.add(ownerPanel);
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
        //If a user clicked the write review button
        if(e.getActionCommand().equals("Write a Review")) {
            //Check if they already reviewed this restaurant
            boolean check = true;
            for (String reviewID : this.user.getPast_reviews()) {
                if (this.restaurant.getReviewIDs().contains(reviewID)) {
                    check = false;
                    break;
                }
            }
            //Launch the proper view
            if (check) {
                WriteReviewScreen writeScreen = new WriteReviewScreen(this, this.writeReviewController,
                        this.reviewGateway, this.userGateway, this.restaurantGateway, this.user, this.restaurant);
            } else {
                JOptionPane.showMessageDialog(this, "You've already written a review for this " +
                        "restaurant. Please edit your previous review instead.");
            }
        //If an owner clicked edit
        }else if(e.getActionCommand().equals("Edit your Restaurant")){
            //Try to launch the appropriate view
            try {
                RestaurantDSGateway restaurantGateway = new FileRestaurant(RESTAURANT_DATABASE_NAME);
                RestaurantPresenter restaurantPresenter = new RestaurantResponseFormatter(this);
                RestaurantInputBoundary editRestaurant = new editRestaurant(restaurantGateway, restaurantPresenter);
                RestaurantController restaurantController = new RestaurantController(editRestaurant);
                EditRestaurantView editRestaurantView = new EditRestaurantView(restaurantController,
                        (OwnerUser) this.user, this.restaurant);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.");
                System.out.println("An error occurred. Please try again later.");
                ex.printStackTrace();
            }
        //If an owner clicked delete
        } else if (e.getActionCommand().equals("Delete Your Restaurant")) {
            //Try to launch the appropriate view
            try {
                RestaurantDSGateway restaurantGateway = new FileRestaurant(RESTAURANT_DATABASE_NAME);
                RestaurantDeletePresenter restaurantPresenter = new RestaurantDeleteResponseFormatter();
                RestaurantDeleteInputBoundary deleteRestaurant = new deleteRestaurant(restaurantGateway,
                        restaurantPresenter);
                RestaurantDeleteController restaurantDeleteController =
                        new RestaurantDeleteController(deleteRestaurant);
                DeleteRestaurantView deleteRestaurantView = new DeleteRestaurantView(restaurantDeleteController,
                        this.restaurant, (OwnerUser) this.user);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.");
                System.out.println("An error occurred. Please try again later.");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void refresh(Restaurant restaurant) {
        this.repaint();
    }
}
