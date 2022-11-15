package user_use_cases;

import entities.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDatabaseGateway {
    // User Data is saved in order: first column -> username, second column -> password

    private static final String NAME_OF_USER_DATABASE = "src/main/java/Databases/UserDatabase.csv";


    public Boolean userExists(String username) {
        try {
            File file = new File(NAME_OF_USER_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ",";
            String line;

            while(scanner.hasNext()){
                line = scanner.nextLine();
                String[] user = line.split(delimiter);

                if (user[0].equals(username)){
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public String getPassword(String username) {
        try {
            File file = new File(NAME_OF_USER_DATABASE);
            Scanner scanner = new Scanner(file);
            String delimiter = ",";
            String line;

            while(scanner.hasNext()) {
                line = scanner.nextLine();
                String[] user = line.split(delimiter);

                if (user[0].equals(username)) {
                    return user[1];
                }
            }
        }catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void addUser(String username, String password) {
        try {
            File file = new File(NAME_OF_USER_DATABASE);
            FileWriter writer = new FileWriter(file, true);
            writer.append(username);
            writer.append(",");
            writer.append(password);
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
