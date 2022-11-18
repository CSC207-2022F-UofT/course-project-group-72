package entities;

import java.util.ArrayList;

/**
 * Creates new restaurants, or reinitializes existing restaurants,
 * allows for package protection of Restaurant entity
 */
public class RestaurantFactory {
    public RestaurantFactory() {}

    /**
     * the creation of the factory, to return a fresh restaurant with no stars and reviews
     *
     * @param ownerID the current OwnerUser's ID, showing ownership of Restaurant
     * @param name the Restaurant's desired name
     * @param location the unique postal code location of the Restaurant
     * @param cuisineType the desired cuisine served at the Restaurant
     * @param priceBucket the desired price range of the Restaurant
     * @return an instance of Restaurant with the above information
     */
    public Restaurant create(String ownerID, String name, String location, String cuisineType, int priceBucket) {
        return new Restaurant(ownerID, name, location, cuisineType, priceBucket);
    }

    /**
     * the reinitialization of factory, to return an existing restaurant with the correct stars
     * and reviews
     *
     * @param ownerID the current OwnerUser's ID, showing ownership of Restaurant
     * @param name the Restaurant's name
     * @param location the unique postal code location of the Restaurant
     * @param cuisineType the cuisine served at the Restaurant
     * @param priceBucket the price range of the Restaurant
     * @param avgStars the average stars of the Restaurant
     * @param reviews the reviews of the Restaurant
     * @return an instance of Restaurant with the above information
     */
    // NOTE Type of ArrayList<T> may be revered to Review and ownerID to OwnerUser
    public Restaurant reinitialize(String ownerID, String name, String location, String cuisineType, int priceBucket,
                                   double avgStars, ArrayList<String> reviews) {
        return new Restaurant(ownerID, name, location, cuisineType, priceBucket, avgStars, reviews);
    }
}
