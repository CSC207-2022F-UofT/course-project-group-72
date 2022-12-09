package global;

import entities.User;
import user_feature.controllers.UpgradeUserController;
import user_feature.interactors.UpgradeUserRequestModel;

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
