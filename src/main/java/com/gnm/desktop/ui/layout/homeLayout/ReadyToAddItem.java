package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.Service;
import com.gnm.desktop.ui.dialog.AddCountBaseServiceDialog;
import com.gnm.desktop.ui.dialog.AddPaymentServiceDialog;
import com.gnm.desktop.ui.dialog.AddTimeBaseServiceDialog;
import com.gnm.desktop.ui.view.HalfCircle;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class ReadyToAddItem extends AnchorPane {

    private ReadyToAddItemCallback callback;

    ReadyToAddItem() {

        HalfCircle closeBtn = HalfCircle.getRemoveNewServiceCircle();
        closeBtn.setOnMouseClicked(mouseEvent -> callback.onCancel());
        AnchorPane.setRightAnchor(closeBtn, 0.0);

        Button timeBaseAddBtn = new Button("بر پایه زمان");
        timeBaseAddBtn.getStyleClass().add("flatButton");
        AnchorPane.setRightAnchor(timeBaseAddBtn, 40.0);
        AnchorPane.setTopAnchor(timeBaseAddBtn, 8.0);
        timeBaseAddBtn.setOnMouseClicked(mouseEvent -> new AddTimeBaseServiceDialog(callback));

        Button countBaseAddBtn = new Button("بر پایه تعداد");
        countBaseAddBtn.getStyleClass().add("flatButton");
        AnchorPane.setRightAnchor(countBaseAddBtn, 140.0);
        AnchorPane.setTopAnchor(countBaseAddBtn, 8.0);
        countBaseAddBtn.setOnMouseClicked(mouseEvent -> new AddCountBaseServiceDialog(callback));

        Button paymentAddBtn = new Button("پرداخت");
        paymentAddBtn.getStyleClass().add("flatButton");
        AnchorPane.setRightAnchor(paymentAddBtn, 250.0);
        AnchorPane.setTopAnchor(paymentAddBtn, 8.0);
        paymentAddBtn.setOnMouseClicked(mouseEvent -> new AddPaymentServiceDialog(callback));


        this.getChildren().addAll(
                closeBtn,
                timeBaseAddBtn,
                countBaseAddBtn,
                paymentAddBtn
        );
    }

    void setCallback(ReadyToAddItemCallback callback) {
        this.callback = callback;
    }

    public interface ReadyToAddItemCallback {
        void onCancel();

        void onNewServiceReadyToAdd(Service service);
    }
}
