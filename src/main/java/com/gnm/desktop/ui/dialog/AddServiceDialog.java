package com.gnm.desktop.ui.dialog;


import com.gnm.desktop.core.Validation;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.GenericDAO;
import com.gnm.desktop.data.model.CountBaseAutoComplete;
import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.ui.layout.priceslayout.CBSCard;
import com.gnm.desktop.ui.layout.priceslayout.PricesLayout;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddServiceDialog extends BaseDialog {


    public AddServiceDialog(GenericDAO repo) {

        //label

        Label lblServiceName = new Label("سرویس :");
        lblServiceName.getStyleClass().add("dialogText");
        lblServiceName.setAlignment(Pos.CENTER_RIGHT);

        Label lblServicePrice = new Label("قیمت(تومن):");
        lblServicePrice.getStyleClass().add("dialogText");

        //input

        TextField txtServiceName = new TextField();
        txtServiceName.getStyleClass().add("textField");

        TextField txtServicePrice = new TextField();
        txtServicePrice.getStyleClass().add("textField");

        //error

        Label errServiceName = new Label("خطا");
        errServiceName.getStyleClass().add("textError");
        errServiceName.setVisible(false);

        Label errServicePrice = new Label("خطا");
        errServicePrice.getStyleClass().add("textError");
        errServicePrice.setVisible(false);

        Button btnAccept = new Button("ثبت");

        btnAccept.setOnMouseClicked(event -> {

            if (Validation.checkEmpty(txtServiceName, errServiceName) &
                    Validation.checkEmptyAndNumeric(txtServicePrice, errServicePrice)) {
                if (repo == DB.Prices) {
                    DB.Prices.Insert(new PricePerHour(txtServiceName.getText(), Integer.valueOf(txtServicePrice.getText())));
                    //update service cards
                    PricesLayout.Refresh();
                }else if (repo == DB.CountBaseAutoComplete){
                    DB.CountBaseAutoComplete.Insert(new CountBaseAutoComplete(txtServiceName.getText(), Integer.valueOf(txtServicePrice.getText())));
                    //update service cards
                    CBSCard.Refresh();
                }

                this.close();
            }
        });

        Button btnCancel = new Button("انصراف");

        btnAccept.getStyleClass().add("flatButton");
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
        var btns = new HBox(btnAccept, btnCancel);
        btns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.getChildren().add(btns);
        root.setAlignment(Pos.CENTER_LEFT);

        setup(root, btnCancel, "افزودن سرویس", 300, 250);
        show();
    }
}
