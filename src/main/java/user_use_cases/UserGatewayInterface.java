package user_use_cases;

import entities.Review;

public interface UserGatewayInterface {

    public Boolean userExists(String username);

    public String getPassword(String username);

    public void addUser(String username, String password);

    public void banUser(String username);

    public void addReview(String reviewId);

    public void addLikedReview(String reviewId);

    public void addReport();



}
