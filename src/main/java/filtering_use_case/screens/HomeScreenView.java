package filtering_use_case.screens;

import entities.User;
import global.IFrame;
import global.ProfileScreenActionListener;
import restaurant_use_case.interactors.FileRestaurant;
import restaurant_use_case.gateways.RestaurantDSGateway;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeScreenView extends IFrame {

    /**
     * The search query
     */
    JTextField query;

    /**
     * The search button that implements the SelectionsActionListener
     */
    JButton searchButton;

    /**
     * The input location (Postal Code)
     */
    JTextField location;

    /**
     * The selected price bucket (pricing /10)
     */
    JComboBox<Integer> priceBucket;

    /**
     * The select star rating (/5 stars)
     */
    JComboBox<Integer> avgStars;

    /**
     * The selected cuisine type
     */
    JComboBox<String> cuisineType;

    /**
     * The text label of the Price Selection
     */
    JLabel priceLabel;

    /**
     * The text label of the Rating (/5 Stars) Selection
     */
    JLabel starsLabel;

    /**
     * The text label of the Cuisine Selection
     */
    JLabel cuisineLabel;

    /**
     * locationLabel: The text label of the Location search input Selection
     */
    JLabel locationLabel;

    /**
     * The Button Group that contains all the filter selection buttons
     */
    ButtonGroup filterButtons;
    /**
     * The radiobutton for filtering by price
     */
    JRadioButton filterPriceButton;
    /**
     * The radiobutton for filtering by average stars
     */
    JRadioButton filterAvgStarsButton;
    /**
     * The radiobutton for filtering by name (alphabetical or reverse-alphabetical order)
     */
    JRadioButton filterNameButton;
    /**
     * The group of radiobuttons for the sorting direction
     */
    ButtonGroup sortDirection;
    /**
     * The radiobutton for sorting direction in ascending order
     */
    JRadioButton sortAscending;
    /**
     * The radiobutton for sorting direction in descending order
     */
    JRadioButton sortDescending;

    /**
     * The radiobutton for sorting direction in descending order
     */
    public static final String NO_PREFERENCE = "No Preference";
    public static final String PRICE = "Price";
    public static final String AVG_STARS = "AvgStars";
    public static final String NAME = "Name";
    public static final String[] CUISINE_LIST =
            {NO_PREFERENCE,
            "Italian",
            "Chinese",
            "Caribbean",
            "American",
            "Indian",
            "Japanese",
            "Hot Dog",
            "Food",
            "K BBQ",
            "Fast Food",
            };

    public HomeScreenView(User user) throws IOException {

        // Initialize Database Location and Gateway
        String RESTAURANT_DATABASE = "src/main/java/Databases/RestaurantDatabase.csv";
        final RestaurantDSGateway choicesGateway = new FileRestaurant(RESTAURANT_DATABASE);

        // Search Field(s) Temporarily placing Location as an input field
        JPanel searchField = new JPanel();

        query = new JTextField(20);
        location = new JTextField(4);

        searchField.add(query);

        // Drop-Down Menus Options
        Integer[] pricingOptions = {0, 1, 2, 3, 4, 5};
        Integer[] avgStarsOptions = {0, 1, 2, 3, 4, 5};

        // TODO: add more cuisine options
        // String[] cuisineOptions = {NO_PREFERENCE, "Food"};
        String[] cuisineOptions = CUISINE_LIST;

        JButton profileScreenButton = new JButton("Profile");
        profileScreenButton.addActionListener(new ProfileScreenActionListener(this, user, user.getUsername()));
        this.add(profileScreenButton);

        // Drop-Down Menus
        priceBucket = new JComboBox<>(pricingOptions);
        avgStars = new JComboBox<>(avgStarsOptions);
        cuisineType = new JComboBox<>(cuisineOptions);

        // Labels
        priceLabel = new JLabel("Filter By Price ($):");
        starsLabel = new JLabel("Filter By Rating (/5 Stars):");
        cuisineLabel = new JLabel("Filter By Cuisine:");
        locationLabel = new JLabel("Filter By Location (Postal Code):");

        // Filter Options
        JPanel filterFields = new JPanel();
        filterFields.add(priceLabel);
        filterFields.add(priceBucket);

        filterFields.add(starsLabel);
        filterFields.add(avgStars);

        filterFields.add(cuisineLabel);
        filterFields.add(cuisineType);

        filterFields.add(locationLabel);
        filterFields.add(location);

        // Filter Buttons
        JPanel sortFields = new JPanel();
        filterButtons = new ButtonGroup();

        // Initialize Buttons
        filterPriceButton = new JRadioButton("Sort By Price ($):");
        filterAvgStarsButton = new JRadioButton("Sort By Rating (/5 Stars):", true);
        filterNameButton = new JRadioButton("Sort By Name:");

        // Set Action Commands for Filters
        filterPriceButton.setActionCommand(PRICE);
        filterAvgStarsButton.setActionCommand(AVG_STARS);
        filterNameButton.setActionCommand(NAME);

        // Add to selection button group
        filterButtons.add(filterPriceButton);
        filterButtons.add(filterAvgStarsButton);
        filterButtons.add(filterNameButton);

        // Sorting Direction Button Group
        sortDirection = new ButtonGroup();

        // Separate Direction Buttons (Sort by Direction: Ascending, Descending)
        sortAscending = new JRadioButton("Sort Ascending: ");
        sortDescending = new JRadioButton("Sort Descending: ", true);

        // Set Action Commands for Direction
        sortAscending.setActionCommand("Ascending");
        sortDescending.setActionCommand("Descending");

        // Add to direction button group
        sortDirection.add(sortAscending);
        sortDirection.add(sortDescending);

        // Add individual panels to panel (can't add Button Group)
        sortFields.add(filterPriceButton);
        sortFields.add(filterAvgStarsButton);
        sortFields.add(filterNameButton);
        sortFields.add(sortAscending);
        sortFields.add(sortDescending);

        // Search Button
        searchButton = new JButton("Search");
        searchField.add(searchButton);
        searchButton.addActionListener(new SelectionsActionListener(
                this,
                choicesGateway,
                user,
                query,
                location,
                cuisineType,
                priceBucket,
                avgStars,
                filterButtons,
                sortDirection));

        // Setup Window
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.add(searchField);
        this.add(filterFields);
        this.add(sortFields);

        // Window options
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setVisible(true);
        repaint();
    }

    @Override
    public void refresh() {

    }

    @Override
    public void back() {

    }
}
