//Class responsible for adding and retrieving reviews from the review database

package review_use_case.gateways;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import review_use_case.interfaces.ReviewGatewayInterface;
import entities.Review;

public class ReviewGateway implements ReviewGatewayInterface {

    private static final String NAME_OF_REVIEW_ID_COUNTER = "src/main/java/Databases/ReviewIDCounter.txt";
    private static final String NAME_OF_REVIEW_DATABASE = "src/main/java/Databases/ReviewDatabase.csv";
    private static final String DELIMITER = ReviewGatewayInterface.DELIMITER;
    private static final int ID_INDEX = 0;
    private static final int STARS_INDEX = 1;
    private static final int TEXT_INDEX = 2;
    private static final int USERNAME_INDEX = 3;
    private static final int LOCATION_INDEX = 4;
    private static final int LIKES_INDEX = 5;
    private static final int RESPONSE_INDEX = 6;
    private static final int REPORTS_INDEX = 7;
    private static final int VISIBLE_INDEX = 8;

    /*
    Retrieve the id that should be used when making a new review
     */
    @Override
    public String loadReviewID() throws FileNotFoundException {
        //Try to access the database and retrieve the string id. If the ID is empty for some reason, return 0
        File file = new File(NAME_OF_REVIEW_ID_COUNTER);
        Scanner scanner = new Scanner(file);
        String ID = scanner.nextLine();
        scanner.close();
        if(ID.equals("")){
            return "0";
        }
        else{
            return ID;
        }
    }

    /*
    After using the current ID to make a new review, increase it by 1
     */
    @Override
    public void incrementReviewID() throws IOException {
        //Get current ID and increment it by 1
        int currentID = Integer.parseInt(this.loadReviewID());
        currentID = currentID + 1;
        String stringID = Integer.toString(currentID);

        //Try to write this to the database
        File file = new File(NAME_OF_REVIEW_ID_COUNTER);
        FileWriter writer = new FileWriter(file);
        writer.write(stringID);
        writer.close();
    }

    /*
    Method to retrieve a single Review using its ID
     */
    @Override
    public Review getReview(String id) throws FileNotFoundException, ReviewNotFoundException {
        //Attach a scanner to the database and declare a variable to hold one line from the database
        File file = new File(NAME_OF_REVIEW_DATABASE);
        Scanner scanner = new Scanner(file);
        String line;

        //While there are lines in the database
        while(scanner.hasNext()){
            //Get each line and split it into pieces by the delimiter
            line = scanner.nextLine();
            String[] pieces = line.split(DELIMITER);

            //If the piece where ID is stored equals the provided ID, then return a Review made from this line
            if(pieces[ID_INDEX].equals(id)){
                return makeReviewObject(pieces);
            }

        }
        //No reviews matching this ID are found, so throw ReviewNotFoundException
        throw new ReviewNotFoundException("No reviews were found matching that id.");
    }

    /*
    Return a list of Reviews based on a list of IDs. Exact same as retrieving a single review, except we iterate until
    ids.length reviews are added to the list of Reviews to return, or we run out of lines
     */
    @Override
    public ArrayList<Review> getReviews(ArrayList<String> ids) throws FileNotFoundException {
        ArrayList<Review> reviews = new ArrayList<>();
        int length = ids.size();
        int counter = 0;
        File file = new File(NAME_OF_REVIEW_DATABASE);
        Scanner scanner = new Scanner(file);
        String line;

        while(scanner.hasNext()){
            line = scanner.nextLine();
            String[] pieces = line.split(DELIMITER);

            if(ids.contains(pieces[ID_INDEX])){
                Review review = makeReviewObject(pieces);
                reviews.add(review);
                counter = counter + 1;

                if(counter == length){
                    return reviews;
                }
            }

        }
        return reviews;
    }

    /*
    Add a Review to the database using the nine attributes
     */
    @Override
    public void addReview(Review review) throws IOException {
        //Try to get the file, make a line from the attributes and add that to the end of the database
        File file = new File(NAME_OF_REVIEW_DATABASE);
        FileWriter writer = new FileWriter(file, true);
        String id = review.getID();
        String stars = Integer.toString(review.getStars());
        String text = review.getText();
        String username = review.getUser();
        String restaurantLocation = review.getRestaurant();
        String likes = Integer.toString(review.getLikes());
        String response = review.getResponse();
        String reports = Integer.toString(review.getReports());
        String visible = Boolean.toString(review.isVisible());
        String line = String.join(DELIMITER, id, stars, text, username, restaurantLocation, likes,
                response, reports, visible);
        writer.append(line);
        writer.append("\n");
        writer.close();
    }

