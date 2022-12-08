package user_use_case.interactors;

import user_use_case.interactors.UpgradeUserResponseModel;
import user_use_case.interactors.UpgradeUserRequestModel;

public interface UpgradeUserInputBoundary {

    public UpgradeUserResponseModel UpgradeUser(UpgradeUserRequestModel requestModel);
}
