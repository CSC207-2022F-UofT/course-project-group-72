package global;

import entities.OwnerUser;
import restaurant_feature.screens.CreateRestaurantView;
import restaurant_feature.interfaces.RestaurantDSGateway;
import user_feature.gateways.UserGateway;
import user_feature.interfaces.UserGatewayInterface;

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
