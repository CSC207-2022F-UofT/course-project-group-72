package user_use_case.interfaces;

import entities.User;

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
     * @param username the String username of the given user
     * @return The whole User object corresponding to the username input
     */
    User getUser(String username);

    /**
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


    void addReview(String reviewId, String userid);

    void removeUser(User user);

    //public void removeUser(User user);
    void addLikedReview(String reviewId, String userid);

}
