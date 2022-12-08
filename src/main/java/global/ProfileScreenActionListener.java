package global;

import entities.User;
import user_use_case.screens.ProfileScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileScreenActionListener implements ActionListener {

    private User user;

    private IFrame previousFrame;

    private String username;

    public ProfileScreenActionListener(IFrame previousFrame, User user, String username) {
        this.user = user;
        this.previousFrame = previousFrame;
        this.username = username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.previousFrame.profile(this.previousFrame, this.user);
    }

}
