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

    private String password_1;

}
