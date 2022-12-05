package report_use_case.interfaces;

import entities.Review;
import entities.User;

//Design Pattern: Strategy
//This interface is used to implement the Strategy design pattern

//Specific ban algorithms (thresholds) are implemented in the classes that implement this interface
public interface BanningAlgorithm {

    //check and ban review
    Review checkAndBanReview();

    //check and ban user
    User checkAndBanUser();
}
