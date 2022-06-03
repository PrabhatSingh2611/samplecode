package pageObject.modals;

import utils.Config;

public abstract class BaseModal {

    Config config = new Config();

    protected abstract BaseModal waitForAppearance();

    public abstract boolean isModalOpen();

}
