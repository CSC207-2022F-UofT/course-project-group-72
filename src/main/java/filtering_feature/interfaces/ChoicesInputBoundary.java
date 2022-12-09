package filtering_feature.interfaces;

import filtering_feature.interactors.ChoicesRequestModel;
import filtering_feature.screens.ChoicesResponseModel;

/**
 * The Choices Input Boundary interface implemented by SortChoicesInteractor
 */

public interface ChoicesInputBoundary {
    ChoicesResponseModel select(ChoicesRequestModel requestModel);
}