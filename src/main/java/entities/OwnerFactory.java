package entities;

public class OwnerFactory extends UserFactory{
    @Override
    public User CreateUserObject(String username, String password) {
        return super.CreateUserObject(username, password);
    }
}
