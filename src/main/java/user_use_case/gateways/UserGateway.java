package user_use_case.gateways;

import entities.OwnerUser;
import entities.User;
import entities.UserFactory;
import entities.OwnerFactory;
import user_use_case.interfaces.UserGatewayInterface;

import java.io.BufferedWriter;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserGateway implements UserGatewayInterface {
    /*
    UserDatabase.csv -> How Instance Variables are saved
     Column 0: username (String)
     Column 1: password (String)
     Column 2: past_reviews (ArrayList<String>) - Use delimiter "/", to separate Review.id values.
     Column 3: likedReviews (ArrayList<String>) - Use delimiter "|" to separate Review.id string values.
     Column 4: received_reports (int)
     Column 5: banned (Boolean) -> 0 - false, 1 - true
     Column 6: owner (Boolean) -> 0 - false, 1 - true
     Column 7: owned_restaurants (ArrayList<String>) - Use Use delimiter "%", to separate Owned locations.
     */

    /**
     * Pathway to csv User Database file
     */
    private String NAME_OF_USER_DATABASE;
    /**
     * Pathway to csv temp_ User Database File. Used in updateUser() and removeUser()
     */
    private String TEMP_FILE;
    /**
     * String "empty", used when array list of owned restaurants is empty (using "" causes errors.)
     */
    private static final String EMPTY_FILLER = "empty";
    /**
     * ownerFactory is an instance of OwnerFactory()
     */
    private OwnerFactory ownerFactory = new OwnerFactory();
    /**
     * userFactory is an instance of UserFactory()
     */
    private UserFactory userFactory = new UserFactory();

    public UserGateway() {
        this.NAME_OF_USER_DATABASE = "src/main/java/Databases/UserDatabase.csv";
        this.TEMP_FILE =  "src/main/java/Databases/temp_UserDatabase.csv";
    }

    // When testing, String path_database, String path_temp_database must be in same folder/directroy,
    // because one is just being renamed to another, so they must be in the same file.
    public UserGateway(String path_database, String path_temp_database) {
        this.NAME_OF_USER_DATABASE = path_database;
        this.TEMP_FILE = path_temp_database;
    }

    /**
     * Update the User Object in the UserDatabase.
     *
     * Note: Cannot update User with new username since the username is the ID of a user
     *
     * @param user The User that is being updated in User database
     */
    @Override
    public void updateUser(User user) {

        // Below (Lines 63-98): Transform user attributes into corresponding String values.

        String new_username = user.getUsername();
        String new_password = user.getPassword();


        ArrayList<String> past_review_list = user.getPast_reviews();
        String new_past_reviews = String.join("/ ", past_review_list);

        ArrayList<String> liked_review_list = user.getLikedReviews();
        String new_likedReviews = String.join("| ", liked_review_list);


        String new_received_reports = String.valueOf(user.getReceived_reports());

        String new_banned = "0";
        if (user.isBanned()) {
            new_banned = "1";
        }

        String new_owner = "0";
        if (user.isOwner()) {
            new_owner = "1";
        }

        String new_owner_restaurants = "";

        if (user instanceof OwnerUser) {
            OwnerUser owner_user = (OwnerUser) user;

            ArrayList<String> owner_restaurants_list = owner_user.getOwnedRestaurants();

            new_owner_restaurants = String.join("% ", owner_restaurants_list);
        }

        if (new_owner_restaurants.length() == 0) {
            new_owner_restaurants = EMPTY_FILLER;
        }

        File old_file = new File(this.NAME_OF_USER_DATABASE);
        File new_file = new File(this.TEMP_FILE);

        String username;
        String password;
        String past_reviews;
        String likedReviews;
        String received_reports;
        String banned;
        String owner;
        String owned_restaurants;


        try {
            FileWriter fw = new FileWriter(new_file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            File file = new File(this.NAME_OF_USER_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ", ";
            String line;

            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String[] values = line.split(delimiter);

                username = values[0];
                password = values[1];
                past_reviews = values[2];
                likedReviews = values[3];
                received_reports = values[4];
                banned = values[5];
                owner = values[6];
                owned_restaurants = values[7];


                // If line contains user: replace the old values with the new values
                String line1;
                if (new_username.equals(username)) {
                    line1 = String.join(", ", username, new_password, new_past_reviews,
                            new_likedReviews, new_received_reports, new_banned, new_owner, new_owner_restaurants);
                    line1 = line1 + ", ";

                    // Else: keep the old values
                } else {
                    line1 = String.join(", ", username, password, past_reviews,
                            likedReviews, received_reports, banned, owner, owned_restaurants);
                    line1 = line1 + ", ";
                }
                // Write all lines in temp file, the line containing user will be written with user updated.
                bw.write(line1);
                bw.newLine();

            }

            scanner.close();
            bw.flush();
            bw.close();
            old_file.delete(); // this file returns bool but we do not need

            File temp = new File(this.NAME_OF_USER_DATABASE);
            new_file.renameTo(temp);


        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Can't find this User");
            e.printStackTrace();
        }

    }

    @Override
    public User getUser(String username) {
        try {
            File file = new File(this.NAME_OF_USER_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ", ";
            String line;

            while(scanner.hasNext()){
                line = scanner.nextLine();
                String[] user = line.split(delimiter);

                if (user[0].equals(username)){
                    // Format Past Reviews

                    String[] past_reviews_elements = user[2].split("/ ");
                    ArrayList<String> return_past_reviews = new ArrayList<>(
                            Arrays.asList( past_reviews_elements ) );

                    String[] liked_reviews_elements = user[3].split("| ");
                    ArrayList<String> return_liked_reviews = new ArrayList<>(
                            Arrays.asList( liked_reviews_elements ) );

                    Boolean return_banned = false;
                    if (Integer.parseInt(user[5]) == 1) {
                        return_banned = true;
                    }

                    Boolean return_owner = false;
                    if (Integer.parseInt(user[6]) == 1) {
                        return_owner = true;
                    }

                    ArrayList<String> return_owned_restaurants = new ArrayList<>();

                    String[] owned_restaurant_elements = user[2].split("% ");
                    if (!owned_restaurant_elements.equals(EMPTY_FILLER)) {
                        return_owned_restaurants = new ArrayList<>(
                                Arrays.asList( owned_restaurant_elements ) );
                    }


                    User return_user = userFactory.reintialize(user[0], user[1], return_past_reviews, return_liked_reviews,
                            Integer.parseInt(user[4]), return_banned, return_owner);

                    if (return_owner) {
                        return_user = ownerFactory.reintialize(user[0], user[1], return_past_reviews, return_liked_reviews,
                                Integer.parseInt(user[4]), return_banned, true, return_owned_restaurants);
                    }

                    return return_user;
                }
            }
        }catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void removeUser(String username) {
        // Change username of user to "Removed" in database
        // functionality will be equivalent to if user was from database

        File old_file = new File(this.NAME_OF_USER_DATABASE);
        File new_file = new File(this.TEMP_FILE);

        String curr_username;
        String password;
        String past_reviews;
        String likedReviews;
        String received_reports;
        String banned;
        String owner;
        String owned_restaurants;


        try {
            FileWriter fw = new FileWriter(new_file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            File file = new File(this.NAME_OF_USER_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ", ";
            String line;

            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String[] values = line.split(delimiter);

                curr_username = values[0];
                password = values[1];
                past_reviews = values[2];
                likedReviews = values[3];
                received_reports = values[4];
                banned = values[5];
                owner = values[6];
                owned_restaurants = values[7];


                // replace the old values with the new values
                String line1;
                if (!username.equals(curr_username)) {
                    //keep the old values
                    line1 = String.join(", ", curr_username, password, past_reviews,
                            likedReviews, received_reports, banned, owner, owned_restaurants);
                } else {
                    line1 = "";
                }

                bw.write(line1);

                if (!line1.equals("")) {

                    bw.newLine();
                }

            }

            scanner.close();
            bw.flush();
            bw.close();
            old_file.delete();

            File temp = new File(this.NAME_OF_USER_DATABASE);
            new_file.renameTo(temp);


        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Can't find this User");
            e.printStackTrace();
        }


    }

    @Override
    public Boolean userExists(String username) {
        try {
            File file = new File(this.NAME_OF_USER_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ", ";
            String line;

            while(scanner.hasNext()){
                line = scanner.nextLine();
                String[] user = line.split(delimiter);

                if (user[0].equals(username)){
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getPassword(String username) {
        try {
            File file = new File(this.NAME_OF_USER_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ", ";
            String line;

            while(scanner.hasNext()) {
                line = scanner.nextLine();
                String[] user = line.split(delimiter);

                if (user[0].equals(username)) {
                    return user[1];
                }
            }
        }catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void addUser(String username, String password) {
        try {
            File file = new File(this.NAME_OF_USER_DATABASE);
            FileWriter writer = new FileWriter(file, true);
            writer.append(username); // String username
            writer.append(", ");
            writer.append(password); // String password
            writer.append(", ");
            writer.append(""); // ArrayList<String> past_reviews
            writer.append(", ");
            writer.append(""); // ArrayList<String> likedReviews
            writer.append(", ");
            writer.append("0"); // int received_reports = 0;
            writer.append(", ");
            writer.append("0"); // boolean banned -> 0 - false, 1 - true
            writer.append(", ");
            writer.append("0"); // boolean owner -> 0 - false, 1 - true
            writer.append(", ");
            writer.append(EMPTY_FILLER); // ArrayList<String> owned_restaurants
            writer.append(", ");
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: Adding User to DataBase");
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String username, String password, ArrayList<String> pastReviews,
                        ArrayList<String> likedReviews,
                        int receivedReports, boolean banned, boolean isOwner,
                        ArrayList<String> ownedRestaurants) {
        try {
            File file = new File(this.NAME_OF_USER_DATABASE);
            FileWriter writer = new FileWriter(file, true);

            String str_past_reviews = String.join("/ ", pastReviews);

            String str_likedReviews = String.join("| ", likedReviews);

            String str_received_reports = String.valueOf(receivedReports);

            String str_banned = "0";
            if (banned) {
                str_banned = "1";
            }

            String str_owner = "0";
            if (isOwner) {
                str_owner = "1";
            }

            String str_owned_restaurants = "";

            if (isOwner && ownedRestaurants.size() > 0) {
                    str_owned_restaurants = String.join("% ", ownedRestaurants);
            } else {
                str_owned_restaurants = EMPTY_FILLER;
            }

            writer.append(username); // String username
            writer.append(", ");
            writer.append(password); // String password
            writer.append(", ");
            writer.append(str_past_reviews); // ArrayList<String> past_reviews
            writer.append(", ");
            writer.append(str_likedReviews); // ArrayList<String> likedReviews
            writer.append(", ");
            writer.append(str_received_reports); // int received_reports = 0;
            writer.append(", ");
            writer.append(str_banned); // boolean banned -> 0 - false, 1 - true
            writer.append(", ");
            writer.append(str_owner); // boolean owner -> 0 - false, 1 - true
            writer.append(", ");
            writer.append(str_owned_restaurants); // ArrayList<String> owned_restaurants
            writer.append(", ");
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: Adding User to DataBase");
            e.printStackTrace();
        }
    }

    public void addReview(String reviewId, String userid) {
        User curr_user = getUser(userid);
        curr_user.add_review(reviewId);
        updateUser(curr_user);
    }

    public void addLikedReview(String reviewId, String userid) {
        User curr_user = getUser(userid);
        curr_user.addLikedReview(reviewId);
        updateUser(curr_user);
    }

}
