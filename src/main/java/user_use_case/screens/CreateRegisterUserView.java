package user_use_case.screens;

import entities.GuestUser;
import user_use_case.controllers.RegisterUserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateRegisterUserView extends JPanel implements ActionListener {

    /**
     * Input User Username
     */
    JTextField username;

    /**
     * Input Password1
     */
    String password1;

    /**
     * Input Password2
     */
    String password2;

    /**
     * Controller
     */
    RegisterUserController registerUserController;
    /**
     * Current GuestUser, until logged in
     */

    GuestUser guestUser;

    public CreateRegisterUserView(RegisterUserController registerusercontroller) {
        this.registerUserController = registerusercontroller;
        this.guestUser = new GuestUser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
