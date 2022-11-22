//Custom error for when no reviews matching the id are found

package review_use_case.gateways;

public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException(String errorMessage){super(errorMessage);}
}
