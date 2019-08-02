package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.Customer;
import com.gnm.desktop.ui.layout.customerlayout.CustomerLayout;
import com.gnm.desktop.ui.view.GameTag;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerDialog extends BaseDialog implements GameTag.deletableGameTag{


    private final int HEIGHT=500;
    private final int WIDTH=450;

    public List<String> gamesList;
    private static FlowPane gameTagsFlow;

    public AddCustomerDialog(){


        AnchorPane root=new AnchorPane();


        //label

        Label lblCustomerName = new Label("نام");
        AnchorPane.setTopAnchor(lblCustomerName,20.0);
        AnchorPane.setRightAnchor(lblCustomerName,10.0);
        lblCustomerName.setPrefHeight(20);
        lblCustomerName.getStyleClass().add("dialogText");

        Label lblCustomerPhone = new Label("تلفن");
        AnchorPane.setTopAnchor(lblCustomerPhone,120.0);
        AnchorPane.setRightAnchor(lblCustomerPhone,10.0);
        lblCustomerPhone.setPrefHeight(20);
        lblCustomerPhone.getStyleClass().add("dialogText");

        //input

        TextField txtCustomerName = new TextField();
        AnchorPane.setTopAnchor(txtCustomerName,50.0);
        AnchorPane.setRightAnchor(txtCustomerName,10.0);
        txtCustomerName.setPrefSize(300,20);
        txtCustomerName.getStyleClass().add("textField");


        TextField txtCustomerPhone = new TextField();
        AnchorPane.setTopAnchor(txtCustomerPhone,150.0);
        AnchorPane.setRightAnchor(txtCustomerPhone,10.0);
        txtCustomerPhone.setPrefSize(300,20);
        txtCustomerPhone.getStyleClass().add("textField");

        //error

        Label errCustomerName = new Label("خطا");
        AnchorPane.setTopAnchor(errCustomerName,80.0);
        AnchorPane.setRightAnchor(errCustomerName,20.0);
        errCustomerName.setPrefHeight(20);
        errCustomerName.getStyleClass().add("textError");
        errCustomerName.setVisible(false);

        Label errCustomerPhone = new Label("خطا");
        AnchorPane.setTopAnchor(errCustomerPhone,180.0);
        AnchorPane.setRightAnchor(errCustomerPhone,20.0);
        errCustomerPhone.setPrefHeight(20);
        errCustomerPhone.getStyleClass().add("textError");
        errCustomerPhone.setVisible(false);

        Button btnAccept = new Button("ثبت");
        AnchorPane.setBottomAnchor(btnAccept,10.0);
        AnchorPane.setLeftAnchor(btnAccept,10.0);
        btnAccept.setPrefHeight(20);

        btnAccept.setOnMouseClicked(event -> {

            if (validation(txtCustomerName, txtCustomerPhone, errCustomerName, errCustomerPhone)) {

                DB.Customers.Insert(new Customer(txtCustomerName.getText(), txtCustomerPhone.getText().trim(),gamesList));
                //update service cards
                CustomerLayout.Refresh();
                close();
            }
        });

        setupClearError(txtCustomerName,errCustomerName);
        setupClearError(txtCustomerPhone,errCustomerPhone);

        Button btnCancel = new Button("انصراف");
        AnchorPane.setBottomAnchor(btnCancel,10.0);
        AnchorPane.setLeftAnchor(btnCancel,50.0);
        btnCancel.setPrefHeight(20);

        btnAccept.getStyleClass().add("flatButton");
        btnCancel.getStyleClass().add("flatButton");





        Label lblGameName = new Label("بازی مورد علاقه");
        AnchorPane.setTopAnchor(lblGameName,220.0);
        AnchorPane.setRightAnchor(lblGameName,10.0);
        lblGameName.setPrefHeight(20);
        lblGameName.getStyleClass().add("dialogText");


        TextField txtGameName = new TextField();
        AnchorPane.setTopAnchor(txtGameName,250.0);
        AnchorPane.setRightAnchor(txtGameName,10.0);
        txtGameName.setPrefSize(250,20);
        txtGameName.getStyleClass().add("textField");

        Button btnAddGame=new Button();
        AnchorPane.setTopAnchor(btnAddGame,250.0);
        AnchorPane.setRightAnchor(btnAddGame,270.0);
        btnAddGame.getStyleClass().add("addAccPlusIconRectangle");
        btnAddGame.setPrefSize(28,20);

        Label errGameName=new Label("خطا");
        AnchorPane.setTopAnchor(errGameName,280.0);
        AnchorPane.setRightAnchor(errGameName,20.0);
        errGameName.setPrefHeight(20);
        errGameName.getStyleClass().add("textError");
        errGameName.setVisible(false);

        gameTagsFlow =new FlowPane(5,5);
        gameTagsFlow.setAlignment(Pos.TOP_CENTER);


        ScrollPane gameTagsScroll=new ScrollPane(gameTagsFlow);
        AnchorPane.setTopAnchor(gameTagsScroll,310.0);
        AnchorPane.setLeftAnchor(gameTagsScroll,10.0);
        AnchorPane.setRightAnchor(gameTagsScroll,10.0);
        AnchorPane.setBottomAnchor(gameTagsScroll,40.0);
        gameTagsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gameTagsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gameTagsScroll.setFitToWidth(true);
        gameTagsScroll.getStyleClass().add("customerCard_gameTagsScroll");



        gamesList=new ArrayList();
        btnAddGame.setOnMouseClicked(event -> {
            if (validation(txtGameName,errGameName)){
            gamesList.add(txtGameName.getText());
            txtGameName.setText("");
            Refresh();
            }
        });
        setupClearError(txtGameName,errGameName);



        root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.setPrefHeight(HEIGHT);
        root.getChildren().addAll(
                lblCustomerName,
                txtCustomerName,
                errCustomerName,
                lblCustomerPhone,
                txtCustomerPhone,
                errCustomerPhone,
                lblGameName,
                txtGameName,
                btnAddGame,
                errGameName,
                gameTagsScroll,
                btnAccept,
                btnCancel
        );

        setup(root, btnCancel, "افزودن مشتری", WIDTH, HEIGHT);
        show();
    }

    private boolean validation(TextField input1, TextField input2, Label err1, Label err2) {

        boolean isOk = true;
        if (input1.getText().isEmpty()) {
            err1.setVisible(true);
            err1.setText("خالی است!");
            isOk = false;
        }

        if (input2.getText().isEmpty()) {
            err2.setVisible(true);
            err2.setText("خالی است!");
            isOk = false;
        } else if (!isInt(input2.getText())) {
            err2.setVisible(true);
            err2.setText("عدد وارد کنید!");
            isOk = false;
        }

        return isOk;

    }

    private boolean validation(TextField inputGame,Label err){

        boolean isOk=true;

        if (inputGame.getText().isEmpty()){
            err.setVisible(true);
            err.setText("نام بازی را وارد کنید!");
            isOk=false;
        }

        return isOk;
    }

    private void setupClearError(TextField input1,Label err2) {

        input1.textProperty().addListener(observable -> err2.setVisible(false));
    }

    private boolean isInt(String str) {
        try {
            var a = Long.valueOf(str);
            return true;
        } catch (java.lang.NumberFormatException e) {
            return false;
        }
    }

    private void Refresh(){

        gameTagsFlow.getChildren().clear();

        for (String game:gamesList) {
            gameTagsFlow.getChildren().add(new GameTag(game,true,this));
        }

    }

    @Override
    public void OnDelete(String tagName) {
        gamesList.remove(tagName);
        Refresh();
    }
}
