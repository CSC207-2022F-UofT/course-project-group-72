package entities;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Review> past_reviews;
    private ArrayList<String> likedReviews;
    private int received_reports = 0;
    private boolean banned = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.past_reviews = new ArrayList<>();
        this.likedReviews = new ArrayList<>();
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
    public ArrayList getPast_reviews() {
        return this.past_reviews;
    }

    public void add_review(Review review) {
        this.past_reviews.add(review);
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
