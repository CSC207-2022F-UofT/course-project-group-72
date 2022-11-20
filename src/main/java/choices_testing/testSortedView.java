package choices_testing;

import entities.Restaurant;
import entities.RestaurantFactory;
import restaurant_screens.ChoicesSortedView;
import restaurant_use_case.ChoicesResponseModel;
import restaurant_use_case.FileRestaurant;
import restaurant_use_case.RestaurantDSGateway;

import java.io.IOException;
import java.util.ArrayList;

public class testSortedView {
    public static void main(String[] args) throws IOException {

        RestaurantFactory factory = new RestaurantFactory();
        Restaurant r1 = factory.create("111",
                "Tim Hortons",
                "M1B",
                "Coffee",
                1);
        Restaurant r2 = factory.create("222",
                "Ajisen",
                "K9B",
                "Ramen",
                3);
        Restaurant r3 = factory.create("333",
                "Chako",
                "Z1B",
                "K BBQ",
                5);

        ArrayList<Restaurant> sortedList = new ArrayList<>();

        sortedList.add(r1);
        sortedList.add(r2);
        sortedList.add(r3);


        ChoicesSortedView view = new ChoicesSortedView(sortedList);
        view.setVisible(true);
    }
}
