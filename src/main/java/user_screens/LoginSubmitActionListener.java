package user_screens;


import user_use_cases.LoginUserController;
import user_use_cases.LoginUserRequestModel;
import user_use_cases.LoginUserResponseModel;
import user_use_cases.UserGatewayInterface;
import entities.User;

import entities.User;
import javax.swing.*;
import java.awt.*;
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
        LoginUserResponseModel responseModel = this.loginUserController.loginUser(
                new LoginUserRequestModel(this.username.getText(), this.password.getText()));
        if (responseModel.wasSuccess()) {
            // Successfully logged-in new user
            // Set responseModel.getUser() to current user, go to home screen
            JOptionPane.showMessageDialog(this.parent, "Welcome Back " + responseModel.getUsername());
            this.parent.dispose();
            User curr_user = userGateway.getUser(this.username.getText());
            //new HomeScreenView(curr_user);
        } else {
            // Error. Specific error: responseModel.getError()
            JOptionPane.showMessageDialog(this.parent, responseModel.getError());
        }
    }

}
