package restaurant_feature.gateways;

import entities.Restaurant;
import entities.RestaurantFactory;
import restaurant_feature.interfaces.RestaurantDSGateway;

import java.io.*;
import java.util.*;


// Current implementation rewrites the whole file whenever save is called, may be updated later

/**
 * The implementation of the Restaurant Gateway, manages the Restaurant Database
 */
public class FileRestaurant implements RestaurantDSGateway {
    public final static String DEFAULT_DATABASE_PATH = "src/main/java/Databases/RestaurantDatabase.csv";
    /**
     * The filler string for empty lists
     */
    private final static String EMPTY_FILLER = "empty";
    /**
     * The file containing the data
     */
    private final File csvFile;
    /**
     * The headers of the csv, corresponding to the information that that column contains
     */
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    /**
     *  The restaurants that have been loaded from the database on construction
     *  Maps the restaurant's unique location to the restaurant
     */
    private final Map<String, Restaurant> currentRestaurants = new HashMap<>();

    /**
     *
     * @param csvPath the path to the csv file
     * @throws IOException Catches errors in writing new data
     */
    public FileRestaurant(String csvPath) throws IOException {
        csvFile = new File(csvPath);

        headers.put("location", 0);
        headers.put("name", 1);
        headers.put("cuisineType", 2);
        headers.put("priceBucket", 3);
        headers.put("owner", 4);

        headers.put("avgStars", 5);
        headers.put("reviews", 6);

        // Writes the headers if it is an empty file
        // Else read through the file and add all restaurants to currentRestaurants
        if (csvFile.length() == 0) {
            Writer writer = writeHeaders();
            writer.close();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine();

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String location = String.valueOf(col[headers.get("location")]);
                String name = String.valueOf(col[headers.get("name")]);
                String cuisineType = String.valueOf(col[headers.get("cuisineType")]);
                int priceBucket = Integer.parseInt(col[headers.get("priceBucket")]);
                String owner = String.valueOf(col[headers.get("owner")]);
                double avgStars = Double.parseDouble(col[headers.get("avgStars")]);

                String reviews = String.valueOf(col[headers.get("reviews")]);
                ArrayList<String> reviewsList = new ArrayList<>(Arrays.asList(reviews.split("<")));
                // If the EMPTY FILLER was in the line, then remove it and return the empty list
                reviewsList.remove(EMPTY_FILLER);

                RestaurantFactory restaurantFactory = new RestaurantFactory();
                Restaurant restaurant = restaurantFactory.reinitialize(owner, name, location, cuisineType,
                        priceBucket, avgStars, reviewsList);
                currentRestaurants.put(location, restaurant);
            }

            reader.close();
        }
    }

    /**
     * adds the Restaurant to the current restaurants then resaves
     *
     * @param requestModel the Restaurant to be saved into the database
     */
    @Override
    public void save(Restaurant requestModel) {
        currentRestaurants.put(requestModel.getLocation(), requestModel);
        save();
    }

    /**
     * Goes through all the currentRestaurants and rewrites them in the file
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = writeHeaders();

            for (Restaurant restaurant : currentRestaurants.values()) {
                String reviewsLine = String.join("<", restaurant.getReviewIDs());
                // Since reading a line with nothing after the last comma causes an index of array error
                // add an EMPTY FILLER
                if (reviewsLine.length() == 0) {
                    reviewsLine = EMPTY_FILLER;
                }
                String line = String.join(",",
                        restaurant.getLocation(),
                        restaurant.getName(),
                        restaurant.getCuisineType(),
                        Integer.toString(restaurant.getPriceBucket()),
                        restaurant.getOwnerID(),
                        Double.toString(restaurant.getAvgStars()),
                        reviewsLine
                );
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method that writes the headers of the file
     *
     * @return BufferedWriter for the csv file
     * @throws IOException catches errors when writing data
     */
    private BufferedWriter writeHeaders() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
        writer.write(String.join(",", headers.keySet()));
        writer.newLine();
        return writer;
    }

    /**
     *  Checks if a restaurant exists in the database
     * @param identifier the unique location identifier of the Restaurant
     * @return boolean, if or if the restaurant is in the database
     */
    @Override
    public boolean existsByLocation(String identifier) {
        return currentRestaurants.containsKey(identifier);
    }

    /**
     *  Gets the desired restaurant
     * @param location the unique location identifier of the Restaurant
     * @return the Restaurant at that location
     */
    @Override
    public Restaurant retrieveRestaurant(String location) {
        return currentRestaurants.get(location);
    }

    /**
     *
     * @return an ArrayList of all Restaurants in the database
     */
    @Override
    public ArrayList<Restaurant> retrieveAllRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        for (String location : currentRestaurants.keySet()) {
            restaurants.add(retrieveRestaurant(location));
        }
        return restaurants;
    }

    /**
     * Deletes the restaurant from the database
     * @param location the unique location identifier of the Restaurant
     */
    @Override
    public void deleteRestaurant(String location) {
        this.currentRestaurants.remove(location);
        save();
    }

    /**
     *  Searches and filters through the Restaurant database for matching restaurant selections
     * @param nameQuery the user's text input for their specified restaurant name
     * @param locationQuery the user's text input for their specified location
     */
    @Override
    public ArrayList<Restaurant> searchMatch(String nameQuery, String locationQuery) {
        ArrayList<Restaurant> filteredRestaurants = new ArrayList<>();

        for (Restaurant restaurant : retrieveAllRestaurants()) {

            if (restaurant.getName().contains(nameQuery) || Objects.equals(nameQuery, "")){
                if (Objects.equals(locationQuery, "")) {
                    filteredRestaurants.add(restaurant);
                }
                else if (locationQuery.length() < 3) {
                    ;   // pass, must be kept here to avoid index error
                }
                else if (restaurant.getLocation().contains(locationQuery.substring(0, 3))) {
                    filteredRestaurants.add(restaurant);
                }
            }
        }

        return filteredRestaurants;
    }
}
