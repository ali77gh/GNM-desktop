package com.gnm.desktop.ui.dialog;

public class AreYouSureDialog extends BaseDialog {

    public AreYouSureDialog(AreYouSureDialogCallback cb) {

    }

    public interface AreYouSureDialogCallback {
        void onConfirm();

        void onCancel();
    }
}