    /*
    Method to update a line in the database based off of a review object
     */
    public void updateReview(Review review) throws IOException {
        //Get the new attributes of the review
        String id = review.getID();
        String newStars = Integer.toString(review.getStars());
        String newText = review.getText();
        String newUsername = review.getUser();
        String newLocation = review.getRestaurant();
        String newLikes = Integer.toString(review.getLikes());
        String newResponse = review.getResponse();
        String newReports = Integer.toString(review.getReports());
        String newVisible = Boolean.toString(review.isVisible());

        //Try to find the database, initialize a variable to hold a single line and another to hold all the lines
        //retrieved so far
        File file = new File(NAME_OF_REVIEW_DATABASE);
        Scanner scanner = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        String line;

        //While the database has more lines in it
        while(scanner.hasNext()){
            //Get the line and split it by the delimiter
            line = scanner.nextLine();
            String[] pieces = line.split(DELIMITER);

            //If this line does not have the ID of the review being deleted, add it as is
            if(!pieces[ID_INDEX].equals(id)) {
                lines.add(line);
            }
            //Otherwise, change the line to reflect the new attributes and then add it
            else{
                pieces[STARS_INDEX] = newStars;
                pieces[TEXT_INDEX] = newText;
                pieces[USERNAME_INDEX] = newUsername;
                pieces[LOCATION_INDEX] = newLocation;
                pieces[LIKES_INDEX] = newLikes;
                pieces[RESPONSE_INDEX] = newResponse;
                pieces[REPORTS_INDEX] = newReports;
                pieces[VISIBLE_INDEX] = newVisible;
                lines.add(makeNewLine(pieces));
            }
        }
        //Empty the old database
        FileWriter clearCSV = new FileWriter(file, false);
        clearCSV.append("");
        clearCSV.close();

        //Remake the database using the lines constructed in the while loop
        FileWriter actualWriter = new FileWriter(file, true);

        for(String row : lines){
            actualWriter.append(row);
            actualWriter.append("\n");
        }
        actualWriter.close();
    }

    /*
    Method to remove a review from the review database
     */
    public void deleteReview(String id) throws IOException {
        //Try to find the database, initialize a variable to hold a single line and another to hold all the lines
        //retrieved so far
        File file = new File(NAME_OF_REVIEW_DATABASE);
        Scanner scanner = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        String line;

        //While the database has more lines in it
        while(scanner.hasNext()){
            //Get the line and split it by the delimiter
            line = scanner.nextLine();
            String[] pieces = line.split(DELIMITER);

            //If this line does not have the ID of the review being deleted, add it back to the database
            if(!pieces[ID_INDEX].equals(id)) {
                lines.add(line);
            }
        }
        //Empty the old database
        FileWriter clearCSV = new FileWriter(file, false);
        clearCSV.append("");
        clearCSV.close();

        //Remake the database using the lines constructed in the while loop
        FileWriter actualWriter = new FileWriter(file, true);

        for(String row : lines){
            actualWriter.append(row);
            actualWriter.append("\n");
        }
        actualWriter.close();
    }

    /*
    Helper method to make a review out of the nine attributes
     */
    private Review makeReviewObject(String[] pieces){
        String id = pieces[ID_INDEX];
        int stars = Integer.parseInt(pieces[STARS_INDEX]);
        String text = pieces[TEXT_INDEX];
        String username = pieces[USERNAME_INDEX];
        String restaurantLocation = pieces[LOCATION_INDEX];
        int likes = Integer.parseInt(pieces[LIKES_INDEX]);
        String response = pieces[RESPONSE_INDEX];
        int reports = Integer.parseInt(pieces[REPORTS_INDEX]);
        boolean visible = Boolean.getBoolean(pieces[VISIBLE_INDEX]);

        return new Review(id, stars, text, username, restaurantLocation, likes, response, reports, visible);
    }

    /*
    Make new line from an array of strings
     */
    private String makeNewLine(String[] pieces){
        return String.join(DELIMITER, pieces[ID_INDEX], pieces[STARS_INDEX], pieces[TEXT_INDEX], pieces[USERNAME_INDEX],
                pieces[LOCATION_INDEX], pieces[LIKES_INDEX], pieces[RESPONSE_INDEX], pieces[REPORTS_INDEX],
                pieces[VISIBLE_INDEX]);
    }

    /*
    Return the gateway's delimiter so screens can prevent people from using it in their reviews
     */
    public static String getDelimiter(){
        return DELIMITER;
    }
}
