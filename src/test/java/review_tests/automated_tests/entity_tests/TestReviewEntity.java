package review_tests.automated_tests.entity_tests;

import entities.Review;
import entities.ReviewFactory;
import org.junit.jupiter.api.Test;

class TestReviewEntity {

    public Review makeNewReview(){
        return new ReviewFactory().reinitialize("0", 5, "Review Test Text",
                "Review Test Username", "Review Test Location", 10,
                "Review Test Response", 5, true);
    }

    @Test
    void testGetID() {
        Review review = makeNewReview();
        assert review.getID().equals("0");
    }

    @Test
    void testGetStars() {
        Review review = makeNewReview();
        assert review.getStars() == 5;
    }

    @Test
    void testSetStars() {
        Review review = makeNewReview();
        review.setStars(1);
        assert review.getStars() == 1;
    }

    @Test
    void testGetText() {
        Review review = makeNewReview();
        assert review.getText().equals("Review Test Text");
    }

    @Test
    void testSetText() {
        Review review = makeNewReview();
        review.setText("New Text");
        assert review.getText().equals("New Text");
    }

    @Test
    void testGetUser() {
        Review review = makeNewReview();
        assert review.getUser().equals("Review Test Username");
    }

    @Test
    void testSetUser() {
        Review review = makeNewReview();
        review.setUser("New Username");
        assert review.getUser().equals("New Username");
    }

    @Test
    void testGetRestaurant() {
        Review review = makeNewReview();
        assert review.getRestaurant().equals("Review Test Location");
    }

    @Test
    void testGetLikes() {
        Review review = makeNewReview();
        assert review.getLikes() == 10;
    }

    @Test
    void testIncrementLikes() {
        Review review = makeNewReview();
        review.incrementLikes();
        assert review.getLikes() == 11;
    }

    @Test
    void testDecrementLikes() {
        Review review = makeNewReview();
        review.decrementLikes();
        assert review.getLikes() == 9;
    }

    @Test
    void testGetResponse() {
        Review review = makeNewReview();
        assert review.getResponse().equals("Review Test Response");
    }

    @Test
    void testSetResponse() {
        Review review = makeNewReview();
        review.setResponse("New Response");
        assert review.getResponse().equals("New Response");
    }

    @Test
    void testGetReports() {
        Review review = makeNewReview();
        assert review.getReports() == 5;
    }

    @Test
    void testAddReport() {
        Review review = makeNewReview();
        review.addReport();
        assert review.getReports() == 6;
    }

    @Test
    void testIsVisible() {
        Review review = makeNewReview();
        assert review.isVisible();
    }

    @Test
    void testSetVisible() {
        Review review = makeNewReview();
        review.setVisible(false);
        assert !review.isVisible();
    }

    @Test
    void testToString() {
        Review review = makeNewReview();
        assert review.toString().equals(review.getID());
    }
}
