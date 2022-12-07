package global;

import entities.User;
import filtering_use_case.screens.HomeScreenView;

import javax.swing.*;
import java.io.IOException;

public abstract class IFrame extends JFrame {
    public abstract void refresh();
    public abstract void back();
    public void home(User user) {
        try {
            new HomeScreenView(user);
            this.dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred, Please try again later");
        }
    }
}
