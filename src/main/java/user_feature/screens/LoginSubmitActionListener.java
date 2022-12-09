package user_feature.screens;


import filtering_feature.screens.HomeScreenView;
import user_feature.controllers.LoginUserController;
import user_feature.interactors.LoginUserRequestModel;
import user_feature.interfaces.UserGatewayInterface;
import entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginSubmitActionListener implements ActionListener{

    private final JFrame parent;
    private final LoginUserController loginUserController;
    private final UserGatewayInterface userGateway;
    private JTextField username;
    private JTextField password;

    /*
    Constructor
     */
    public LoginSubmitActionListener(JFrame parent, LoginUserController loginUserController,
                                        UserGatewayInterface userGateway, JTextField username, JTextField password) {
        this.parent = parent;
        this.loginUserController = loginUserController;
        this.userGateway = userGateway;
        this.username = username;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LoginUserResponseModel responseModel = this.loginUserController.loginUser(
                new LoginUserRequestModel(this.username.getText(), this.password.getText()));
        if (responseModel.wasSuccess()) {
            // Successfully logged-in new user
            // Set responseModel.getUser() to current user, go to home screen
            JOptionPane.showMessageDialog(this.parent, "Welcome Back " + responseModel.getUsername());
            this.parent.dispose();
            User curr_user = userGateway.getUser(this.username.getText());

            // Change View to HomeScreenView
            // new HomeScreenView(curr_user);   OLD CODE
            HomeScreenView HomeView;
            try {
                HomeView = new HomeScreenView(curr_user);
            } catch (IOException ex) {
                throw new RuntimeException(ex); // Change?
            }
            HomeView.setVisible(true);

        } else {
            // Error. Specific error: responseModel.getError()
            JOptionPane.showMessageDialog(this.parent, responseModel.getError());
        }
    }

}
