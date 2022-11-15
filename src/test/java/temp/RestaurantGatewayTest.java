package temp;

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

    @Test
    public void testSave() {
        Restaurant test1 = factory.create(
                "123",
                "timsstraunt",
                "1234 temp ave",
                "terrible",
                5);

        gateway.save(test1);
    }

    @Test
    public void testRetrieveRestaurant() {
        String actual = gateway.retrieveRestaurant("1234 temp ave").getName();
        assert actual.equals("timsstraunt");
    }
}
