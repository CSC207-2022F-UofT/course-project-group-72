//Interface that all review gateways must implement

package review_use_case.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import review_use_case.gateways.ReviewNotFoundException;
import entities.Review;

public interface ReviewGatewayInterface {
    //All implementations must use this delimiter
    String DELIMITER = "`";
    //Methods required to manipulate the database
    String loadReviewID() throws FileNotFoundException;

    void incrementReviewID() throws IOException;

    entities.Review getReview(String id) throws FileNotFoundException, ReviewNotFoundException;

    ArrayList<entities.Review> getReviews(ArrayList<String> ids) throws FileNotFoundException;

    void addReview(Review review) throws IOException;

    void updateReview(Review review) throws IOException;

    void deleteReview(String id) throws IOException;

    /*
    Default method for returning delimiter
     */
    static String getDelimiter(){return DELIMITER;}

}
