package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.core.AppRefresh;
import com.gnm.desktop.core.Validation;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.Customer;
import com.gnm.desktop.ui.layout.customerlayout.CustomerLayout;
import com.gnm.desktop.ui.view.AutoCompleteTextField;
import com.gnm.desktop.ui.view.GameTag;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

import static com.gnm.desktop.ui.layout.rightMenu.Items.MONITOR;

public class EditCustomerDialog extends BaseDialog implements GameTag.deletableGameTag {

    private final int HEIGHT = 500;
    private final int WIDTH = 450;

    private List<String> gamesList;
    private static FlowPane gameTagsFlow;


    public EditCustomerDialog(Customer customer) {


        AnchorPane root = new AnchorPane();


        //label

        Label lblCustomerName = new Label("نام");
        AnchorPane.setTopAnchor(lblCustomerName, 0.0);
        AnchorPane.setRightAnchor(lblCustomerName, 10.0);
        lblCustomerName.setPrefHeight(20);
        lblCustomerName.getStyleClass().add("dialogText");

        Label lblCustomerPhone = new Label("تلفن");
        AnchorPane.setTopAnchor(lblCustomerPhone, 90.0);
        AnchorPane.setRightAnchor(lblCustomerPhone, 10.0);
        lblCustomerPhone.setPrefHeight(20);
        lblCustomerPhone.getStyleClass().add("dialogText");


        Label lblCredit = new Label("موجودی حساب");
        AnchorPane.setTopAnchor(lblCredit, 180.0);
        AnchorPane.setRightAnchor(lblCredit, 10.0);
        lblCredit.setPrefHeight(20);
        lblCredit.getStyleClass().add("dialogText");

        //input

        TextField txtCustomerName = new TextField(customer.name);
        AnchorPane.setTopAnchor(txtCustomerName, 30.0);
        AnchorPane.setRightAnchor(txtCustomerName, 10.0);
        txtCustomerName.setPrefSize(300, 20);
        txtCustomerName.getStyleClass().add("textField");


        TextField txtCustomerPhone = new TextField(customer.phone);
        AnchorPane.setTopAnchor(txtCustomerPhone, 120.0);
        AnchorPane.setRightAnchor(txtCustomerPhone, 10.0);
        txtCustomerPhone.setPrefSize(300, 20);
        txtCustomerPhone.getStyleClass().add("textField");

        TextField txtCredit = new TextField(String.valueOf(customer.credit));
        AnchorPane.setTopAnchor(txtCredit, 210.0);
        AnchorPane.setRightAnchor(txtCredit, 10.0);
        txtCredit.setPrefSize(300, 20);
        txtCredit.getStyleClass().add("textField");

        //error

        Label errCustomerName = new Label("خطا");
        AnchorPane.setTopAnchor(errCustomerName, 60.0);
        AnchorPane.setRightAnchor(errCustomerName, 20.0);
        errCustomerName.setPrefHeight(20);
        errCustomerName.getStyleClass().add("textError");
        errCustomerName.setVisible(false);

        Label errCustomerPhone = new Label("خطا");
        AnchorPane.setTopAnchor(errCustomerPhone, 150.0);
        AnchorPane.setRightAnchor(errCustomerPhone, 20.0);
        errCustomerPhone.setPrefHeight(20);
        errCustomerPhone.getStyleClass().add("textError");
        errCustomerPhone.setVisible(false);

        Label errCredit = new Label("خطا");
        AnchorPane.setTopAnchor(errCredit, 240.0);
        AnchorPane.setRightAnchor(errCredit, 20.0);
        errCredit.setPrefHeight(20);
        errCredit.getStyleClass().add("textError");
        errCredit.setVisible(false);

        Button Edit = new Button("ویرایش");
        AnchorPane.setBottomAnchor(Edit, 10.0);
        AnchorPane.setLeftAnchor(Edit, 10.0);
        Edit.setPrefHeight(20);

        Edit.setOnMouseClicked(event -> {

            if (Validation.checkEmpty(txtCustomerName, errCustomerName) &
                    Validation.checkEmptyAndNumeric(txtCustomerPhone, errCustomerPhone) &
                    Validation.checkEmptyAndNumeric(txtCredit, errCredit)) {

                customer.name = txtCustomerName.getText();
                customer.phone = txtCustomerPhone.getText().trim();
                customer.credit = Integer.valueOf(txtCredit.getText());
                customer.games = gamesList;
                DB.Customers.Update(customer);
                //update service cards
                CustomerLayout.Refresh();
                close();
            }

            AppRefresh.pleaseRefresh(MONITOR);
        });


        Button btnDelete = new Button("حذف");
        AnchorPane.setBottomAnchor(btnDelete, 10.0);
        AnchorPane.setLeftAnchor(btnDelete, 80.0);
        btnDelete.setPrefHeight(20);
        btnDelete.setOnMouseClicked(event -> {
            DB.Customers.Remove(customer.getId());
            CustomerLayout.Refresh();
            close();
        });


        Button btnCancel = new Button("انصراف");
        AnchorPane.setBottomAnchor(btnCancel, 10.0);
        AnchorPane.setLeftAnchor(btnCancel, 130.0);
        btnCancel.setPrefHeight(20);


        Edit.getStyleClass().add("flatButton");
        btnCancel.getStyleClass().add("flatButton");
        btnDelete.getStyleClass().add("flatButtonDelete");


        Label lblGameName = new Label("بازی مورد علاقه");
        AnchorPane.setTopAnchor(lblGameName, 260.0);
        AnchorPane.setRightAnchor(lblGameName, 10.0);
        lblGameName.setPrefHeight(20);
        lblGameName.getStyleClass().add("dialogText");


        AutoCompleteTextField txtGameName = new AutoCompleteTextField();
        AnchorPane.setTopAnchor(txtGameName, 290.0);
        AnchorPane.setRightAnchor(txtGameName, 10.0);
        txtGameName.setPrefSize(250, 20);
        txtGameName.getStyleClass().add("textField");
        txtGameName.getEntries().addAll(DB.Customers.getGames());

        Button btnAddGame = new Button();
        AnchorPane.setTopAnchor(btnAddGame, 290.0);
        AnchorPane.setRightAnchor(btnAddGame, 270.0);
        btnAddGame.getStyleClass().add("addAccPlusIconRectangle");
        btnAddGame.setPrefSize(28, 20);

        Label errGameName = new Label("خطا");
        AnchorPane.setTopAnchor(errGameName, 320.0);
        AnchorPane.setRightAnchor(errGameName, 20.0);
        errGameName.setPrefHeight(20);
        errGameName.getStyleClass().add("textError");
        errGameName.setVisible(false);

        gameTagsFlow = new FlowPane(5, 5);
        gameTagsFlow.setAlignment(Pos.TOP_CENTER);


        ScrollPane gameTagsScroll = new ScrollPane(gameTagsFlow);
        AnchorPane.setTopAnchor(gameTagsScroll, 350.0);
        AnchorPane.setLeftAnchor(gameTagsScroll, 10.0);
        AnchorPane.setRightAnchor(gameTagsScroll, 10.0);
        AnchorPane.setBottomAnchor(gameTagsScroll, 40.0);
        gameTagsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gameTagsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gameTagsScroll.setFitToWidth(true);
        gameTagsScroll.getStyleClass().add("customerCard_gameTagsScroll");


        gamesList = new ArrayList<>(customer.games);
        Refresh();
        btnAddGame.setOnMouseClicked(event -> {
            if (Validation.checkEmpty(txtGameName, errGameName)) {
                gamesList.add(txtGameName.getText());
                txtGameName.setText("");
                Refresh();
            }
        });

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
                lblCredit,
                txtCredit,
                errCredit,
                gameTagsScroll,
                Edit,
                btnDelete,
                btnCancel
        );

        setup(root, btnCancel, "افزودن مشتری", WIDTH, HEIGHT);
        show();
    }

    private void Refresh() {

        gameTagsFlow.getChildren().clear();

        for (String game : gamesList) {
            gameTagsFlow.getChildren().add(new GameTag(game, true, this));
        }
    }

    @Override
    public void OnDelete(String tagName) {
        gamesList.remove(tagName);
        Refresh();
    }
}
