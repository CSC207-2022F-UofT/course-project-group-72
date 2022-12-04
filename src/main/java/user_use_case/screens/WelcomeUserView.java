package user_use_case.screens;

import entities.*;
import user_use_case.controllers.LoginUserController;
import user_use_case.controllers.RegisterUserController;
import user_use_case.gateways.UserGateway;
import user_use_case.interactors.LoginUserInputBoundary;
import user_use_case.interactors.LoginUserInteractor;
import user_use_case.interactors.RegisterUserInputBoundary;
import user_use_case.interactors.RegisterUserInteractor;

import javax.swing.*;
import java.awt.*;

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

        LoginUserInputBoundary loginUserInteractor = new LoginUserInteractor(factory,
                userGateway);
        this.loginUserController = new LoginUserController(loginUserInteractor);

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
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout());
        JLabel usernameFieldLabel = new JLabel("Enter Username:");
        JTextField usernameFieldText = new JTextField(12);
        usernameFieldText.setFont(usernameFieldText.getFont().deriveFont(16F));
        usernamePanel.add(usernameFieldLabel);
        usernamePanel.add(usernameFieldText);


        // Input password_1 - Register
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout());
        JLabel passwordFieldLabel = new JLabel("Enter Password:");
        JTextField passwordFieldText = new JTextField(12);
        passwordFieldText.setFont(passwordFieldText.getFont().deriveFont(16F));
        passwordPanel.add(passwordFieldLabel);
        passwordPanel.add(passwordFieldText);


        // Input password_2 (Input Password) & Label for explanation - Register
        JPanel confirmpassowrdPanel = new JPanel();
        confirmpassowrdPanel.setLayout(new BoxLayout());
        JLabel confirmPasswordFieldLabel = new JLabel("Confirm Password:");
        JTextField confirmPasswordFieldText = new JTextField(12);
        confirmPasswordFieldText.setFont(confirmPasswordFieldText.getFont().deriveFont(16F));
        confirmpassowrdPanel.add(confirmPasswordFieldLabel);
        confirmpassowrdPanel.add(confirmPasswordFieldText);

        // Register Submit button
        JButton registerSubmitButton = new JButton("Submit");
        registerSubmitButton.setOpaque(true);
        registerSubmitButton.addActionListener(new RegisterSubmitActionListener(this, registerUserController,
                userGateway, usernameFieldText, passwordFieldText, confirmPasswordFieldText));

        // Label with "Login" and basic instructions
        JLabel login_label = new JLabel("Log-In to Existing Account");
        login_label.setFont(login_label.getFont().deriveFont(16F));
        login_label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Input username - Log-in
        JPanel login_usernamePanel = new JPanel();
        login_usernamePanel.setLayout(new BoxLayout());
        JLabel login_usernameFieldLabel = new JLabel("Enter Existing Username");
        JTextField login_usernameFieldText = new JTextField(12);
        login_usernameFieldText.setFont(usernameFieldText.getFont().deriveFont(16F));
        login_usernameFieldText.setAlignmentX(Component.LEFT_ALIGNMENT);
        login_usernamePanel.add(login_usernameFieldLabel);
        login_usernamePanel.add(login_usernameFieldText);


        // Input password - Log-in
        JPanel login_passwordPanel = new JPanel();
        login_passwordPanel.setLayout(new BoxLayout());
        JLabel login_passwordFieldLabel = new JLabel("Enter Password");
        JTextField login_passwordFieldText = new JTextField(12);
        login_passwordFieldText.setFont(passwordFieldText.getFont().deriveFont(16F));
        login_passwordFieldText.setAlignmentX(Component.LEFT_ALIGNMENT);
        login_passwordPanel.add(login_passwordFieldLabel);
        login_passwordPanel.add(login_passwordFieldText);

        // Register Submit button - Log-in
        JButton loginSubmitButton = new JButton("Log In");
        loginSubmitButton.setOpaque(true);
        loginSubmitButton.addActionListener(new LoginSubmitActionListener(this, loginUserController,
                userGateway, login_usernameFieldText, login_passwordFieldText));


        // Register User Area
        JPanel regsiterPanel = new JPanel();
        regsiterPanel.add(register_label);
        regsiterPanel.add(usernamePanel);
        regsiterPanel.add(passwordPanel;
        regsiterPanel.add(confirmpassowrdPanel;
        regsiterPanel.add(registerSubmitButton);

        // Login User Area
        JPanel loginPanel = new JPanel();
        loginPanel.add(login_label);
        loginPanel.add(login_usernamePanel;
        loginPanel.add(login_passwordPanel;
        loginPanel.add(loginSubmitButton);


        // Assemble all components
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(regsiterPanel);
        mainPanel.add(loginPanel);

        mainPanel.setVisible(true);

        this.add(mainPanel);

        //Set this instance's content pane to main and set its behaviour

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);

        this.setVisible(true);


    }

}
