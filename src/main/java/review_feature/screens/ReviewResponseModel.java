//Class that holds a boolean representing whether an action was a success

package review_feature.screens;

public class ReviewResponseModel {
    private final boolean success;

    /*
    Constructor
     */
    public ReviewResponseModel(boolean success){this.success = success;}

    /*
    Return success
     */
    public boolean wasSuccessful(){return this.success;}
}
