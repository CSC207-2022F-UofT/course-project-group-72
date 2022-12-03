package filtering_use_case.screens;

import entities.User;
import restaurant_use_case.interactors.FileRestaurant;
import restaurant_use_case.gateways.RestaurantDSGateway;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HomeScreenView extends JFrame {
    /**
     * The search query
     */
    JTextField query;
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
     * The group of radiobuttons for the sorting selection
     */
    JLabel priceLabel;
    JLabel starsLabel;
    JLabel cuisineLabel;
    JLabel locationLabel;
    ButtonGroup sortButtons;
    /**
     * The radiobutton for sorting by price
     */
    JRadioButton sortPriceButton;
    /**
     * The radiobutton for sorting by average stars
     */
    JRadioButton sortAvgStarsButton;
    /**
     * The radiobutton for sorting by name (alphabetical or reverse-alphabetical order)
     */
    JRadioButton sortNameButton;
    /**
     * The group of radiobuttons for the sorting direction
     */
    ButtonGroup sortDirection;
    /**
     * The radiobutton for sorting direction
     */
    JRadioButton sortAscending;
    /**
     * The radiobutton for sorting direction
     */
    JRadioButton sortDescending;
    /**
     * The choices controller
     */
    public static final String NO_PREFERENCE = "No Preference";
    public static final String PRICE = "Price";
    public static final String AVG_STARS = "AvgStars";
    public static final String NAME = "Name";

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
        String[] cuisineOptions = {NO_PREFERENCE, "Food"};
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

        // Sort Method Buttons
        JPanel sortFields = new JPanel();
        sortButtons = new ButtonGroup();

        // Initialize Buttons
        sortPriceButton = new JRadioButton("Sort By Price ($):");
        sortAvgStarsButton = new JRadioButton("Sort By Rating (/5 Stars):", true);
        sortNameButton = new JRadioButton("Sort By Name:");

        // Set Action Commands for Sort Method
        sortPriceButton.setActionCommand("Price");
        sortAvgStarsButton.setActionCommand("AvgStars");
        sortNameButton.setActionCommand("Name");

        // Add to selection button group
        sortButtons.add(sortPriceButton);
        sortButtons.add(sortAvgStarsButton);
        sortButtons.add(sortNameButton);

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
        sortFields.add(sortPriceButton);
        sortFields.add(sortAvgStarsButton);
        sortFields.add(sortNameButton);
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
                sortButtons,
                sortDirection));

        // Setup Window
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.add(searchField);
        this.add(filterFields);
        this.add(sortFields);

        // Window options
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 350));
        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        repaint();
    }

}
