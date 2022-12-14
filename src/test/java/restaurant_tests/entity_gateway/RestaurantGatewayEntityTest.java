package restaurant_tests.entity_gateway;

import entities.Restaurant;
import entities.RestaurantFactory;
import entities.Review;
import entities.ReviewFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import restaurant_feature.interfaces.RestaurantDSGateway;
import restaurant_feature.gateways.FileRestaurant;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Tests the FileRestaurant database class and Restaurant entity class
 * Merged together since FileRestaurant returns a Restaurant
 */
public class RestaurantGatewayEntityTest {
    /**
     * The restaurant gateway responsible for the management of the temporary test database
     */
    final RestaurantDSGateway gateway = new FileRestaurant("src/test/java/restaurant_tests/temptest.csv");
    /**
     * The Restaurant factory
     */
    final RestaurantFactory factory = new RestaurantFactory();
    /**
     * The Array List of Restaurants used in the environment
     */
    ArrayList<Restaurant> testRestaurants = new ArrayList<>();

    public RestaurantGatewayEntityTest() throws IOException {
    }

    // BEFORE DOESN'T WORK FOR SOME REASON
    /**
     * Sets up the simulation environment to contain 2 restaurants and 2 review IDs
     */
    @Before
    public void setup() {
        Restaurant test1 = factory.create(
                "123",
                "timsstraunt",
                "1234 temp ave",
                "bbq",
                5);

        ArrayList<String> reviews = new ArrayList<String>();
        reviews.add("1");
        reviews.add("2");
        Restaurant test2 = factory.reinitialize(
                "000",
                "McDonalds",
                "45 fake st",
                "fast food",
                1,
                3.2,
                reviews);

        testRestaurants.add(test1);
        testRestaurants.add(test2);

        gateway.save(test1);
        gateway.save(test2);
    }
    /**
     * Test that the gateway and entity returns the correct Name
     */
    @Test
    public void testRetrieveRestaurantName() {
        String actual = gateway.retrieveRestaurant("1234 temp ave").getName();
        assertEquals("timsstraunt", actual);
    }

    /**
     * Test that the gateway and entity returns the correct OwnerID
     */
    @Test
    public void testRetrieveRestaurantOwnerID() {
        String actual = gateway.retrieveRestaurant("1234 temp ave").getOwnerID();
        assertEquals("123", actual);
    }

    /**
     * Test that the gateway and entity returns the correct Location
     */
    @Test
    public void testRetrieveRestaurantLocation() {
        String actual = gateway.retrieveRestaurant("1234 temp ave").getLocation();
        assertEquals("1234 temp ave", actual);
    }

    /**
     * Test that the gateway and entity returns the correct CuisineType
     */
    @Test
    public void testRetrieveRestaurantCuisineType() {
        String actual = gateway.retrieveRestaurant("1234 temp ave").getCuisineType();
        assertEquals("bbq", actual);
    }

    /**
     * Test that the gateway and entity returns the correct PriceBucket
     */
    @Test
    public void testRetrieveRestaurantPriceBucket() {
        int actual = gateway.retrieveRestaurant("1234 temp ave").getPriceBucket();
        assertEquals(5, actual);
    }

    /**
     * Test that the gateway and entity returns the correct Reviews
     */
    @Test
    public void testRetrieveRestaurantReviews() {
        ArrayList<String> actual = gateway.retrieveRestaurant("45 fake st").getReviewIDs();
        assertTrue(actual.contains("1") && actual.contains("2"));
    }

    /**
     * Test that the gateway and entity returns the correct AverageStars
     */
    @Test
    public void testRetrieveRestaurantAvgStars() {
        double actual = gateway.retrieveRestaurant("45 fake st").getAvgStars();
        assertEquals(3.2, actual, 0.000000001d);
    }

    /**
     * Test that the Restaurant correctly sets restaurant name
     */
    @Test
    public void testSetRestaurantName() {
        setup();
        Restaurant test = testRestaurants.get(0);
        test.setName("newname");
        assertEquals("newname", test.getName());
    }

    /**
     * Test that the Restaurant correctly sets the ownerID
     */
    @Test
    public void testSetRestaurantOwner() {
        setup();
        Restaurant test = testRestaurants.get(0);
        test.setOwnerID("newid");
        assertEquals("newid", test.getOwnerID());
    }

    /**
     * Test that the Restaurant sets the correct cuisineType
     */
    @Test
    public void testSetRestaurantCuisine() {
        setup();
        Restaurant test = testRestaurants.get(0);
        test.setCuisineType("newcuisine");
        assertEquals("newcuisine", test.getCuisineType());
    }

    /**
     * Test that the Restaurant sets the correct priceBucket
     */
    @Test
    public void testSetRestaurantPrice() {
        setup();
        Restaurant test = testRestaurants.get(0);
        test.setPriceBucket(4);
        assertEquals(4, test.getPriceBucket());
    }

    /**
     * Test that the Restaurant correctly adds the Review and
     * RestaurantAttributes increments the Average Stars
     */
    @Test
    public void testAddReview() {
        setup();
        Review testReview = new ReviewFactory().create("0", 2, "",
                "00", "invalid but not tested in restaurant");
        Restaurant testRestaurant1 = testRestaurants.get(0);
        testRestaurant1.addReview(testReview);

        assertEquals("0", testRestaurant1.getReviewIDs().get(0));
        assertEquals(2, testRestaurant1.getAvgStars(), 0.000001d);

        Restaurant testRestaurant2 = testRestaurants.get(1);
        assertEquals(3.2, testRestaurant2.getAvgStars(), 0.000001d);
        testRestaurant2.addReview(testReview);
        double expected = (3.2 * 2 + 2)/3;

        assertEquals(expected, testRestaurant2.getAvgStars(), 0.000001d);
    }

    /**
     * Test that the Restaurant correctly removes review
     */
    @Test
    public void testRemoveReview() {
        setup();
        Review testReview = new ReviewFactory().create("1", 2, "",
                "00", "invalid but not tested in restaurant");

        Restaurant testRestaurant2 = testRestaurants.get(1);
        assertEquals(3.2, testRestaurant2.getAvgStars(), 0.000001d);
        testRestaurant2.removeReview(testReview);
        double expected = (3.2 * 2 - 2);

        assertEquals(expected, testRestaurant2.getAvgStars(), 0.000001d);
    }
    /**
     * Test that the gateway is able to Retrieve all restaurants
     */
    @Test
    public void testRetrieveAllRestaurants() {
        ArrayList<Restaurant> restaurants = gateway.retrieveAllRestaurants();
        assertEquals("1234 temp ave", restaurants.get(1).getLocation());
        assertEquals("45 fake st", restaurants.get(0).getLocation());
    }

    /**
     * Test that the Gateway correctly removes the restaurant from the database
     */
    @Test
    public void testRemoveRestaurant() {
        gateway.deleteRestaurant("1234 temp ave");
        assertFalse(gateway.existsByLocation("1234 temp ave"));
    }

    // AFTER DOESN'T WORK
    @After
    public void teardown() {
        ArrayList<Restaurant> removeAll = gateway.retrieveAllRestaurants();
        for (Restaurant remove: removeAll) {
            gateway.deleteRestaurant(remove.getLocation());
        }
    }

}
