package global;

import entities.User;
import filtering_use_case.screens.HomeScreenView;
import user_use_case.screens.ProfileScreen;

import javax.swing.*;
import java.io.IOException;

/**
 * The abstract class that also acts as the View Interface to
 * allow for the presenter to call on a View class without violating
 * dependency rules
 */
public abstract class IFrame extends JFrame implements ViewInterface{
    /**
     * Refreshes the current screen with updated data from the database
     */
    public abstract void refresh();
    /**
     * Refreshes the previous screen, and disposes of the current one
     */
    public abstract void back();
    /**
     * Goes back to the starting home page by reinitializing
     * HomeScreenView with the current user
     *
     * @param user the current active user
     */
    public void home(User user) {
        try {
            // Try to reinitialize home screen
            new HomeScreenView(user);
            // Dispose of the current screen
            this.dispose();
        } catch (IOException e) {
            // If unable to, display error message
            JOptionPane.showMessageDialog(this, "An error occurred, Please try again later");
        }
    }

    public void profile(IFrame previousFrame, User user) {
        new ProfileScreen(previousFrame, user, user.getUsername());
        this.dispose();
    }
}
