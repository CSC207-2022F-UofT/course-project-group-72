package restaurant_screens;

import entities.OwnerUser;
import restaurant_use_case.RestaurantResponseModel;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CreateRestaurantView extends JPanel implements ActionListener {
    /**
     * The restaurant name
     */
    JTextField name;
    /**
     * The restaurant location
     */
    JTextField location;
    /**
     * The restaurant cuisine type
     */
    JTextField cuisineType;
    /**
     * The restaurant price range
     */
    JTextField priceBucket;
    /**
     * The controller
     */
    RestaurantController restaurantController;
    /**
     * The current user (which must be an owner user)
     */
    OwnerUser owner;
//    /**
//     * The previous frame
//     */
//    JFrame previousFrame;

    public CreateRestaurantView(RestaurantController restaurantController, OwnerUser owner) {

        this.restaurantController = restaurantController;
        this.owner = owner;
//        this.previousFrame = previousFrame;

        JLabel title = new JLabel("Create Restaurant");

        JPanel nameInfo = new JPanel();
        nameInfo.add(new JLabel("Restaurant Name"));
        name = new JTextField(15);
        nameInfo.add(name);
        // Choice of implementations

        location = new JTextField(15);
        LabelTextPanel locationInfo = new LabelTextPanel(
                new JLabel("Restaurant Location"), location);

        cuisineType = new JTextField(15);
        LabelTextPanel cuisineInfo = new LabelTextPanel(
                new JLabel("Restaurant Cuisine"), cuisineType);

        priceBucket = new JTextField("0", 15);
        LabelTextPanel priceInfo = new LabelTextPanel(
                new JLabel("Price Range"), priceBucket);

        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(confirm);
        buttons.add(cancel);

        confirm.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(nameInfo);
        this.add(cuisineInfo);
        this.add(locationInfo);
        this.add(priceInfo);
        this.add(buttons);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());

        try {
            if (Objects.equals(e.getActionCommand(), "Confirm")) {
                RestaurantResponseModel result = restaurantController.create(
                        owner.getUsername(),
                        name.getText(),
                        location.getText(),
                        cuisineType.getText(),
                        Integer.parseInt(priceBucket.getText()),
                        0, new ArrayList<String>()
                );

                JOptionPane.showMessageDialog(this, result.getOperation());
            }


//          TODO revert to previous view

            Container parentPanel = this.getParent();
            parentPanel.remove(this);
            parentPanel.revalidate();
            parentPanel.repaint();

//            this.setVisible(false);
//            this.dispose();
//            this.previousFrame.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }
}
