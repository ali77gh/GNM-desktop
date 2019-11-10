package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.core.KeyboardListener;
import com.gnm.desktop.core.Validation;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.CountBaseAutoComplete;
import com.gnm.desktop.ui.layout.priceslayout.CBSCard;
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

        TextField txtServiceName = new TextField(cbs.name);
        txtServiceName.getStyleClass().add("textField");
        Validation.setLimit(txtServiceName, Validation.SERVICE_NAME);

        TextField txtServicePrice = new TextField(String.valueOf(cbs.price));
        txtServicePrice.getStyleClass().add("textField");
        Validation.setLimit(txtServicePrice, Validation.MONEY);

        //error

        Label errServiceName = new Label("خطا");
        errServiceName.getStyleClass().add("textError");
        errServiceName.setVisible(false);

        Label errServicePrice = new Label("خطا");
        errServicePrice.getStyleClass().add("textError");
        errServicePrice.setVisible(false);

        Button btnEdit = new Button("ویرایش");
        Button btnDelete = new Button("حذف");
        btnDelete.setOnMouseClicked(event -> {
            DB.CountBaseAutoComplete.Remove(cbs.getId());
            //update service cards
            CBSCard.Refresh();
            close();
        });

        Button btnCancel = new Button("انصراف");

        btnEdit.getStyleClass().add("flatButton");
        btnDelete.getStyleClass().add("flatButtonDelete");
        btnCancel.getStyleClass().add("flatButton");


        KeyboardListener.setEnter(btnEdit, () -> {

            if (Validation.checkEmpty(txtServiceName, errServiceName) &
                    Validation.checkEmptyAndNumeric(txtServicePrice, errServicePrice)) {
                cbs.name = txtServiceName.getText();
                cbs.price = Integer.valueOf(txtServicePrice.getText());
                DB.CountBaseAutoComplete.Update(cbs);
                CBSCard.Refresh();
                close();
            }
        }, txtServiceName, txtServicePrice, btnCancel, btnDelete, btnEdit);

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
}
