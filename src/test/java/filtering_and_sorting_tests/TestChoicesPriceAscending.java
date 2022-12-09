package filtering_and_sorting_tests;
import entities.Restaurant;
import entities.RestaurantFactory;
import entities.User;
import filtering_use_case.interactors.*;
import filtering_use_case.interfaces.ChoicesInputBoundary;
import filtering_use_case.screens.*;
import org.junit.jupiter.api.Test;
import restaurant_use_case.gateways.RestaurantDSGateway;
import restaurant_use_case.interactors.FileRestaurant;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestChoicesPriceAscending {

    final RestaurantDSGateway gateway =
            new FileRestaurant("src/test/java/filtering_and_sorting_tests/tempchoices.csv");


    public TestChoicesPriceAscending() throws IOException {
    }

    // Restaurant setup
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
            "Food",
            5);


    @Test
    void PriceAscending() throws IOException {
        final Sorting sortMethod = new SortPrice();

        gateway.save(r1);
        gateway.save(r2);
        gateway.save(r3);

        ArrayList<Restaurant> newList = new ArrayList<>();
        newList.add(r1);
        newList.add(r2);
        newList.add(r3);

        ChoicesRequestModel inputData = new ChoicesRequestModel(
                "",
                "",
                "No Preference",
                0,
                0,
                "Ascending"
        );

        ChoicesPresenter presenter = new ChoicesPresenter() {
            @Override
            public ChoicesResponseModel prepareSuccessView(ChoicesResponseModel responseModel) {
                ArrayList<Restaurant> sortedList = responseModel.getRestaurants();
                assertEquals(newList, sortedList);
                return null;
            }

            @Override
            public ChoicesResponseModel prepareFailView(String error) {
                // Only if empty list
                fail(error);
                return null;
            }
        };

        // Set Up Interactor
        ChoicesInputBoundary interactor = new SortChoicesInteractor(gateway, presenter, sortMethod);

        // Sort
        interactor.select(inputData);
    }

    @Test
    void NameDescending() throws IOException {
        final Sorting sortMethod = new SortName();

        gateway.save(r1);
        gateway.save(r2);
        gateway.save(r3);

        ArrayList<Restaurant> newList = new ArrayList<>();
        newList.add(r1);
        newList.add(r3);
        newList.add(r2);

        ChoicesRequestModel inputData = new ChoicesRequestModel(
                "",
                "",
                "No Preference",
                0,
                0,
                "Descending"
        );

        ChoicesPresenter presenter = new ChoicesPresenter() {
            @Override
            public ChoicesResponseModel prepareSuccessView(ChoicesResponseModel responseModel) {
                ArrayList<Restaurant> sortedList = responseModel.getRestaurants();
                assertEquals(newList, sortedList);
                return null;
            }

            @Override
            public ChoicesResponseModel prepareFailView(String error) {
                // Only if empty list
                fail(error);
                return null;
            }
        };

        // Set Up Interactor
        ChoicesInputBoundary interactor = new SortChoicesInteractor(gateway, presenter, sortMethod);

        // Sort
        interactor.select(inputData);
    }

    @Test
    void AvgStarsDescending() throws IOException {
        final Sorting sortMethod = new SortAvgStars();

        gateway.save(r1);
        gateway.save(r2);
        gateway.save(r3);

        ArrayList<Restaurant> newList = new ArrayList<>();
        newList.add(r2);
        newList.add(r1);
        newList.add(r3);

        ChoicesRequestModel inputData = new ChoicesRequestModel(
                "",
                "",
                "No Preference",
                0,
                0,
                "Descending"
        );

        ChoicesPresenter presenter = new ChoicesPresenter() {
            @Override
            public ChoicesResponseModel prepareSuccessView(ChoicesResponseModel responseModel) {
                ArrayList<Restaurant> sortedList = responseModel.getRestaurants();
                assertEquals(newList, sortedList);
                return null;
            }

            @Override
            public ChoicesResponseModel prepareFailView(String error) {
                // Only if empty list
                fail(error);
                return null;
            }
        };

        // Set Up Interactor
        ChoicesInputBoundary interactor = new SortChoicesInteractor(gateway, presenter, sortMethod);

        // Sort
        interactor.select(inputData);
    }

    @Test
    void emptyList() throws IOException {
        final Sorting sortMethod = new SortName();

        ArrayList<Restaurant> newList = new ArrayList<>();

        ChoicesRequestModel inputData = new ChoicesRequestModel(
                "",
                "",
                "No",
                0,
                0,
                "Descending"
        );

        ChoicesPresenter presenter = new ChoicesPresenter() {
            @Override
            public ChoicesResponseModel prepareSuccessView(ChoicesResponseModel responseModel) {
                fail();
                return null;
            }

            @Override
            public ChoicesResponseModel prepareFailView(String error) {
                // Only if empty list
                assertEquals("NO RESTAURANTS MATCH SELECTIONS. PLEASE TRY AGAIN", error);
                return null;
            }
        };

        // Set Up Interactor
        ChoicesInputBoundary interactor = new SortChoicesInteractor(gateway, presenter, sortMethod);

        // Sort
        interactor.select(inputData);
    }

}
