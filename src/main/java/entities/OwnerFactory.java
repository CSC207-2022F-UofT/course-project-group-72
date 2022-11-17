package entities;

public class OwnerFactory extends UserFactory{
    @Override
    public OwnerUser CreateUserObject(String username, String password) {
        return (OwnerUser) super.CreateUserObject(username, password);
    }
}
