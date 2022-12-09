package user_use_case.interfaces;

import entities.User;

import java.util.ArrayList;

/**
 * User Gateway interface, for dependency inversion
 */
public interface UserGatewayInterface {
    /**
     * If the instance user does not match how it is saved in the User Database, updateUser will
     * update the attributes of the user in the database.
     *
     * @param user The User that is being updated in User database
     */
    void updateUser(User user);

    /**
     * Update the User Object in the UserDatabase.
     *
     * Note: Cannot update User with new username since the username is the ID of a user
     *
     * @param username the String username of the given user
     * @return The whole User object corresponding to the username input
     */
    User getUser(String username);

    /**
     *
     * @param username the String username of the given user
     * @return Bool, true is the String username is already in the User Database
     */
    Boolean userExists(String username);

    /**
     * @param username the String username of the given user
     * @return The String password of the User object corresponding to the username input
     */
    String getPassword(String username);

    /**
     * Primarily used when saving a new user for the first time
     *
     * @param username the String username of the given user
     * @param password the String password of the given user
     */
    void addUser(String username, String password);

    /**
     *
     * @param username the String username of the given user
     * @param password the String password of the given user
     * @param pastReviews the ArrayList past reviews of the given user
     * @param likedReviews the likedReviews liked reviews of the given user
     * @param receivedReports the int received reports of the given user
     * @param banned the String bool banned of the given user
     * @param isOwner the String bool owner of the given user
     * @param ownedRestaurants the ArrayList password of the given user
     */
    void addUser(String username, String password, ArrayList<String> pastReviews,
                        ArrayList<String> likedReviews,
                        int receivedReports, boolean banned, boolean isOwner,
                        ArrayList<String> ownedRestaurants);

    void addReview(String reviewId, String userid);

    /**
     * Removes User Object from UserDatabase
     *
     * @param username the String username of the given user
     */
    void removeUser(String username);

    //public void removeUser(User user);
    void addLikedReview(String reviewId, String userid);

}
