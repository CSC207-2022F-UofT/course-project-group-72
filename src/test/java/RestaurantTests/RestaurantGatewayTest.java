package RestaurantTests;

import entities.Restaurant;
import entities.RestaurantFactory;
import restaurant_use_case.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantGatewayTest {

    final RestaurantDSGateway gateway = new FileRestaurant("./temptest.csv");
    final RestaurantFactory factory = new RestaurantFactory();

    public RestaurantGatewayTest() throws IOException {
    }

    /**
     * Test that the Gateway can save the restaurant
     */
    @Test
    public void testSave() {
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
                3,
                reviews);

        gateway.save(test1);
    }

    /**
     * Test that the Gateway retrieves the correct Restaurant
     */
    @Test
    public void testRetrieveRestaurant() {
        Restaurant actual = gateway.retrieveRestaurant("1234");
    }
    @Test
    public void testRetrieveRestaurantName() {
        String actual = gateway.retrieveRestaurant("1234 temp ave").getName();
        assert actual.equals("timsstraunt");
    }

    @Test
    public void testRetrieveRestaurantOwnerID() {
        String actual = gateway.retrieveRestaurant("1234 temp ave").getOwnerID();
        assert actual.equals("123");
    }

    @Test
    public void testRetrieveRestaurantLocation() {
        String actual = gateway.retrieveRestaurant("1234 temp ave").getLocation();
        assert actual.equals("1234 temp ave");
    }

    @Test
    public void testRetrieveRestaurantCuisineType() {
        String actual = gateway.retrieveRestaurant("1234, temp ave").getCuisineType();
        assert actual.equals("bbq");
    }

    @Test
    public void testRetrieveRestaurantPriceBucket() {
        int actual = gateway.retrieveRestaurant("1234, temp ave").getPriceBucket();
        assert actual == 5;
    }
}
