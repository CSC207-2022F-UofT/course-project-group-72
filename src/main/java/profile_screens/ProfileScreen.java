package profile_screens;

import Gateways.ReviewGateway;
import Gateways.UserGateway;
import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProfileScreen extends JFrame {
    User profile;

    JFrame profile_screen_window;

    ArrayList<Review> reviews;

    public ProfileScreen(String profile_name) {
        UserGateway userGateway = new UserGateway();

        this.profile = userGateway.getUser(profile_name);  // TODO: change with actual gateway

        ReviewGateway reviewGateway = new ReviewGateway();

        this.reviews = reviewGateway.getReviews(profile.getPast_reviews());

        profile_screen_window = new JFrame();

        JLabel title = new JLabel(profile.getUsername());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        int totalStars = 0;

        ArrayList<JPanel> review_panels = new ArrayList<>();

        for (Review review : this.reviews) {
            JLabel restarauntName = new JLabel(review.getRestaurant());
            JLabel reviewText = new JLabel(review.getText());
            int starCount = review.getStars();
            JLabel stars = new JLabel(Integer.toString(starCount) + "/5 stars");


            JPanel panel = new JPanel();
            panel.add(restarauntName);
            panel.add(stars);
            panel.add(reviewText);

            review_panels.add(panel);

            totalStars = totalStars + starCount;
        }

        int averageStars = totalStars / this.reviews.size();


        JLabel averageStarLabel = new JLabel(Integer.toString(averageStars));

        profile_screen_window.add(title);
        profile_screen_window.add(averageStarLabel);


        for (JPanel panel : review_panels) {
            profile_screen_window.add(panel);
        }

        profile_screen_window.setVisible(true);

    }

}
