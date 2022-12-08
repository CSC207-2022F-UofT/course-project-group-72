package restaurant_use_case.screens;

import entities.OwnerUser;
import entities.RestaurantFactory;
import global.IFrame;
import global.LabelTextPanel;
import restaurant_use_case.interfaces.RestaurantInputBoundary;
import restaurant_use_case.gateways.RestaurantDSGateway;
import restaurant_use_case.interactors.CreateRestaurantInteractor;
import user_use_case.interfaces.UserGatewayInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class CreateRestaurantView extends JFrame implements ActionListener {
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
     * The restaurant price ranges
     */
    JButton priceRange1;
    JButton priceRange2;
    JButton priceRange3;
    JButton priceRange4;
    JButton priceRange5;
    /**
     * The price bucket corresponding to the priceRange buttons clicked
     */
    int priceBucket = 1;
    /**
     * The Restaurant gateway which manages restaurant database
     */
    RestaurantDSGateway restaurantGateway;
    /**
     * The User gateway which manages user database
     */
    UserGatewayInterface userGateway;
    /**
     * The current user (which must be an owner user)
     */
    OwnerUser owner;
    /**
     * The previous screen
     */
    IFrame previousFrame;

    /**
     *
     * @param restaurantGateway the RestaurantDSGateway that manages the restaurant database
     * @param owner the current active User which must be an OwnerUser to access this view
     * @param userGateway the UserGateway that manages the user database
     * @param previousFrame the previous frame that initialized this one
     */
    public CreateRestaurantView(RestaurantDSGateway restaurantGateway, UserGatewayInterface userGateway,
                                OwnerUser owner, IFrame previousFrame) {

        this.restaurantGateway = restaurantGateway;
        this.userGateway = userGateway;
        this.owner = owner;
        this.previousFrame = previousFrame;

        // Title Label Creation
        JLabel title = new JLabel("Create Your Restaurant Profile");

        // TextField Creation
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

        // Price Bucket Info Creation
        JPanel priceInfo = new JPanel();

        // Price Bucket Label Creation
        JLabel dollarLabel = new JLabel("What is your price range?");
        dollarLabel.setFont(dollarLabel.getFont().deriveFont(16F));
        dollarLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        priceInfo.add(dollarLabel);

        // Range Button Creation
        priceRange1 = new JButton("1");
        priceRange2 = new JButton("2");
        priceRange3 = new JButton("3");
        priceRange4 = new JButton("4");
        priceRange5 = new JButton("5");

        priceRange1.setBackground(Color.GREEN);
        priceRange2.setBackground(Color.WHITE);
        priceRange3.setBackground(Color.WHITE);
        priceRange4.setBackground(Color.WHITE);
        priceRange5.setBackground(Color.WHITE);

        priceRange1.setOpaque(true);
        priceRange2.setOpaque(true);
        priceRange3.setOpaque(true);
        priceRange4.setOpaque(true);
        priceRange5.setOpaque(true);

        priceRange1.addActionListener(this);
        priceRange2.addActionListener(this);
        priceRange3.addActionListener(this);
        priceRange4.addActionListener(this);
        priceRange5.addActionListener(this);

        // Create a panel to hold the price buttons
        JPanel dollars = new JPanel();
        dollars.setLayout(new BoxLayout(dollars, BoxLayout.X_AXIS));
        dollars.add(priceRange1);
        dollars.add(priceRange2);
        dollars.add(priceRange3);
        dollars.add(priceRange4);
        dollars.add(priceRange5);
        dollars.setAlignmentX(Component.LEFT_ALIGNMENT);
        priceInfo.add(dollars);

        // Button Creation
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(confirm);
        buttons.add(cancel);

        confirm.addActionListener(this);
        cancel.addActionListener(this);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(nameInfo);
        main.add(locationInfo);
        main.add(cuisineInfo);
        main.add(priceInfo);
        main.add(buttons);

        // Set the Content Pane
        this.setContentPane(main);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        this.pack();

        // Set Visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        System.out.println("Click " + buttonPressed);
            switch(buttonPressed) {
                case("1"):
                    priceRange1.setBackground(Color.GREEN);
                    priceRange2.setBackground(Color.WHITE);
                    priceRange3.setBackground(Color.WHITE);
                    priceRange4.setBackground(Color.WHITE);
                    priceRange5.setBackground(Color.WHITE);
                    priceBucket = 1;
                    break;
                case("2"):
                    priceRange1.setBackground(Color.GREEN);
                    priceRange2.setBackground(Color.GREEN);
                    priceRange3.setBackground(Color.WHITE);
                    priceRange4.setBackground(Color.WHITE);
                    priceRange5.setBackground(Color.WHITE);
                    priceBucket = 2;
                    break;
                case("3"):
                    priceRange1.setBackground(Color.GREEN);
                    priceRange2.setBackground(Color.GREEN);
                    priceRange3.setBackground(Color.GREEN);
                    priceRange4.setBackground(Color.WHITE);
                    priceRange5.setBackground(Color.WHITE);
                    priceBucket = 3;
                    break;
                case("4"):
                    priceRange1.setBackground(Color.GREEN);
                    priceRange2.setBackground(Color.GREEN);
                    priceRange3.setBackground(Color.GREEN);
                    priceRange4.setBackground(Color.GREEN);
                    priceRange5.setBackground(Color.WHITE);
                    priceBucket = 4;
                    break;
                case("5"):
                    priceRange1.setBackground(Color.GREEN);
                    priceRange2.setBackground(Color.GREEN);
                    priceRange3.setBackground(Color.GREEN);
                    priceRange4.setBackground(Color.GREEN);
                    priceRange5.setBackground(Color.GREEN);
                    priceBucket = 5;
                    break;
                default:
                    // If no price bucket buttons were pressed the confirm or cancel was pressed
                    try {
                        if (Objects.equals(buttonPressed, "Confirm")) {
                            RestaurantInputBoundary interactor = new CreateRestaurantInteractor(
                                    new RestaurantFactory(),
                                    restaurantGateway,
                                    userGateway,
                                    new RestaurantResponseFormatter(previousFrame)
                            );
                            RestaurantCreateController restaurantController = new RestaurantCreateController(interactor);
                            RestaurantResponseModel result = restaurantController.create(
                                    owner,
                                    name.getText(),
                                    location.getText(),
                                    cuisineType.getText(),
                                    priceBucket
                            );

                            JOptionPane.showMessageDialog(this, result.getOperation());
                        }

                        // Dispose this current screen
                        this.dispose();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
            }

    }
}
