package user_screens;

import user_use_cases.RegisterUserController;
import user_use_cases.RegisterUserRequestModel;
import user_use_cases.RegisterUserResponseModel;
import user_use_cases.UserGatewayInterface;
import entities.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterSubmitActionListener implements ActionListener{

    private final JFrame parent;
    private final RegisterUserController registerUserController;
    private final UserGatewayInterface userGateway;
    private String username;

    private String password_1;

    private String password_2;

    /*
    Constructor
     */
    public RegisterSubmitActionListener(JFrame parent, RegisterUserController registerUserController,
                              UserGatewayInterface userGateway, String username, String password_1, String password_2) {
        this.parent = parent;
        this.registerUserController = registerUserController;
        this.userGateway = userGateway;
        this.username = username;
        this.password_1 = password_1;
        this.password_2 = password_2;
    }


        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterUserResponseModel responseModel = this.registerUserController.CreateUserObject(
                    new RegisterUserRequestModel(this.username, this.password_1, this.password_2));
            if (responseModel.wasSuccess()) {
                // Successfully registered new user
                // Set responseModel.getUser() to current user, go to home screen
                JOptionPane.showMessageDialog(this.parent, "Welcome " + responseModel.getUsername());
                this.parent.dispose();
                User curr_user = new User(username, password_1);
                // new HomeScreenView();
            } else {
                // Error. Specific error: responseModel.getError()
                JOptionPane.showMessageDialog(this.parent, responseModel.getError());
            }

    }

}
