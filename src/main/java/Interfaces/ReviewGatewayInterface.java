package Interfaces;

import java.util.ArrayList;

public interface ReviewGatewayInterface {
    public String loadReviewID();

    public void incrementReviewID();

    public entities.Review getReview(String id);

    public ArrayList<entities.Review> getReviews(ArrayList<String> ids);

    public void addReview(String id, String stars, String text, String username, String restaurantAddress,
                          String likes, String response, String reports, String visible);

    public void deleteReview(String id);

}
