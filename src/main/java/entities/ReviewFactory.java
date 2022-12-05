package entities;

/*
Creates a new review or reinitializes one from the database. Allows for package protection of review entity.
 */
public class ReviewFactory {

    public ReviewFactory(){}

    /**
     * Create a new review object from the given attributes
     *
     * @param id The id of the review
     * @param stars The stars given to this review
     * @param text The text the user provided for the review
     * @param username The username of the user who wrote the review
     * @param restaurantLocation The restaurant this review is for
     */
    public Review create(String id, int stars, String text, String username, String restaurantLocation){
        return new Review(id, stars, text, username, restaurantLocation);
    }

    /**
     * Create a new review object from the given attributes
     *
     * @param id The id of the review
     * @param stars The stars given to this review
     * @param text The text the user provided for the review
     * @param username The username of the user who wrote the review
     * @param restaurantLocation The restaurant this review is for
     * @param likes The likes given to this review
     * @param response The response of the restaurant's owner
     * @param reports How many reports this review has received
     * @param visible Can the review be seen
     */
    public Review reinitialize(String id, int stars, String text, String username, String restaurantLocation, int likes,
                               String response, int reports, boolean visible){
        return new Review(id, stars, text, username, restaurantLocation, likes, response, reports, visible);
    }
}
