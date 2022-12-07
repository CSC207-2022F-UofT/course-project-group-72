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

    private static final String NAME_OF_USER_DATABASE = "src/main/java/Databases/UserDatabase.csv";

    private static final String EMPTY_FILLER = "empty";
    private OwnerFactory ownerFactory = new OwnerFactory();
    private UserFactory userFactory = new UserFactory();

    @Override
    public void updateUser(User user) {

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

        String tempFile = "src/main/java/Databases/temp_UserDatabase.csv";

        File old_file = new File(NAME_OF_USER_DATABASE);
        File new_file = new File(tempFile);

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
            File file = new File(NAME_OF_USER_DATABASE);
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


                // replace the old values with the new values
                String line1;
                if (new_username.equals(username)) {
                    line1 = String.join(", ", username, new_password, new_past_reviews,
                            new_likedReviews, new_received_reports, new_banned, new_owner, new_owner_restaurants);

                    //keep the old values
                } else {
                    line1 = String.join(", ", username, password, past_reviews,
                            likedReviews, received_reports, banned, owner, owned_restaurants);
                }
                bw.write(line1);
                bw.newLine();

            }

            scanner.close();
            bw.flush();
            bw.close();
            old_file.delete();

            File temp = new File(NAME_OF_USER_DATABASE);
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
            File file = new File(NAME_OF_USER_DATABASE);
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

    //@Override
    //public void removeUser(User user) {
        // DOES NOT WORK CURRENTLY
        // Change username of user to "Removed" in database
        // functionality will be equivalent to if user was from database
        //String new_username = "Removed";

        //user.setUsername(new_username);

        //updateUser(user);
    //}

    @Override
    public Boolean userExists(String username) {
        try {
            File file = new File(NAME_OF_USER_DATABASE);
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
            File file = new File(NAME_OF_USER_DATABASE);
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
            File file = new File(NAME_OF_USER_DATABASE);
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
            System.out.println("An error occurred.");
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
