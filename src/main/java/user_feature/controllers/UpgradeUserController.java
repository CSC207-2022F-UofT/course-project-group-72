package user_feature.controllers;

import user_feature.interactors.UpgradeUserInputBoundary;
import user_feature.interactors.UpgradeUserRequestModel;
import user_feature.interactors.UpgradeUserResponseModel;

public class UpgradeUserController {
    private final UpgradeUserInputBoundary upgradeUserInputBoundary;

    public UpgradeUserController(UpgradeUserInputBoundary upgradeUserInputBoundary) {
        this.upgradeUserInputBoundary = upgradeUserInputBoundary;
    }

    public UpgradeUserResponseModel UpgradeUser(UpgradeUserRequestModel requestModel) {
        return this.upgradeUserInputBoundary.UpgradeUser(requestModel);
    }
}
