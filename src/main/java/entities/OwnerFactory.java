package entities;

import java.util.ArrayList;

/**
 * Creates an instance of OwnerUser in the same manner as User
 */
public class OwnerFactory extends UserFactory{
    /**
     * User to create a new instance of OwnerUser
     *
     * @param username the unique username of the User
     * @param password the password of the User
     * @return an OwnerUser instance
     */
    @Override
    public OwnerUser CreateUserObject(String username, String password) {
        return new OwnerUser(username, password);
    }

    /**
     *
     * @param username the username of the User
     * @param password the password of the User
     * @param restaurants the owned Restaurants of the OwnerUser
     * @return an OwnerUser instance
     */
    public OwnerUser reintialize(String username, String password, ArrayList<String> restaurants) {
        return new OwnerUser(username, password, restaurants);
    }
}
