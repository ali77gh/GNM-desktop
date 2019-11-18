package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.core.KeyboardListener;
import com.gnm.desktop.core.Validation;
import com.gnm.desktop.core.calculator.TimeBaseService;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.ui.layout.homeLayout.ReadyToAddItem;
import com.gnm.desktop.ui.view.AutoCompleteTextField;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class AddTimeBaseServiceDialog extends BaseDialog {

    public AddTimeBaseServiceDialog(ReadyToAddItem.ReadyToAddItemCallback cb) {

        //label
        Label nameLabel = new Label("نام سرویس :");
        nameLabel.getStyleClass().addAll("dialogText");
        nameLabel.setAlignment(Pos.CENTER_RIGHT);

        Label amountLabel = new Label("مبلغ :");
        amountLabel.getStyleClass().addAll("dialogText");
        amountLabel.setAlignment(Pos.CENTER_RIGHT);

        Label consoleIdLabel = new Label("شناسه ی دستگاه :");
        consoleIdLabel.getStyleClass().addAll("dialogText");
        consoleIdLabel.setAlignment(Pos.CENTER_RIGHT);

        //input
        AutoCompleteTextField nameInput = new AutoCompleteTextField();
        nameInput.getStyleClass().addAll("textField");
        Validation.setLimit(nameInput, Validation.SERVICE_NAME);

        TextField amountInput = new TextField();
        amountInput.getStyleClass().addAll("textField");
        Validation.setLimit(amountInput, Validation.MONEY);

        TextField consoleIdInput = new TextField();
        consoleIdInput.getStyleClass().addAll("textField");
        Validation.setLimit(consoleIdInput, Validation.CONSOLE_NAME);

        setupAutoComplete(nameInput, amountInput);

        //error
        Label nameErr = new Label("خطا");
        nameErr.getStyleClass().addAll("textError");
        nameErr.setVisible(false);

        Label amountErr = new Label("خطا");
        amountErr.getStyleClass().addAll("textError");
        amountErr.setVisible(false);

        Label consoleIdErr = new Label("خطا");
        consoleIdErr.getStyleClass().add("textError");
        consoleIdErr.setVisible(false);

        // buttons
        Button btnAccept = new Button("اضافه کن");
        Button btnCancel = new Button("انصراف");

        btnAccept.getStyleClass().add("flatButton");
        btnCancel.getStyleClass().add("flatButton");

        KeyboardListener.setEnter(btnAccept, () -> {
            if (
                    Validation.checkEmpty(nameInput, nameErr) &
                            Validation.checkEmptyAndNumeric(amountInput, amountErr)
            ) {

                cb.onNewServiceReadyToAdd(new TimeBaseService(nameInput.getText(), Integer.valueOf(amountInput.getText()), consoleIdInput.getText()));

                this.close();
            }
        }, nameInput, amountInput, consoleIdInput, btnAccept, btnCancel);

        //setup
        var root = new VBox(15);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.getChildren().addAll(
                nameLabel,
                nameInput,
                nameErr,
                amountLabel,
                amountInput,
                amountErr,
                consoleIdLabel,
                consoleIdInput,
                consoleIdErr
        );
        var btns = new HBox(btnAccept, btnCancel);
        btns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.getChildren().add(btns);
        root.setAlignment(Pos.CENTER_LEFT);

        setup(root, btnCancel, "افزودن سرویس", 300, 360);
        show();
    }

    private void setupAutoComplete(AutoCompleteTextField name, TextField amount) {

        List<PricePerHour> pricePerHours = DB.Prices.getAll();

        List<String> names = new ArrayList<>();
        for (PricePerHour i : pricePerHours)
            names.add(i.name);


        name.getEntries().addAll(names);


        name.textProperty().addListener((observableValue, s, t1) -> {

            int index = names.indexOf(name.getText());
            if (index != -1) {
                amount.setText(String.valueOf(pricePerHours.get(index).pricePerHour));
            }
        });
    }
}
