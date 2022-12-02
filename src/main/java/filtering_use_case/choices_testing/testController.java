package filtering_use_case.choices_testing;

import filtering_use_case.*;
import org.w3c.dom.ls.LSOutput;
import entities.Restaurant;
import entities.RestaurantFactory;
import entities.User;
import restaurant_use_case.gateways.RestaurantDSGateway;
import restaurant_use_case.interactors.FileRestaurant;

import java.io.IOException;
import java.util.ArrayList;

public class testController {

    public static void main(String[] args) throws IOException {

        final RestaurantDSGateway choicesGateway = new FileRestaurant("./temp2.csv");

        ChoicesPresenter presenter = new ChoicesResponseFormatter();
        ChoicesInputBoundary interactor = new sortChoices(choicesGateway, presenter);
        ChoicesController choicesController = new ChoicesController(interactor);


        ChoicesResponseModel selections = choicesController.select(
                "", "", "No Preference", 0, 0,
                "Price", "Ascending");

        ArrayList<Restaurant> nae = selections.getRestaurants();
        System.out.println(selections.toString());

        User userTest = new User("aa", "bb");

        ChoicesSortedView view = new ChoicesSortedView(nae, userTest);
        view.setVisible(true);
    }
}
