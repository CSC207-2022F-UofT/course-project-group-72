package entities;

import java.util.ArrayList;

public class UserFactory {

    public User CreateUserObject(String username, String password) {
        return new User(username, password);
    }

    /**
     *
     * @param username the username of the User
     * @param password the password of the User
     * @return an OwnerUser instance
     */
    public User reintialize(String username, String password, ArrayList<String> pastReviews,
                                 ArrayList<String> likedReviews,
                                 int receivedReports, boolean banned, boolean isOwner) {
        return new User(username, password, pastReviews, likedReviews, receivedReports, banned, isOwner);
    }
}
