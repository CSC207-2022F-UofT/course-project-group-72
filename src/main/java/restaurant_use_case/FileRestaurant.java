package restaurant_use_case;

import entities.Restaurant;
import entities.RestaurantFactory;

import java.io.*;
import java.util.*;


// Current implementation rewrites the whole file whenever save is called, may be updated later

//TODO figure out why this error below occurs
public class FileRestaurant implements RestaurantDSGateway{

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, RestaurantDSRequestModel> currentRestaurants = new HashMap<>();

    private final RestaurantFactory factory = new RestaurantFactory();

    public FileRestaurant(String csvPath) throws IOException {
        csvFile = new File(csvPath);

        headers.put("location", 0);
        headers.put("name", 1);
        headers.put("cuisineType", 2);
        headers.put("priceBucket", 3);
        headers.put("owner", 4);

        headers.put("avgStars", 5);
        headers.put("reviews", 6);

        if (csvFile.length() == 0) {
            writeHeaders();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine();

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String location = String.valueOf(col[headers.get("location")]);
                String name = String.valueOf(col[headers.get("name")]);
                String cusineType = String.valueOf(col[headers.get("cusineType")]);
                int priceBucket = Integer.parseInt(col[headers.get("priceBucket")]);
                String owner = String.valueOf(col[headers.get("owner")]);
                double avgStars = Double.parseDouble(col[headers.get("avgStars")]);

                String reviews = String.valueOf(col[headers.get("reviews")]);
                ArrayList<String> reviewsList = new ArrayList<>(Arrays.asList(reviews.split("<")));

                RestaurantDSRequestModel restaurant = new RestaurantDSRequestModel(owner, name,
                        location, cusineType, priceBucket, avgStars, reviewsList);
                currentRestaurants.put(location, restaurant);
            }

            reader.close();
        }
    }

    @Override
    public void save(RestaurantDSRequestModel requestModel) {
        currentRestaurants.put(requestModel.getName(), requestModel);
        save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = writeHeaders();

            for (RestaurantDSRequestModel restaurant : currentRestaurants.values()) {
                String reviewsLine = String.join("<", restaurant.getReviews());
                String line = String.join(",",
                        restaurant.getLocation(),
                        restaurant.getName(),
                        restaurant.getCuisineType(),
                        Integer.toString(restaurant.getPriceBucket()),
                        restaurant.getOwner(),
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

    private BufferedWriter writeHeaders() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
        writer.write(String.join(",", headers.keySet()));
        writer.newLine();
        return writer;
    }

    @Override
    public boolean existsByLocation(String identifier) {
        return currentRestaurants.containsKey(identifier);
    }

    @Override
    public Restaurant retrieveRestaurant(String location) {
        //TODO reinitializing
        RestaurantDSRequestModel temp = currentRestaurants.get(location);
        return factory.reintialize(
                temp.getOwner(),
                temp.getName(),
                temp.getLocation(),
                temp.getCuisineType(),
                temp.getPriceBucket(),
                temp.getAvgStars(),
                temp.getReviews()
        );
    }

    @Override
    public ArrayList<Restaurant> retrieveAllRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        for (String location : currentRestaurants.keySet()) {
            restaurants.add(retrieveRestaurant(location));
        }
        return restaurants;
    }
}
