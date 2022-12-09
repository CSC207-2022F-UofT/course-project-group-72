package review_tests.automated_tests.interactor_tests;

import entities.Restaurant;
import entities.Review;
import entities.ReviewFactory;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import restaurant_use_case.interactors.FileRestaurant;
import review_use_case.gateways.ReviewGateway;
import review_use_case.interactors.EditReviewInteractor;
import review_use_case.interactors.EditReviewRequestModel;
import review_use_case.screens.ReviewResponseModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class TestEditInteractor {

    private final static String NAME_OF_TEST_REVIEW_ID_COUNTER =
            "src/test/java/review_tests/TestReviewIDCounterDatabase.txt";
    private final static String NAME_OF_TEST_REVIEW_DATABASE = "src/test/java/review_tests/TestReviewDatabase.csv";
    private final static ReviewGateway REVIEW_GATEWAY = new ReviewGateway(NAME_OF_TEST_REVIEW_ID_COUNTER,
            NAME_OF_TEST_REVIEW_DATABASE);
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

    /*
    Test to see if the interactor returns success, which it should
     */
    @Test
    public void testInteractorReturnsSuccess(){
        clearReviewDatabases();
        EditReviewRequestModel requestModel = new EditReviewRequestModel(REVIEW_GATEWAY,
                RESTAURANT_GATEWAY, TEST_REVIEW_1, RESTAURANT, 1, "Interactor Test text");
        EditReviewInteractor editReviewInteractor = new EditReviewInteractor();
        ReviewResponseModel responseModel = editReviewInteractor.interact(requestModel);
        assert responseModel.wasSuccessful();
    }

    /*
    Test to see if the interactor actually edits a review
     */
    @Test
    public void testInteractorEdits(){
        clearReviewDatabases();
        EditReviewRequestModel requestModel = new EditReviewRequestModel(REVIEW_GATEWAY,
                RESTAURANT_GATEWAY, TEST_REVIEW_1, RESTAURANT, 1, "Interactor Test text");
        EditReviewInteractor editReviewInteractor = new EditReviewInteractor();
        editReviewInteractor.interact(requestModel);
        assert (TEST_REVIEW_1.getStars() == 1 && TEST_REVIEW_1.getText().equals("Interactor Test text"));
    }
}
