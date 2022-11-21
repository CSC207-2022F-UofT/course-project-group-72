package user_screens;

import user_use_cases.*;

import entities.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeUserView extends JFrame {

    private static final String NAME_OF_USER_DATABASE = "src/main/java/Databases/UserDatabase.csv";
    private GuestUser guest_user;
    private UserGateway userGateway;
    private UserFactory factory = new UserFactory();
    private RegisterUserController registerUserController;
    private LoginUserController loginUserController;
    public WelcomeUserView() {

        //Initialize all needed
        this.userGateway = new UserGateway();

        //Initialize all needed interactors and controllers
        RegisterUserInputBoundary registerUserInteractor = new RegisterUserInteractor(factory,
                userGateway);
        this.registerUserController = new RegisterUserController(registerUserInteractor);

        //Attach these objects to the screen, so they can be used by actionPerformed
        this.guest_user = new GuestUser();

        // Label with "Register" and basic instructions
        JLabel title = new JLabel("Welcome!");
        title.setFont(title.getFont().deriveFont(16F));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Label with "Register" and basic instructions
        JLabel register_label = new JLabel("Register New Account");
        register_label.setFont(register_label.getFont().deriveFont(16F));
        register_label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Input username - Register
        JTextField usernameField = new JTextField("Enter Username");
        usernameField.setFont(usernameField.getFont().deriveFont(16F));
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);


        // Input password_1 - Register
        JTextField passwordField = new JTextField("Enter Password");
        passwordField.setFont(passwordField.getFont().deriveFont(16F));
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Input password_2 (Input Password) & Label for explanation - Register
        JTextField confirmPasswordField = new JTextField("Confirm Password");
        confirmPasswordField.setFont(confirmPasswordField.getFont().deriveFont(16F));
        confirmPasswordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Register Submit button
        JButton registerSubmitButton = new JButton("Submit");
        registerSubmitButton.setOpaque(true);
        registerSubmitButton.addActionListener(new RegisterSubmitActionListener(this, registerUserController,
                userGateway, usernameField.getText(), passwordField.getText(), confirmPasswordField.getText()));

        // Label with "Login" and basic instructions
        JLabel login_label = new JLabel("Log-In to Existing Account");
        login_label.setFont(login_label.getFont().deriveFont(16F));
        login_label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Input username - Log-in
        JTextField login_usernameField = new JTextField("Enter Existing Username");
        login_usernameField.setFont(usernameField.getFont().deriveFont(16F));
        login_usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);


        // Input password - Log-in
        JTextField login_passwordField = new JTextField("Enter Password");
        login_passwordField.setFont(passwordField.getFont().deriveFont(16F));
        login_passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Register Submit button - Log-in
        JButton loginSubmitButton = new JButton("Log In");
        loginSubmitButton.setOpaque(true);
        loginSubmitButton.addActionListener(new LoginSubmitActionListener(this, loginUserController,
                userGateway, login_usernameField.getText(), login_passwordField.getText()));


        // Assemble all components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.setVisible(true);


    }

}
