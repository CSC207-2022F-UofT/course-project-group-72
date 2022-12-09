package entities;

import java.util.ArrayList;

public class User extends GuestUser{
    /**
     * The User's username
     */
    private String username;
    /**
     * The User's login password
     */
    private String password;
    /**
     * The User's past reviews
     * The ArrayList-String- contains the String IDs of Review objects
     */
    private ArrayList<String> past_reviews;
    /**
     * The User's past liked reviews
     * The ArrayList-String- contains the String IDs of past liked Review objects
     */
    private ArrayList<String> likedReviews;
    /**
     * The number of Reports User has received
     */
    private int received_reports = 0;
    /**
     * true - User is Banned
     * false - User is not Banned
     */
    private boolean banned = false;
    /**
     * true - User instanceof OwnerUser
     * false - !(User instanceof OwnerUser)
     */
    private boolean owner = false;

    /**
     * The creator constructor of the (new) user
     *
     * @param username User's username
     * @param password User's password
     */

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.past_reviews = new ArrayList<>();
        this.likedReviews = new ArrayList<>();
        this.received_reports = 0;
        this.banned = false;
        this.owner = false;
    }

    /**
     * The reinitialization constructor of an existing user
     *
     * @param username User's username
     * @param password User's password
     * @param past_reviews User's passed reviews (String Ids of reviews)
     * @param likedReviews User's passed liked reviews (String Ids of reviews)
     * @param received_reports User's received reports
     * @param banned Whether the User is banned
     * @param owner Whether the User is: instanceof OwnerUser
     */

    public User(String username, String password, ArrayList<String> past_reviews,
                ArrayList<String> likedReviews, int received_reports, Boolean banned, Boolean owner) {
        this.username = username;
        this.password = password;
        this.past_reviews = past_reviews;
        this.likedReviews = likedReviews;
        this.received_reports = received_reports;
        this.banned = banned;
        this.owner = owner;
    }

    public User() {
        // For GuestUser Implementation
        this.username = null;
        this.password = null;
    }
    // Get & Set Username
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Get & Set Password
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Get & Add to Past Reviews
    public ArrayList<String> getPast_reviews() {
        return this.past_reviews;
    }

    public void add_review(String reviewid) {
        this.past_reviews.add(reviewid);
    }

     // Get & Add to Liked Reviews
    public ArrayList<String> getLikedReviews() {
        return likedReviews;
    }

    public void addLikedReview(String id){
        this.likedReviews.add(id);
    }

    public void removeLikedReview(String id){
        this.likedReviews.remove(id);
    }

    // Get & Add Report of User
    public int getReceived_reports(){
        return this.received_reports;
    }
    public void addReport(){
        this.received_reports++;
    }

    // Get & Set User Banned
    public boolean isBanned(){
        return this.banned;
    }
    public void setBanned(){
        this.banned = true;
    }


    // Equivalent to: user instanceof OwnerUser
    public boolean isOwner() {
        return owner;
    }

    public void setOwner() { this.owner = true; }
}
