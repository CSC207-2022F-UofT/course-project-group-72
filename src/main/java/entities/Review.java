package entities;

public class Review {

    private static final Interfaces.ReviewGatewayInterface gateway = new Gateways.ReviewGateway();
    private static String currentID = "0"; //NOTE: In main, call Review.setCurrentID();
    private final String id;
    private int stars;
    private String text;
    private final String username;
    private final String restaurantAddress;
    private int likes;
    private String response;
    private int reports;
    private boolean visible;

    /*
    Construct a new review object using the given parameters
     */
    public Review(String id, int stars, String text, String user, String restaurantAddress){
        this.id = id;
        this.stars = stars;
        this.text = text;
        this.username = user;
        this.restaurantAddress = restaurantAddress;
        this.likes = 0;
        this.response = null;
        this.reports = 0;
        this.visible = true;
    }

    /*
    Reconstruct a Review object using information from the database
     */
    public Review(String id, int stars, String text, String username, String restaurantAddress, int likes,
                  String response, int reports, boolean visible){
        this.id = id;
        this.stars = stars;
        this.text = text;
        this.username = username;
        this.restaurantAddress = restaurantAddress;
        this.likes = likes;
        this.response = response;
        this.reports = reports;
        this.visible = visible;
    }

    public static String getCurrentID() {
        return currentID;
    }
    public static void setCurrentID(){
        currentID = gateway.loadReviewID();
    }

    public static void incrementCurrentID(){
        currentID = currentID + 1;
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
    Getter for restaurant
     */
    public String getRestaurant() {return this.restaurantAddress;}

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

    @Override
    public String toString(){
        return this.id;
    }

}
