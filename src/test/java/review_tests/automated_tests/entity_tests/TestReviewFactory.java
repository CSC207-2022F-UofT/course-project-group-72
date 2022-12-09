package review_tests.automated_tests.entity_tests;

import entities.Review;
import entities.ReviewFactory;
import org.junit.jupiter.api.Test;

public class TestReviewFactory {

    /**
     * Test that ReviewFactory correctly creates a review
     */
    @Test
    public void testCreate(){
        ReviewFactory factory = new ReviewFactory();
        Review review = factory.create("0", 5, "Factory Test Text",
                "Factory Test Username", "Factory Test Location");
        assert(
                review.getID().equals("0") &&
                        review.getStars() == 5 &&
                        review.getText().equals("Factory Test Text") &&
                        review.getUser().equals("Factory Test Username") &&
                        review.getRestaurant().equals("Factory Test Location") &&
                        review.getLikes() == 0 &&
                        review.getResponse().equals("") &&
                        review.getReports() == 0 &&
                        review.isVisible()
                );
    }

    /**
     * Test that ReviewFactory correctly reinitializes a review
     */
    @Test
    public void testReinitialize(){
        ReviewFactory factory = new ReviewFactory();
        Review review = factory.reinitialize("0", 5, "Factory Test Text",
                "Factory Test Username", "Factory Test Location", 20,
                "Factory Test Response", 15, false);
        assert(
                review.getID().equals("0") &&
                        review.getStars() == 5 &&
                        review.getText().equals("Factory Test Text") &&
                        review.getUser().equals("Factory Test Username") &&
                        review.getRestaurant().equals("Factory Test Location") &&
                        review.getLikes() == 20 &&
                        review.getResponse().equals("Factory Test Response") &&
                        review.getReports() == 15 &&
                        !review.isVisible()
        );
    }
}
