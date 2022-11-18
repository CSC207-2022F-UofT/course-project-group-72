//Object that represents a review

package entities;

public class Review {

    //Attributes each review needs
    private final String id;
    private int stars;
    private String text;
    private String username;
    private final String restaurantLocation;
    private int likes;
    private String response;
    private int reports;
    private boolean visible;

    /*
    Construct a new review object using the given parameters
     */
    public Review(String id, int stars, String text, String user, String restaurantLocation){
        this.id = id;
        this.stars = stars;
        this.text = text;
        this.username = user;
        this.restaurantLocation = restaurantLocation;
        this.likes = 0;
        this.response = "";
        this.reports = 0;
        this.visible = true;
    }

    /*
    Reconstruct a Review object using information from the database
     */
    public Review(String id, int stars, String text, String username, String restaurantLocation, int likes,
                  String response, int reports, boolean visible){
        this.id = id;
        this.stars = stars;
        this.text = text;
        this.username = username;
        this.restaurantLocation = restaurantLocation;
        this.likes = likes;
        this.response = response;
        this.reports = reports;
        this.visible = visible;
    }

    /*
    Getter for ID
     */
    public String getID() {return this.id;}

    /*
    Getter for stars
     */
    public int getStars() {return this.stars;}

    /*
    Setter for stars
     */
    public void setStars(int stars) {this.stars = stars;}

    /*
    Getter for text
     */
    public String getText() {return this.text;}

    /*
    Setter for text
     */
    public void setText(String text) {this.text = text;}

    /*
    Getter for user
     */
    public String getUser() {return this.username;}

    /*
    Setter for user
     */
    public void setUser(String newUsername){this.username = newUsername;}

    /*
    Getter for restaurant
     */
    public String getRestaurant() {return this.restaurantLocation;}

    /*
    Getter for likes
     */
    public int getLikes() {return this.likes;}

    /*
    Increase likes by 1
     */
    public void incrementLikes(){this.likes = this.likes + 1;}

    /*
    Decrease likes by 1
     */
    public void decrementLikes(){this.likes = this.likes - 1;}

    /*
    Getter for response
     */
    public String getResponse() {return this.response;}

    /*
    Setter for response
     */
    public void setResponse(String response) {this.response = response;}

    /*
    Getter for reports
     */
    public int getReports() {return this.reports;}

    /*
    Add a new report to this Review's list of reports
     */
    public void addReport(){this.reports = this.reports + 1;}

    /*
    Getter for visible
     */
    public boolean isVisible() {return this.visible;}

    /*
    Setter for visible
     */
    public void setVisible(boolean visible) {this.visible = visible;}

    /*
    The string representation of a review should just be its id
     */
    @Override
    public String toString(){
        return this.id;
    }

}
