package profile_screens;

import entities.OwnerUser;
import restaurant_screens.CreateRestaurantView;
import restaurant_screens.IFrame;
import restaurant_use_case.RestaurantDSGateway;
import user_use_cases.UserGateway;
import user_use_cases.UserGatewayInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateRestaurantActionListener implements ActionListener {
    private final IFrame parent;
    private final OwnerUser owner;
    private final RestaurantDSGateway restaurantGateway;

    private final UserGatewayInterface userGateway;


    public CreateRestaurantActionListener(IFrame parent, UserGateway userGateway, RestaurantDSGateway restaurantDSGateway, OwnerUser owner) {
        this.parent = parent;
        this.restaurantGateway = restaurantDSGateway;
        this.userGateway = userGateway;
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CreateRestaurantView createRestaurantView = new CreateRestaurantView(this.restaurantGateway, this.userGateway, this.owner, this.parent);
    }
}
