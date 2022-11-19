package user_use_cases;

import Interfaces.ReviewGatewayInterface;
import entities.User;
import entities.Review;
import Gateways.ReviewGateway;

import java.io.BufferedWriter;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserGateway implements UserGatewayInterface{
    /*
    UserDatabase.csv -> How Instance Variables are saved
     Column 0: username (String)
     Column 1: password (String)
     Column 2: past_reviews (ArrayList<Reviews>) - Use delimiter "/", to separate Review.id values.
     Column 3: likedReviews (ArrayList<String>) - Use delimiter "|" to separate Review.id string values.
     Column 4: received_reports (int)
     Column 5: banned (Boolean) -> 0 - false, 1 - true
     */

    private static final String NAME_OF_USER_DATABASE = "src/main/java/Databases/UserDatabase.csv";

    @Override
    public void updateUser(User user) {

        String new_username = user.getUsername();
        String new_password = user.getPassword();

        ArrayList<Review> past_review_list = user.getPast_reviews();
        String past_reviews_string = "";
        for (int i = 0; i < past_review_list.size() - 1; i++) {
            past_reviews_string = past_reviews_string + past_review_list.get(i).getID() + "/";
        }
        // last review without delimiter
        if (past_review_list.size() > 0) {
            past_reviews_string = past_reviews_string + past_review_list.get(past_review_list.size()).getID();
        }
        String new_past_reviews = past_reviews_string;

        ArrayList<String> liked_review_list = user.getLikedReviews();
        String new_likedReviews = String.join("|", liked_review_list);


        String new_received_reports = String.valueOf(user.getReceived_reports());
        String new_banned = "0";
        if (user.isBanned()) {
            new_banned = "1";
        }

        String tempFile = "src/main/java/Databases/temp_UserDatabase.csv";

        File old_file = new File(NAME_OF_USER_DATABASE);
        File new_file = new File(tempFile);

        String username = "";
        String password = "";
        String past_reviews = "";
        String likedReviews = "";
        String received_reports = "";
        String banned = "";



        try {
            FileWriter fw = new FileWriter(new_file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            Scanner scanner = new Scanner(new File(NAME_OF_USER_DATABASE));
            String delimiter = ",";
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


                // replace the old values with the new values
                if (new_username.equals(username)) {
                    String line1 = String.join(",", username, new_password, new_past_reviews,
                            new_likedReviews, new_received_reports, new_banned);
                    bw.write(line1);
                    bw.newLine();

                    //keep the old values
                } else {
                    String line1 = String.join(",", username, password, past_reviews,
                            likedReviews, received_reports, banned);
                    bw.write(line1);
                    bw.newLine();
                }

            }

            scanner.close();
            bw.flush();
            bw.close();
            old_file.delete();
            File temp = new File(NAME_OF_USER_DATABASE);
            new_file.renameTo(temp);


        } catch (Exception e) {
            System.out.println("Can't find this User");
            e.printStackTrace();
        }

    }

    @Override
    public User getUser(String username) {
        try {
            File file = new File(NAME_OF_USER_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ",";
            String line;

            while(scanner.hasNext()){
                line = scanner.nextLine();
                String[] user = line.split(delimiter);

                if (user[0].equals(username)){
                    // Format Past Reviews
                    List<String> past_review_ids = new ArrayList<String>();
                    String[] past_reviews_elements = user[2].split("/");
                    ArrayList<Review> return_past_review = new ArrayList<Review>();
                    for (int i = 0; i < past_review_ids.size(); i++) {
                        return_past_review.add(ReviewGatewayInterface.getReview(past_reviews_elements[i]));
                    }

                    String[] liked_reviews_elements = user[3].split("|");
                    ArrayList<String> return_liked_reviews = new ArrayList(
                            Arrays.asList( liked_reviews_elements ) );

                    Boolean return_banned = false;
                    if (Integer.parseInt(user[5]) == 1) {
                        return_banned = true;
                    }

                    User return_user = new User(user[0], user[1], return_past_review, return_liked_reviews,
                            Integer.parseInt(user[4]), return_banned);

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
    public Boolean userExists(String username) {
        try {
            File file = new File(NAME_OF_USER_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ",";
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
            String delimiter = ",";
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
            writer.append(",");
            writer.append(password); // String password
            writer.append(",");
            writer.append(""); // ArrayList<Review> past_reviews
            writer.append(",");
            writer.append(""); // ArrayList<String> likedReviews
            writer.append(",");
            writer.append("0"); // int received_reports = 0;
            writer.append(",");
            writer.append("0"); // boolean banned -> 0 - false, 1 - true
            writer.append(",");
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void banUser(String username) {

    }

    @Override
    public void addReview(String reviewId) {

    }

    @Override
    public void addLikedReview(String reviewId) {

    }

}
