package review_tests.automated_tests.gatewaytests;

import entities.Review;
import entities.ReviewFactory;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import review_use_case.gateways.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestReviewGateway {

    private final static String NAME_OF_TEST_REVIEW_ID_COUNTER =
            "src/test/java/review_tests/TestReviewIDCounterDatabase.txt";
    private final static String NAME_OF_TEST_REVIEW_DATABASE = "src/test/java/review_tests/TestReviewDatabase.csv";
    private final static ReviewGateway REVIEW_GATEWAY = new ReviewGateway(NAME_OF_TEST_REVIEW_ID_COUNTER,
            NAME_OF_TEST_REVIEW_DATABASE);
    private final String TEST_REVIEW_STRING_1 = String.join(ReviewGateway.getDelimiter(), "1", "5",
            "test text 1", "test user 1", "test location 1", "10", "test response 1", "3", "true");
    private final String TEST_REVIEW_STRING_2 = String.join(ReviewGateway.getDelimiter(), "2", "3",
            "test text 2", "test user 2", "test location 2", "5", "test response 2", "1", "true");
    private final Review TEST_REVIEW_1 = new ReviewFactory().reinitialize("1", 5, "test text 1",
            "test user 1", "test location 1", 10, "test response 1",
            3, true);
    private final Review TEST_REVIEW_2 = new ReviewFactory().reinitialize("2", 3, "test text 2",
            "test user 2", "test location 2", 5, "test response 2",
            1, true);
    private final String TEST_ID = "3";

    /*
    Wipe the review database and replace with a default review, then reset the ID counter to 3. The ID is reset to 3
    since 0 is a default value for blank a blank file. we want to be sure that the current value is being returned,
    not just the default
     */
    @Before
    public void clearDatabases(){
        try {
            TEST_REVIEW_1.setStars(5);
            TEST_REVIEW_1.setText("test text 1");

            File file1 = new File(NAME_OF_TEST_REVIEW_DATABASE);
            FileWriter clearCSV = new FileWriter(file1, false);
            clearCSV.append(TEST_REVIEW_STRING_1).append("\n").append(TEST_REVIEW_STRING_2).append("\n");
            clearCSV.close();

            File file2 = new File(NAME_OF_TEST_REVIEW_ID_COUNTER);
            FileWriter clearTXT = new FileWriter(file2);
            clearTXT.write(TEST_ID);
            clearTXT.close();
        }catch(FileNotFoundException e1){
            fail("A FileNotFoundException caused this method to fail. This should never be reached.");
        }catch(IOException e2){
            fail("An IOException caused this test to fail. This should never be reached.");
        }

    }

    /*
    Test that loadReviewID returns the correct ID
     */
    @Test
    public void testLoadReviewID(){
        clearDatabases();
        try {
            String actual = REVIEW_GATEWAY.loadReviewID();
            String expected = "3";
            assert(actual.equals(expected));
        }catch(IOException e){
            fail("A FileNotFoundException caused this test to fail. This should never be reached.");
        }
    }

    /*
    Test that incrementReviewID method correctly increments the ID
     */
    @Test
    public void testIncrementReviewID(){
        clearDatabases();
        try {
            REVIEW_GATEWAY.incrementReviewID();
            String actual = REVIEW_GATEWAY.loadReviewID();
            String expected = "4";
            assert(actual.equals(expected));
        }catch(IOException e){
            fail("An IOException caused this test to fail. This should never be reached.");
        }
    }

    /*
    Test that getReview returns the correct review
     */
    @Test
    public void testGetReview(){
        clearDatabases();
        try {
            Review actualReview = REVIEW_GATEWAY.getReview("1");
            assert(reviewsMatch(TEST_REVIEW_1, actualReview));
        }catch(IOException e1){
            fail("An IOException caused this test to fail. This should never be reached.");
        }catch(ReviewNotFoundException e2){
            fail("This review ID was not found in the test database. Double check the constants " +
                    "at the top of the file.");
        }
    }

    /*
    Test that getReviews returns an ArrayList containing the right reviews
     */
    @Test
    public void testGetReviews(){
        clearDatabases();
        try {
            ArrayList<String> ids = new ArrayList<>();
            ids.add("1");
            ids.add("2");
            ArrayList<Review> actualReviews = REVIEW_GATEWAY.getReviews(ids);
            assert(reviewsMatch(TEST_REVIEW_1, actualReviews.get(0)) &&
                    reviewsMatch(TEST_REVIEW_2, actualReviews.get(1)));
        }catch(IOException e){
            fail("An IOException caused this test to fail. This should never be reached.");
        }
    }

    /*
    Test that addReview actually adds a review to the database
     */
    @Test
    public void testAddReview(){
        clearDatabases();
        String newID = "5";
        int newStars = 1;
        String newText = "new text";
        String newUser = "new username";
        String newLocation = "new location";
        Review newReview = new ReviewFactory().create(newID, newStars, newText, newUser, newLocation);

        try{
            REVIEW_GATEWAY.addReview(newReview);
            Review returnedReview = REVIEW_GATEWAY.getReview(newID);
            assert(reviewsMatch(newReview, returnedReview));
        }catch(IOException e1){
            fail("An IOException caused this test to fail. This should never be reached.");
        }catch(ReviewNotFoundException e2){
            fail("This review was not found in the database. If testGetReview works, addReview is the issue");
        }
    }

    /*
    Test whether updateReview updates the review properly
     */
    @Test
    public void testUpdateReview(){
        clearDatabases();
        int newStars = 2;
        String newText = "new text";
        TEST_REVIEW_1.setStars(newStars);
        TEST_REVIEW_1.setText(newText);

        try{
            REVIEW_GATEWAY.updateReview(TEST_REVIEW_1);
            Review returnedReview = REVIEW_GATEWAY.getReview(TEST_REVIEW_1.getID());
            assert(returnedReview.getStars() == newStars && returnedReview.getText().equals(newText));
        }catch(IOException e1){
            fail("An IOException caused this test to fail. This should never be reached.");
        }catch(ReviewNotFoundException e2){
            fail("This review was not found in the database. If testAddReview passes, this should not be reached");
        }
    }

    /*
    Test whether deleteReview actually removes a review from the database
     */
    @Test
    public void testDeleteReview(){
        clearDatabases();
        try{
            REVIEW_GATEWAY.deleteReview(TEST_REVIEW_2.getID());
            REVIEW_GATEWAY.getReview(TEST_REVIEW_2.getID());
            fail("getReview did not throw a ReviewNotFoundException, so the review was not removed from th database");
        }catch(IOException e1){
            fail("An IOException caused this test to fail. This should never be reached.");
        }catch(ReviewNotFoundException e2){
            return;
        }
    }

    /*
    Return whether all attributes of two reviews match, i.e. whether they are the same review
     */
    private boolean reviewsMatch(Review expectedReview, Review actualReview){
        boolean check = true;

        if(!(expectedReview.getID().equals(actualReview.getID()))){
            check = false;
        }else if(!(expectedReview.getStars() == (actualReview.getStars()))){
            check = false;
        }else if(!(expectedReview.getText().equals(actualReview.getText()))){
            check = false;
        }else if(!(expectedReview.getUser().equals(actualReview.getUser()))){
            check = false;
        }else if(!(expectedReview.getRestaurant().equals(actualReview.getRestaurant()))){
            check = false;
        }else if(!(expectedReview.getLikes() == (actualReview.getLikes()))){
            check = false;
        }else if(!(expectedReview.getResponse().equals(actualReview.getResponse()))){
            check = false;
        }else if(!(expectedReview.getReports() == (actualReview.getReports()))){
            check = false;
        }else if(!(expectedReview.isVisible() == (actualReview.isVisible()))){
            check = false;
        }

        return check;
    }
}
