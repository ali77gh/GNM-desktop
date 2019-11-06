package com.gnm.desktop.ui.layout.customerlayout;

import com.gnm.desktop.core.Validation;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class NewCustomerPane extends AnchorPane {
    public NewCustomerPane(){
        setPrefWidth(300);
        setPadding(new Insets(10,20,10,20));
        getStyleClass().add("newCustomerPane_addNewCustomer");

        Label lblName=new Label("نام");
        lblName.getStyleClass().add("newCustomerPane_lblName");
        lblName.setPrefHeight(30);
        AnchorPane.setTopAnchor(lblName,0.0);
        AnchorPane.setRightAnchor(lblName,5.0);

        TextField txtName=new TextField();
        txtName.getStyleClass().add("textField");
        txtName.setPrefHeight(30);
        AnchorPane.setTopAnchor(txtName,40.0);
        AnchorPane.setRightAnchor(txtName,0.0);
        AnchorPane.setLeftAnchor(txtName,0.0);
        Validation.setLimit(txtName, Validation.CUSTOMER_NAME);

        Label lblPhoneNumber=new Label("شماره تلفن");
        lblPhoneNumber.getStyleClass().add("newCustomerPane_lblPhoneNumber");
        lblPhoneNumber.setPrefHeight(30);
        AnchorPane.setTopAnchor(lblPhoneNumber,110.0);
        AnchorPane.setRightAnchor(lblPhoneNumber,5.0);

        TextField txtPhoneNumber=new TextField();
        txtPhoneNumber.getStyleClass().add("textField");
        txtPhoneNumber.setPrefHeight(30);
        AnchorPane.setTopAnchor(txtPhoneNumber,150.0);
        AnchorPane.setRightAnchor(txtPhoneNumber,0.0);
        AnchorPane.setLeftAnchor(txtPhoneNumber,0.0);
        Validation.setLimit(txtPhoneNumber, Validation.PHONE_NUMBER);

        Button btnAdd=new Button("ثبت");
        btnAdd.getStyleClass().add("flatButton");
        AnchorPane.setTopAnchor(btnAdd,220.0);
        AnchorPane.setLeftAnchor(btnAdd,20.0);

        getChildren().addAll(lblName,txtName,lblPhoneNumber,txtPhoneNumber,btnAdd);

    }
}
