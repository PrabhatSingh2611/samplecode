package pageObject.modals;

public class PreviewModal extends BaseModal {
    @Override
    protected BaseModal waitForAppearance() {
        return null;
    }

    @Override
    public boolean isModalOpen() {
        return true;
    }
}
