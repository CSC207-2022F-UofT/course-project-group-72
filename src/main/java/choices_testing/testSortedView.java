package choices_testing;

import entities.Restaurant;
import entities.RestaurantFactory;
import filtering_use_case.ChoicesSortedView;
import restaurant_use_case.FileRestaurant;
import restaurant_use_case.RestaurantDSGateway;

import java.io.IOException;
import java.util.ArrayList;

public class testSortedView {
    public static void main(String[] args) throws IOException {
        final RestaurantDSGateway gateway = new FileRestaurant("./temp2.csv");

        RestaurantFactory factory = new RestaurantFactory();
        Restaurant r1 = factory.create("111",
                "Tim Hortons",
                "M1B 1M2",
                "Coffee",
                1);
        Restaurant r2 = factory.create("222",
                "Ajisen",
                "K9B 5L0",
                "Ramen",
                3);
        Restaurant r3 = factory.create("333",
                "Chako",
                "Z1B 2C8",
                "K BBQ",
                5);

        ArrayList<Restaurant> sortedList = new ArrayList<>();

        sortedList.add(r1);
        sortedList.add(r2);
        sortedList.add(r3);

        gateway.save(r1);
        gateway.save(r2);
        gateway.save(r3);

        ArrayList<Restaurant> newList = gateway.searchMatch("Chako", "Z1B");

        ChoicesSortedView view = new ChoicesSortedView(newList);
        view.setVisible(true);
    }
}


