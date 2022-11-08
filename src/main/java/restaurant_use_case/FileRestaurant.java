package restaurant_use_case;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


//TODO Still to be done, database read write
//TODO Figure out how to overwrite for editing data
public class FileRestaurant implements RestaurantDSGateway{

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, RestaurantDSRequestModel> restaurants = new HashMap<>();

    public FileRestaurant(String csvPath) {
        csvFile = new File(csvPath);
    }

    @Override
    public void save(RestaurantDSRequestModel requestModel) {}

    @Override
    public boolean existsByLocation(String identifier) {}
}
