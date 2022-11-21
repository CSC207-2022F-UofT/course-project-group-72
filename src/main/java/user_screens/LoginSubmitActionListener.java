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

public class LoginSubmitActionListener {

    private final JFrame parent;
    private final LoginUserController loginUserController;
    private final UserGatewayInterface userGateway;
    private String username;

    private String password;

    /*
    Constructor
     */
    public LoginSubmitActionListener(JFrame parent, LoginUserController loginUserController,
                                        UserGatewayInterface userGateway, String username, String password) {
        this.parent = parent;
        this.loginUserController = registerUserController;
        this.userGateway = userGateway;
        this.username = username;
        this.password_1 = password_1;
        this.password_2 = password_2;
    }

}
