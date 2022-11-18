package entities;

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
        return (OwnerUser) super.CreateUserObject(username, password);
    }
}
