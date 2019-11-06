package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.PaymentBaseService;
import com.gnm.desktop.ui.dialog.AreYouSureDialog;
import com.gnm.desktop.ui.view.HalfCircle;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

class PaymentBaseItem extends AnchorPane {

    private ServiceCardItemCallback callback;

    PaymentBaseItem(PaymentBaseService paymentBaseService) {

        HalfCircle closeBtn = HalfCircle.getRemoveNewServiceCircle();
        closeBtn.setOnMouseClicked(mouseEvent -> {
            new AreYouSureDialog(() -> {
                callback.onDelete();
            });
        });
        AnchorPane.setRightAnchor(closeBtn, 0.0);


        Label payment = new Label("پرداخت");
        payment.getStyleClass().add("dialogText");
        AnchorPane.setRightAnchor(payment, 40.0);
        AnchorPane.setTopAnchor(payment, 10.0);


        Label paymentValue = new Label(paymentBaseService.getCurrentCost() + "T");
        paymentValue.getStyleClass().add("dialogText");
        AnchorPane.setLeftAnchor(paymentValue, 46.0);
        AnchorPane.setTopAnchor(paymentValue, 10.0);

        this.getChildren().addAll(
                closeBtn,
                payment,
                paymentValue
        );
    }

    public void setCallback(ServiceCardItemCallback callback) {
        this.callback = callback;
    }
}
