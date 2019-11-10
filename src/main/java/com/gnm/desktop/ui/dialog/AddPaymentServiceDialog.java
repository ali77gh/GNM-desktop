package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.core.KeyboardListener;
import com.gnm.desktop.core.Validation;
import com.gnm.desktop.core.calculator.PaymentBaseService;
import com.gnm.desktop.ui.layout.homeLayout.ReadyToAddItem;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddPaymentServiceDialog extends BaseDialog {

    public AddPaymentServiceDialog(ReadyToAddItem.ReadyToAddItemCallback cb) {

        //label
        Label amountLabel = new Label("مبلغ :");
        amountLabel.getStyleClass().add("dialogText");
        amountLabel.setAlignment(Pos.CENTER_RIGHT);

        //input
        TextField amountInput = new TextField();
        amountInput.getStyleClass().add("textField");
        Validation.setLimit(amountInput, Validation.MONEY);

        //error
        Label amountErr = new Label("خطا");
        amountErr.getStyleClass().add("textError");
        amountErr.setVisible(false);

        // buttons
        Button btnAccept = new Button("اضافه کن");
        Button btnCancel = new Button("انصراف");

        btnAccept.getStyleClass().add("flatButton");
        btnCancel.getStyleClass().add("flatButton");

        KeyboardListener.setEnter(btnAccept, () -> {
            if (Validation.checkEmptyAndNumeric(amountInput, amountErr)) {
                cb.onNewServiceReadyToAdd(new PaymentBaseService(Integer.valueOf(amountInput.getText())));
                this.close();
            }
        }, amountInput, btnAccept, btnCancel);


        //setup
        var root = new VBox(15);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.getChildren().addAll(
                amountLabel,
                amountInput,
                amountErr
        );
        var btns = new HBox(btnAccept, btnCancel);
        btns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.getChildren().add(btns);
        root.setAlignment(Pos.CENTER_LEFT);

        setup(root, btnCancel, "افزودن پرداخت", 300, 160);
        show();
    }
}
