package com.gnm.desktop.ui.dialog;

public class RemoveActiveCustomerDialog {

    public RemoveActiveCustomerDialog(RemoveActiveCustomerDialogCallback cb) {
        cb.onDelete();
    }

    public interface RemoveActiveCustomerDialogCallback {
        void onDelete();
    }
}
