package review_tests.automated_tests.interactor_tests;

import entities.Restaurant;
import entities.Review;
import entities.ReviewFactory;
import entities.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import restaurant_feature.gateways.FileRestaurant;
import review_feature.gateways.ReviewGateway;
import review_feature.gateways.ReviewNotFoundException;
import review_feature.interactors.WriteReviewInteractor;
import review_feature.interactors.WriteReviewRequestModel;
import review_feature.screens.ReviewResponseModel;
import user_feature.gateways.UserGateway;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class TestWriteInteractor {

    private final static String NAME_OF_TEST_REVIEW_ID_COUNTER =
            "src/test/java/review_tests/TestReviewIDCounterDatabase.txt";
    private final static String NAME_OF_TEST_REVIEW_DATABASE = "src/test/java/review_tests/TestReviewDatabase.csv";
    private final static ReviewGateway REVIEW_GATEWAY = new ReviewGateway(NAME_OF_TEST_REVIEW_ID_COUNTER,
            NAME_OF_TEST_REVIEW_DATABASE);
    private final static UserGateway USER_GATEWAY = new UserGateway();
    private final static FileRestaurant RESTAURANT_GATEWAY;

    static {
        try {
            RESTAURANT_GATEWAY = new FileRestaurant("src/test/java/review_tests/TestRestaurantDatabaseForReviews.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final String TEST_REVIEW_STRING_1 = String.join(ReviewGateway.getDelimiter(), "1", "5",
            "test text 1", "test user 1", "test location 1", "10", "test response 1", "3", "true");
    private final String TEST_REVIEW_STRING_2 = String.join(ReviewGateway.getDelimiter(), "2", "3",
            "test text 2", "test user 2", "test location 2", "5", "test response 2", "1", "true");
    private final Review TEST_REVIEW_1 = new ReviewFactory().reinitialize("1", 5, "test text 1",
            "test user 1", "test location 1", 10, "test response 1",
            3, true);
    private final String TEST_ID = "3";
    private final User USER = USER_GATEWAY.getUser("TestUser");
    private final Restaurant RESTAURANT = RESTAURANT_GATEWAY.retrieveRestaurant("Z1B 2C8");

    @Before
    public void clearReviewDatabases(){
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

    //@After
    public void tearDown(){
        //Remove the newly written review from these objects
        Review review = null;
        try {
            review = REVIEW_GATEWAY.getReview(TEST_ID);
        } catch (FileNotFoundException | ReviewNotFoundException e) {
            throw new RuntimeException(e);
        }
        USER.getPast_reviews().remove(TEST_ID);
        RESTAURANT.removeReview(review);

        //Update their databases
        USER_GATEWAY.updateUser(USER);
        RESTAURANT_GATEWAY.deleteRestaurant(RESTAURANT.getLocation());
        RESTAURANT_GATEWAY.save(RESTAURANT);
    }

    /*
    Test to see if the interactor returns success
     */
    @Test
    public void testInteractorReturnsSuccess(){
        clearReviewDatabases();
        WriteReviewRequestModel requestModel = new WriteReviewRequestModel(REVIEW_GATEWAY, USER_GATEWAY,
                RESTAURANT_GATEWAY, USER, RESTAURANT, 5, "Interactor Test text");
        WriteReviewInteractor writeReviewInteractor = new WriteReviewInteractor();
        ReviewResponseModel responseModel = writeReviewInteractor.interact(requestModel);
        tearDown();
        assert responseModel.wasSuccessful();
    }

    /*
    Test to see if the interactor adds the review to the database
     */
    @Test
    public void testInteractorAddsReview(){
        try {
            clearReviewDatabases();
            WriteReviewRequestModel requestModel = new WriteReviewRequestModel(REVIEW_GATEWAY, USER_GATEWAY,
                    RESTAURANT_GATEWAY, USER, RESTAURANT, 5, "Interactor Test text");
            WriteReviewInteractor writeReviewInteractor = new WriteReviewInteractor();
            writeReviewInteractor.interact(requestModel);
            Review review = REVIEW_GATEWAY.getReview(TEST_ID);
            tearDown();
            assert (review.getID().equals(TEST_ID) && review.getStars() == 5 &&
                    review.getText().equals("Interactor Test text"));
        }catch(IOException | ReviewNotFoundException e1){
            fail("This should never be reached");
        }
    }
}
