package restaurant_use_case.gateways;

import entities.Restaurant;

import java.util.ArrayList;

/**
 * The gateway interface, for dependency inversion
 */
public interface RestaurantDSGateway {
    /**
     * Save a Restaurant to the database
     *
     * @param restaurant the Restaurant that is requested to be saved
     */
    void save(Restaurant restaurant);
    /**
     * Check if there is already a Restaurant at a certain location
     *
     * @param location the location to be checked
     * @return boolean if or if not a Restaurant exists at that locations
     */
    boolean existsByLocation(String location);
    /**
     * Get the Restaurant at that location
     *
     * @param location the location of the Restaurant to be returned
     * @return Restaurant object at that location
     */
    Restaurant retrieveRestaurant(String location);
    /**
     * Get all the restaurants in the database
     *
     * @return An ArrayList of all the Restaurants in the database
     */
    ArrayList<Restaurant> retrieveAllRestaurants();
    /**
     * Delete the Restaurant at that location
     *
     * @param location the location of the Restaurant to be deleted
     */
    void deleteRestaurant(String location);
    ArrayList<Restaurant> searchMatch(String nameQuery, String locationQuery);
}
