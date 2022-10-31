package entities;

public class UserFactory {

    public User CreateUserObject(String username, String password) {
        return new User(username, password);
    }
}
