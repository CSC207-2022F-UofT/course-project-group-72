package filtering_use_case.interfaces;

import filtering_use_case.interactors.ChoicesRequestModel;
import filtering_use_case.screens.ChoicesResponseModel;

public interface ChoicesInputBoundary {
    ChoicesResponseModel select(ChoicesRequestModel requestModel);
}