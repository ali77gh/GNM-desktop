package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.Service;
import com.gnm.desktop.ui.dialog.AddCountBaseServiceDialog;
import com.gnm.desktop.ui.dialog.AddPaymenServiceDialog;
import com.gnm.desktop.ui.dialog.AddTimeBaseServiceDialog;
import com.gnm.desktop.ui.view.HalfCircle;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class ReadyToAddItem extends AnchorPane {

    private ReadyToAddItemCallback callback;

    ReadyToAddItem() {

        HalfCircle closeBtn = HalfCircle.getRemoveNewServiceCircle();
        closeBtn.setOnMouseClicked(mouseEvent -> callback.onCancel());

        Button timeBaseAddBtn = new Button();
        timeBaseAddBtn.setOnMouseClicked(mouseEvent -> new AddTimeBaseServiceDialog(callback));

        Button countBaseAddBtn = new Button();
        countBaseAddBtn.setOnMouseClicked(mouseEvent -> new AddCountBaseServiceDialog(callback));

        Button paymentAddBtn = new Button();
        paymentAddBtn.setOnMouseClicked(mouseEvent -> new AddPaymenServiceDialog(callback));

    }

    public void setCallback(ReadyToAddItemCallback callback) {
        this.callback = callback;
    }

    public interface ReadyToAddItemCallback {
        void onCancel();

        void onNewServiceReadyToAdd(Service service);
    }
}
