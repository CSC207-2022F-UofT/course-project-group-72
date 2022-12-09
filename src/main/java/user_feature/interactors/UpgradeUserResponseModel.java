package user_feature.interactors;

import entities.OwnerUser;

public class UpgradeUserResponseModel {
    OwnerUser ownerUser;

    Boolean status;

    public UpgradeUserResponseModel(OwnerUser ownerUser, Boolean status) {
        this.status = status;
        this.ownerUser = ownerUser;
    }

    public OwnerUser getOwnerUser() {
        return this.ownerUser;
    }

    public boolean getStatus() {
        return this.status;
    }
}
