package user_feature.screens;

import user_feature.controllers.RegisterUserController;
import user_feature.interactors.RegisterUserRequestModel;
import user_feature.interfaces.UserGatewayInterface;
import entities.User;

import filtering_feature.screens.HomeScreenView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterSubmitActionListener implements ActionListener{

    private final JFrame parent;
    private final RegisterUserController registerUserController;
    private final UserGatewayInterface userGateway;
    private JTextField username;

    private JTextField password_1;

    private JTextField password_2;

    /*
    Constructor
     */
    public RegisterSubmitActionListener(JFrame parent, RegisterUserController registerUserController,
                              UserGatewayInterface userGateway, JTextField username,
                                        JTextField password_1, JTextField password_2) {
        this.parent = parent;
        this.registerUserController = registerUserController;
        this.userGateway = userGateway;
        this.username = username;
        this.password_1 = password_1;
        this.password_2 = password_2;
    }


        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                RegisterUserResponseModel responseModel = this.registerUserController.CreateUserObject(
                        new RegisterUserRequestModel(this.username.getText(), this.password_1.getText(),
                                this.password_2.getText()));
                if (responseModel.wasSuccess()) {
                    // Successfully registered new user
                    // Set responseModel.getUser() to current user, go to home screen
                    JOptionPane.showMessageDialog(this.parent, "Welcome " + responseModel.getUsername());
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
