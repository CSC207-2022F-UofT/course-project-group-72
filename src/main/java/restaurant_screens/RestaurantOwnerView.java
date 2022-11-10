package restaurant_screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class RestaurantOwnerView extends JFrame implements ActionListener{
    //NOTE: to access this class the restaurant must be owned by the OwnerUser

    public RestaurantOwnerView() {

        JLabel title = new JLabel("Restaurant");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton edit = new JButton("Edit Restaurant");
        JButton delete = new JButton("Delete Restaurant");

        JPanel buttons = new JPanel();
        buttons.add(edit);
        buttons.add(delete);

        edit.addActionListener(this);
        delete.addActionListener(this);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(buttons);

        this.pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());

        try {
            //TODO intialize new view
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


    }
}
