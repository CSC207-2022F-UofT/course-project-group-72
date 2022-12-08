package user_use_case.interactors;

import entities.User;

import java.util.ArrayList;

public class UpgradeUserRequestModel {

    private final User user;
    private final String username;
    private final String password;
    private final ArrayList<String> pastReviews;
    private final ArrayList<String> likedReviews;
    private final int received_reports;
    private final boolean banned;

    public UpgradeUserRequestModel(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.likedReviews = user.getLikedReviews();
        this.pastReviews = user.getPast_reviews();
        this.received_reports = user.getReceived_reports();
        this.banned = user.isBanned();
    }

    public User getUser() {return this.user;}

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public ArrayList<String> getPastReviews() {
        return this.pastReviews;
    }

    public ArrayList<String> getLikedReviews() {
        return this.likedReviews;
    }

    public int getReceivedReports() {
        return this.received_reports;
    }

    public boolean isBanned() {
        return this.banned;
    }

}
