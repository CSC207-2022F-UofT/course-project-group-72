package user_use_case;

import entities.User;

public interface UserGatewayInterface {
    public void updateUser(User user);

    public User getUser(String username);

    public Boolean userExists(String username);

    public String getPassword(String username);

    public void addUser(String username, String password);


    public void addReview(String reviewId, String userid);

    public void addLikedReview(String reviewId, String userid);

}
