package user_use_cases;

import entities.Review;
import entities.User;

public interface UserGatewayInterface {
    public void updateUser(User user);

    public User getUser(String username);

    public Boolean userExists(String username);

    public String getPassword(String username);

    public void addUser(String username, String password);

    public void banUser(String username);

    public void addReview(String reviewId);

    public void addLikedReview(String reviewId);

    void deleteReview(String reviewID);

}
