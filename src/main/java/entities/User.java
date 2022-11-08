package entities;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Review> past_reviews;
    private Boolean banned_user;
    private int num_reports;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.past_reviews = new ArrayList<>();
        this.banned_user = false;
        this.num_reports = 0;
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

    // Get & Set Banned User
    public Boolean getBanned_user() { return this.banned_user; }

    public void banUser() { this.banned_user = true; }

    // Get & Add to Number of Reports of User
    public int getNum_reports() { return this.num_reports; }

    public void addOneReport() { this.num_reports += 1; }
}
