//Custom error for when no reviews matching the id are found

package ReviewGateways;

public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException(String errorMessage){super(errorMessage);}
}
