package Gateways;

import entities.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ReviewGateway implements Interfaces.ReviewGatewayInterface {

    private static final String NAME_OF_REVIEW_ID_COUNTER = "src/main/java/Databases/ReviewIDCounter.txt";
    private static final String NAME_OF_REVIEW_DATABASE = "src/main/java/Databases/ReviewDatabase.csv";
    private static final int ID_INDEX = 0;
    private static final int STARS_INDEX = 1;
    private static final int TEXT_INDEX = 2;
    private static final int USERNAME_INDEX = 3;
    private static final int ADDRESS_INDEX = 4;
    private static final int LIKES_INDEX = 5;
    private static final int RESPONSE_INDEX = 6;
    private static final int REPORTS_INDEX = 7;
    private static final int VISIBLE_INDEX = 8;
    @Override
    public String loadReviewID() {
        try {
            File file = new File(NAME_OF_REVIEW_ID_COUNTER);
            Scanner scanner = new Scanner(file);
            String ID = scanner.nextLine();
            scanner.close();
            return ID;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "0";
        }
    }

    @Override
    public void incrementReviewID() {
        int currentID = Integer.parseInt(this.loadReviewID());
        currentID = currentID + 1;
        String stringID = Integer.toString(currentID);

        try {
            File file = new File(NAME_OF_REVIEW_ID_COUNTER);
            FileWriter writer = new FileWriter(file);
            writer.write(stringID);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public Review getReview(String id) {
        try{
            File file = new File(NAME_OF_REVIEW_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ",";
            String line;

            while(scanner.hasNext()){
                line = scanner.nextLine();
                String[] pieces = line.split(delimiter);

                if(pieces[ID_INDEX].equals(id)){
                    int stars = Integer.parseInt(pieces[STARS_INDEX]);
                    String text = pieces[TEXT_INDEX];
                    String username = pieces[USERNAME_INDEX];
                    String restaurantAddress = pieces[ADDRESS_INDEX];
                    int likes = Integer.parseInt(pieces[LIKES_INDEX]);
                    String response = pieces[RESPONSE_INDEX];
                    int reports = Integer.parseInt(pieces[REPORTS_INDEX]);
                    boolean visible = Boolean.getBoolean(pieces[VISIBLE_INDEX]);

                    return new Review(id, stars, text, username, restaurantAddress, likes, response, reports, visible);
                }

            }
            return null;
        }catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Review> getReviews(ArrayList<String> ids) {
        try{
            ArrayList<Review> reviews = new ArrayList<>();
            int length = ids.size();
            int counter = 0;
            File file = new File(NAME_OF_REVIEW_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ",";
            String line;

            while(scanner.hasNext()){
                line = scanner.nextLine();
                String[] pieces = line.split(delimiter);

                if(ids.contains(pieces[ID_INDEX])){
                    String id = pieces[ID_INDEX];
                    int stars = Integer.parseInt(pieces[STARS_INDEX]);
                    String text = pieces[TEXT_INDEX];
                    String username = pieces[USERNAME_INDEX];
                    String restaurantAddress = pieces[ADDRESS_INDEX];
                    int likes = Integer.parseInt(pieces[LIKES_INDEX]);
                    String response = pieces[RESPONSE_INDEX];
                    int reports = Integer.parseInt(pieces[REPORTS_INDEX]);
                    boolean visible = Boolean.getBoolean(pieces[VISIBLE_INDEX]);

                    Review review = new Review(id, stars, text, username, restaurantAddress, likes,
                                                response, reports, visible);
                    reviews.add(review);
                    counter = counter + 1;

                    if(counter == length){
                        return reviews;
                    }
                }

            }
            return reviews;
        }catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addReview(String id, String stars, String text, String username, String restaurantAddress,
                          String likes, String response, String reports, String visible) {
        try {
            File file = new File(NAME_OF_REVIEW_DATABASE);
            FileWriter writer = new FileWriter(file, true);
            //This looks disgusting, but it there is just a lot of instance attributes to add to the database
            writer.append(id);
            writer.append(",");
            writer.append(stars);
            writer.append(",");
            writer.append(text);
            writer.append(",");
            writer.append(username);
            writer.append(",");
            writer.append(restaurantAddress);
            writer.append(",");
            writer.append(likes);
            writer.append(",");
            writer.append(response);
            writer.append(",");
            writer.append(reports);
            writer.append(",");
            writer.append(visible);
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void deleteReview(String id){
        try{
            File file = new File(NAME_OF_REVIEW_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ",";
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while(scanner.hasNext()){
                line = scanner.nextLine();
                String[] pieces = line.split(delimiter);

                if(!pieces[0].equals(id)) {
                    lines.add(line);
                }
            }
            FileWriter clearCSV = new FileWriter(file, false);
            clearCSV.append("");
            clearCSV.close();

            FileWriter actualWriter = new FileWriter(file, true);

            for(String row : lines){
                actualWriter.append(row);
                actualWriter.append("\n");
            }
            actualWriter.close();
        }catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //This method is used to update the review database when a review object is manipulated
    public void updateReview(Review review) {

        String review_id = review.getID();
        String new_STARS = String.valueOf(review.getStars());
        String new_text = String.valueOf(review.getText());
        String new_username = String.valueOf(review.getUser());
        String new_address = String.valueOf(review.getRestaurant());
        String new_likes = String.valueOf(review.getLikes());
        String new_response = String.valueOf(review.getResponse());
        String new_reports = String.valueOf(review.getReports());
        String new_visibility = String.valueOf(review.isVisible());

        String tempFile = "src/main/java/Databases/temp_ReviewDatabase.csv";

        File oldfile = new File(NAME_OF_REVIEW_DATABASE);
        File newfile = new File(tempFile);

        String id = "";
        String stars = "";
        String text = "";
        String username = "";
        String restaurantAddress = "";
        String likes = "";
        String response = "";
        String reports = "";
        String visible = "";

        try {
            FileWriter fw = new FileWriter(newfile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            Scanner scanner = new Scanner(new File(NAME_OF_REVIEW_DATABASE));
            String delimiter = ",";
            String line;

            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String[] values = line.split(delimiter);

                id = values[0];
                stars = values[1];
                text = values[2];
                username = values[3];
                restaurantAddress = values[4];
                likes = values[5];
                response = values[6];
                reports = values[7];
                visible = values[8];

                // replace the old values with the new values
                if (id.equals(review_id)) {
                    String line1 = String.join(",", review_id, new_STARS, new_text, new_username,
                            new_address, new_likes, new_response, new_reports, new_visibility);
                    bw.write(line1);
                    bw.newLine();

                    //keep the old values
                } else {
                    String line1 = String.join(",", id, stars, text, username,
                            restaurantAddress, likes, response, reports, visible);
                    bw.write(line1);
                    bw.newLine();
                }

            }

            scanner.close();
            bw.flush();
            bw.close();
            oldfile.delete();
            File temp = new File(NAME_OF_REVIEW_DATABASE);
            newfile.renameTo(temp);



        } catch (Exception e) {
            System.out.println("Can't find this Review");
            e.printStackTrace();
        }

    }


}
