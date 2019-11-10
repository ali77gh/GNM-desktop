package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.core.AppRefresh;
import com.gnm.desktop.core.KeyboardListener;
import com.gnm.desktop.core.Validation;
import com.gnm.desktop.core.calculator.CountBaseService;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.CountBaseAutoComplete;
import com.gnm.desktop.ui.layout.homeLayout.ReadyToAddItem;
import com.gnm.desktop.ui.layout.rightMenu.Items;
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

public class AddCountBaseServiceDialog extends BaseDialog {

    public AddCountBaseServiceDialog(ReadyToAddItem.ReadyToAddItemCallback cb) {

        //label
        Label nameLabel = new Label("نام سرویس :");
        nameLabel.getStyleClass().add("dialogText");
        nameLabel.setAlignment(Pos.CENTER_RIGHT);

        Label amountLabel = new Label("مبلغ :");
        amountLabel.getStyleClass().add("dialogText");
        amountLabel.setAlignment(Pos.CENTER_RIGHT);

        //input
        AutoCompleteTextField nameInput = new AutoCompleteTextField();
        nameInput.getStyleClass().add("textField");
        Validation.setLimit(nameInput, Validation.SERVICE_NAME);

        TextField amountInput = new TextField();
        amountInput.getStyleClass().add("textField");
        Validation.setLimit(amountInput, Validation.MONEY);

        setupAutoComplete(nameInput, amountInput);

        //error
        Label nameErr = new Label("خطا");
        nameErr.getStyleClass().add("textError");
        nameErr.setVisible(false);

        Label amountErr = new Label("خطا");
        amountErr.getStyleClass().add("textError");
        amountErr.setVisible(false);

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

                cb.onNewServiceReadyToAdd(new CountBaseService(nameInput.getText(), Integer.valueOf(amountInput.getText())));

                AddCountBaseIfNotExist(nameInput.getText(), Integer.valueOf(amountInput.getText()));
                this.close();
            }
        }, nameInput, amountInput, btnAccept, btnCancel);

        //setup
        var root = new VBox(15);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.getChildren().addAll(
                nameLabel,
                nameInput,
                nameErr,
                amountLabel,
                amountInput,
                amountErr
        );
        var btns = new HBox(btnAccept, btnCancel);
        btns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.getChildren().add(btns);
        root.setAlignment(Pos.CENTER_LEFT);

        setup(root, btnCancel, "افزودن سرویس", 300, 250);
        show();
    }

    private void AddCountBaseIfNotExist(String name, int price) {

        //check exist
        List<CountBaseAutoComplete> result = DB.CountBaseAutoComplete.getWithCondition(object -> (
                (CountBaseAutoComplete) object).name.equals(name)
        );

        if (result.size() == 0) {
            // not exist
            DB.CountBaseAutoComplete.Insert(new CountBaseAutoComplete(name, price));
        } else {
            // exist
            result.get(0).price = price;
            DB.CountBaseAutoComplete.Update(result.get(0));
        }

        AppRefresh.pleaseRefresh(Items.PRICES);
    }

    private void setupAutoComplete(AutoCompleteTextField name, TextField amount) {

        List<CountBaseAutoComplete> countBaseServices = DB.CountBaseAutoComplete.getAll();

        List<String> names = new ArrayList<>();
        for (CountBaseAutoComplete i : countBaseServices)
            names.add(i.name);


        name.getEntries().addAll(names);


        name.textProperty().addListener((observableValue, s, t1) -> {

            int index = names.indexOf(name.getText());
            if (index != -1) {
                amount.setText(String.valueOf(countBaseServices.get(index).price));
            }
        });
    }
}
