package com.gnm.desktop.ui.dialog;

public class RemoveActiveCustomerDialog {

    public RemoveActiveCustomerDialog(RemoveActiveCustomerDialogCallback cb) {

    }

    public interface RemoveActiveCustomerDialogCallback {
        void onDelete();
    }
}
