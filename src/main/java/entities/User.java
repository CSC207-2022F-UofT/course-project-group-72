package entities;

import java.util.ArrayList;

public class User {
    public String username;
    private String password;
    private ArrayList<Review> past_reviews;



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
}
