package entities;

import java.util.ArrayList;

public class Review {

    private int stars;
    private String text;
    private final User user; //Possibly remove later
    private final Restaurant restaurant; //Possibly remove later
    private int likes;
    private String response;
    private final ArrayList<Report> reports;
    private boolean visible;

    /*
    Construct a new review object using the given parameters

    REMOVE LATER: I like to be explicit when writing my code, so I called this.attribute = defaultValue even though
    it is not strictly necessary. We can change this later if it is a bad practice
     */
    public Review(int stars, String text, User user, Restaurant restaurant){
        this.stars = stars;
        this.text = text;
        this.user = user;
        this.restaurant = restaurant;
        this.likes = 0;
        this.response = null;
        this.reports = new ArrayList<Report>();
        this.visible = true;
    }

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
    public User getUser() {return this.user;}

    /*
    Getter for restaurant
     */
    public Restaurant getRestaurant() {return this.restaurant;}

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
    public ArrayList<Report> getReports() {return this.reports;}

    /*
    Add a new report to this Review's list of reports
     */
    public void addReport(Report report){this.reports.add(report);}

    /*
    Getter for visible
     */
    public boolean isVisible() {return this.visible;}

    /*
    Setter for visible
     */
    public void setVisible(boolean visible) {this.visible = visible;}

}
