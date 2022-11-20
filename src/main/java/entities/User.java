package entities;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<String> past_reviews;
    private ArrayList<String> likedReviews;
    private int received_reports = 0;
    private boolean banned = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.past_reviews = new ArrayList<>();
        this.likedReviews = new ArrayList<>();
    }

    public User(String username, String password, ArrayList<Review> past_reviews,
                ArrayList<String> likedReviews, int received_reports, Boolean banned) {
        this.username = username;
        this.password = password;
        this.past_reviews = past_reviews;
        this.likedReviews = likedReviews;
        this.received_reports = received_reports;
        this.banned = banned;
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

    public void add_review(String id) {
        this.past_reviews.add(id);
    }

    public ArrayList<String> getLikedReviews() {
        return likedReviews;
    }

    public void addLikedReview(String id){
        this.likedReviews.add(id);
    }

    public void removeLikedReview(String id){
        this.likedReviews.remove(id);
    }

    public int getReceived_reports(){
        return this.received_reports;
    }

    public void addReport(){
        this.received_reports++;
    }

    public boolean isBanned(){
        return this.banned;
    }
    public void setBanned(){
        this.banned = true;
    }
}
