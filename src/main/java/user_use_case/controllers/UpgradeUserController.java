package user_use_case.controllers;

import user_use_case.interactors.UpgradeUserInputBoundary;
import user_use_case.interactors.UpgradeUserRequestModel;
import user_use_case.interactors.UpgradeUserResponseModel;

public class UpgradeUserController {
    private final UpgradeUserInputBoundary upgradeUserInputBoundary;

    public UpgradeUserController(UpgradeUserInputBoundary upgradeUserInputBoundary) {
        this.upgradeUserInputBoundary = upgradeUserInputBoundary;
    }

    public UpgradeUserResponseModel UpgradeUser(UpgradeUserRequestModel requestModel) {
        return this.upgradeUserInputBoundary.UpgradeUser(requestModel);
    }
}
