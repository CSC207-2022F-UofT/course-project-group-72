package report_screens;


import entities.Review;
import java.io.*;
import java.util.Scanner;


//This class is used to update the database with the manipulated review object
public class ManipulateReviewDs {

    private static final String NAME_OF_REVIEW_DATABASE = "src/main/java/Databases/ReviewDatabase.csv";


    public void updateReview(Review review) throws IOException {

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

