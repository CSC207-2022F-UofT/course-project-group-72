package user_use_case.screens;


import user_use_case.controllers.LoginUserController;
import user_use_case.interactors.LoginUserRequestModel;
import user_use_case.interfaces.UserGatewayInterface;
import entities.User;

import filtering_use_case.HomeScreenView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        try {
            LoginUserResponseModel responseModel = this.loginUserController.loginUser(
                    new LoginUserRequestModel(this.username.getText(), this.password.getText()));
            if (responseModel.wasSuccess()) {
                // Successfully logged-in new user
                // Set responseModel.getUser() to current user, go to home screen
                JOptionPane.showMessageDialog(this.parent, "Welcome Back " + responseModel.getUsername());
                this.parent.dispose();
                User curr_user = userGateway.getUser(this.username.getText());
                new HomeScreenView(curr_user);
            } else {
                // Error. Specific error: responseModel.getError()
                JOptionPane.showMessageDialog(this.parent, responseModel.getError());
            }
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this.parent,"Error Occurred");
        }

    }

}
