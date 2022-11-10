package Models;

public class ReviewResponseModel {
    private final boolean success;

    public ReviewResponseModel(boolean success){this.success = success;}

    public boolean wasSuccessful(){return this.success;}
}
