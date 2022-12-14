package entities;

import java.util.ArrayList;

/**
 * The OwnerUser is a subclass of User, which has the additional capabilities to own and interact
 * with owned restaurants
 */
public class OwnerUser extends User{
    /**
     * The list of Restaurants IDs that the OwnerUser currently owns
     */
    private ArrayList<String> ownedRestaurants;
    /**
     *  Constructed in the same way as User, with the additional instance
     *  variable ownedRestaurants
     *
     * @param username the unique username of the User
     * @param password the password of the User
     */
    OwnerUser(String username, String password) {
        super(username, password);
        this.ownedRestaurants = new ArrayList<>();
    }

    /**
     * Reinitializes the OwnerUser
     *
     * @param username the unique username of the User
     * @param password the password of the User
     * @param pastReviews the pastReviews of the User
     * @param likedReviews the likedReviews of the User
     * @param receivedReports the receivedReports of the User
     * @param banned the boolean, if or if not the User was banned
     * @param isOwner the boolean, if or if not the User is an OwnerUser
     * @param ownedRestaurants the ownedRestaurants of the OwnerUser
     */
    OwnerUser(String username, String password, ArrayList<String> pastReviews, ArrayList<String> likedReviews,
              int receivedReports, boolean banned, boolean isOwner,
              ArrayList<String> ownedRestaurants) {
        super(username, password, pastReviews, likedReviews, receivedReports, banned, isOwner);
        this.ownedRestaurants = ownedRestaurants;
    }
    /**
     *
     * @param restaurant adds the Restaurant to the list of ownedRestaurants of this
     *                   OwnerUser
     */
    public void addRestaurant(Restaurant restaurant) {
        this.ownedRestaurants.add(restaurant.getLocation());
    }
    /**
     *
     * @param restaurant removes the Restaurant from the list of ownedRestaurants of this
     *                   OwnerUser
     */
    public void removeRestaurant(Restaurant restaurant) {this.ownedRestaurants.remove(restaurant.getLocation());}
    /**
     *
     * @return ArrayList of all the ownedRestaurants
     */
    public ArrayList<String> getOwnedRestaurants() {
        return this.ownedRestaurants;
    }

}
