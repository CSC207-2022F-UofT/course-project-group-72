package entities;

import java.util.ArrayList;

/**
 * The OwnerUser is a subclass of User, which has the additional capabilities to own and interact
 * with owned restaurants
 */
public class OwnerUser extends User{
    /**
     * The list of Restaurants that the OwnerUser currently owns
     */
    private ArrayList<Restaurant> ownedRestaurants;
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
     *
     * @param restaurant adds the Restaurant to the list of ownedRestaurants of this
     *                   OwnerUser
     */
    public void addRestaurant(Restaurant restaurant) {
        this.ownedRestaurants.add(restaurant);
    }
    /**
     *
     * @param restaurant removes the Restaurant from the list of ownedRestaurants of this
     *                   OwnerUser
     */
    public void removeRestaurant(Restaurant restaurant) {this.ownedRestaurants.remove(restaurant);}
    /**
     *
     * @return ArrayList of all the ownedRestaurants
     */
    public ArrayList<Restaurant> getOwnedRestaurants() {
        return this.ownedRestaurants;
    }
}
