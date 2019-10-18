package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.PaymentBaseService;
import javafx.scene.layout.AnchorPane;

public class PaymentBaseItem extends AnchorPane {

    private ServiceCardItemCallback callback;

    PaymentBaseItem(PaymentBaseService paymentBaseService) {

    }

    public void setCallback(ServiceCardItemCallback callback) {
        this.callback = callback;
    }
}
