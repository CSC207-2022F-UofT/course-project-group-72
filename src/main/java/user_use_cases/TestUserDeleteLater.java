package user_use_cases;

import java.io.IOException;
import entities.User;
import entities.UserFactory;
import entities.OwnerFactory;
import user_screens.WelcomeUserView;


import java.io.BufferedWriter;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TestUserDeleteLater {

    UserFactory factory = new UserFactory();

    public static void main(String[] args) {
        /*
        UserGateway gateway = new UserGateway();

        User user1 = new UserFactory().CreateUserObject("Henry", "testpassword");
        gateway.addUser(user1.getUsername(), user1.getPassword());
        */
        // Test Welcome View
        WelcomeUserView weclomeview = new WelcomeUserView();

    }

}
