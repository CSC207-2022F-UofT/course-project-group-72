package global;

import entities.OwnerFactory;
import entities.User;
import user_use_case.controllers.UpgradeUserController;
import user_use_case.interactors.UpgradeUserInteractor;
import user_use_case.interactors.UpgradeUserRequestModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpgradeUserActionListener implements ActionListener {

    private final User user;

    private UpgradeUserController controller;

    public UpgradeUserActionListener(User user, UpgradeUserController controller) {
        this.user = user;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UpgradeUserRequestModel requestModel = new UpgradeUserRequestModel(this.user);
        this.controller.UpgradeUser(requestModel);
    }
}
