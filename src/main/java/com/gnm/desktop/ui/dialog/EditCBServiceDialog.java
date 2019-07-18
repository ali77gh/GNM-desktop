package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.CountBaseAutoComplete;
import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.ui.layout.priceslayout.PricesLayout;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditCBServiceDialog extends BaseDialog {

    public EditCBServiceDialog(CountBaseAutoComplete cbs) {

        //label

        Label lblServiceName = new Label("سرویس :");
        lblServiceName.getStyleClass().add("dialogText");
        lblServiceName.setAlignment(Pos.CENTER_RIGHT);

        Label lblServicePrice = new Label("قیمت(تومن):");
        lblServicePrice.getStyleClass().add("dialogText");

        //input

        TextField txtServiceName = new TextField();
        txtServiceName.getStyleClass().add("textField");
        txtServiceName.setText(cbs.name);

        TextField txtServicePrice = new TextField();
        txtServicePrice.getStyleClass().add("textField");
        txtServicePrice.setText(String.valueOf(cbs.price));

        //error

        Label errServiceName = new Label("خطا");
        errServiceName.getStyleClass().add("textError");
        errServiceName.setVisible(false);

        Label errServicePrice = new Label("خطا");
        errServicePrice.getStyleClass().add("textError");
        errServicePrice.setVisible(false);

        Button btnEdit = new Button("ویرایش");

        btnEdit.setOnMouseClicked(event -> {

            if (validation(txtServiceName, txtServicePrice, errServiceName, errServicePrice)) {
                cbs.name = txtServiceName.getText();
                cbs.price = Integer.valueOf(txtServicePrice.getText());
                DB.CountBaseAutoComplete.Update(cbs);
                PricesLayout.makeTimeBaseServiceCards();
                close();
            }
        });

        setupClearError(txtServiceName, txtServicePrice, errServiceName, errServicePrice);

        Button btnDelete = new Button("حذف");
        btnDelete.setOnMouseClicked(event -> {
            DB.Prices.Remove(cbs.getId());
            //update service cards
            PricesLayout.makeTimeBaseServiceCards();
            close();
        });

        Button btnCancel = new Button("انصراف");

        btnEdit.getStyleClass().add("flatButton");
        btnDelete.getStyleClass().add("flatButtonDelete");
        btnCancel.getStyleClass().add("flatButton");

        var root = new VBox(15);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.getChildren().addAll(
                lblServiceName,
                txtServiceName,
                errServiceName,
                lblServicePrice,
                txtServicePrice,
                errServicePrice
        );
        var btns = new HBox(btnEdit, btnCancel, btnDelete);
        btns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.getChildren().add(btns);
        root.setAlignment(Pos.CENTER_LEFT);


        super.setup(root, btnCancel, "ویرایش سرویس", 300, 250);
        show();
    }

    private boolean validation(TextField input1, TextField input2, Label err1, Label err2) {

        boolean isOk = true;
        if (input1.getText().equals("")) {
            err1.setVisible(true);
            err1.setText("خالی است");
            isOk = false;
        }

        if (input2.getText().equals("")) {
            err2.setVisible(true);
            err2.setText("خالی است");
            isOk = false;
        } else if (!isInt(input2.getText())) {
            err2.setVisible(true);
            err2.setText("عدد وارد کنید");
            isOk = false;
        }

        return isOk;

    }

    private void setupClearError(TextField input1, TextField input2, Label err1, Label err2) {

        input1.textProperty().addListener(observable -> err1.setVisible(false));
        input2.textProperty().addListener(observable -> err2.setVisible(false));
    }

    private boolean isInt(String str) {
        try {
            var a = Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
