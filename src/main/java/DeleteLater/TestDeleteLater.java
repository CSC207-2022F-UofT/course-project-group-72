package DeleteLater;

import entities.Review;
import org.junit.Test;

import java.util.ArrayList;

public class TestDeleteLater {

    @Test
    public void testLoadReviewID(){
        Gateways.ReviewGateway gateway = new Gateways.ReviewGateway();
        String expected = "0";
        String actual = gateway.loadReviewID();
        assert actual.equals(expected);
    }

    @Test
    public void testIncrementReviewID(){
        Gateways.ReviewGateway gateway = new Gateways.ReviewGateway();
        String expected = "1";
        gateway.incrementReviewID();
        String actual = gateway.loadReviewID();
        assert actual.equals(expected);
    }

    @Test
    public void testAddReview(){
        Gateways.ReviewGateway gateway = new Gateways.ReviewGateway();
        gateway.addReview("0", "0", "test text 0", "test user 0", "test address",
                "0", "test response 0", "0", "true");
        gateway.addReview("1", "1", "test text 1", "test user 1", "test address",
                "0", "test response 1", "0", "true");
        gateway.addReview("2", "2", "test text 2", "test user 2", "test address",
                "0", "test response 2", "0", "true");
    }

    @Test
    public void testGetReview(){
        Gateways.ReviewGateway gateway = new Gateways.ReviewGateway();
        gateway.getReview("2");
    }

    @Test
    public void testGetReviews(){
        Gateways.ReviewGateway gateway = new Gateways.ReviewGateway();
        ArrayList<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        ids.add("4");
        ArrayList<entities.Review> reviews = gateway.getReviews(ids);
    }

    @Test
    public void testDeleteReview(){
        Gateways.ReviewGateway gateway = new Gateways.ReviewGateway();
        gateway.deleteReview("1");
    }
}
