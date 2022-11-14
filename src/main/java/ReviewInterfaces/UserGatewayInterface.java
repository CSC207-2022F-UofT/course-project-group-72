package ReviewInterfaces;

import entities.User;

public interface UserGatewayInterface {

    public void addReview(String id);

    public void deleteReview(String id);

    public User getUser(String username);
}
