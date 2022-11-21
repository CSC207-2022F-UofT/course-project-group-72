package entities;

import java.util.ArrayList;

/**
 * Creates an instance of OwnerUser in the same manner as User
 */
public class OwnerFactory extends UserFactory{
    /**
     * User to create a new instance of OwnerUser
     *
     * @param username the unique username of the User
     * @param password the password of the User
     * @return an OwnerUser instance
     */
    @Override
    public OwnerUser CreateUserObject(String username, String password) {
        return new OwnerUser(username, password);
    }

    /**
     * Used to reinitialize the OwnerUser
     *
     * @param username the unique username of the User
     * @param password the password of the User
     * @param pastReviews the pastReviews of the User
     * @param likedReviews the likedReviews of the User
     * @param receivedReports the receivedReports of the User
     * @param banned the boolean, if or if not the User was banned
     * @param isOwner the boolean, if or if not the User is an OwnerUser
     * @param ownedRestaurants the ownedRestaurants of the OwnerUser
     * @return an OwnerUser instance
     */
    public OwnerUser reintialize(String username, String password, ArrayList<String> pastReviews,
                                 ArrayList<String> likedReviews,
                                 int receivedReports, boolean banned, boolean isOwner,
                                 ArrayList<String> ownedRestaurants) {
        return new OwnerUser(username, password, pastReviews, likedReviews, receivedReports, banned, isOwner,
                ownedRestaurants);
    }
}
