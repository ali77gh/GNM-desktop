package com.gnm.desktop.ui.layout.customerlayout;


import com.gnm.desktop.data.model.Customer;
import com.gnm.desktop.ui.dialog.EditCustomerDialog;
import com.gnm.desktop.ui.view.GameTag;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;


public class CustomerCard extends AnchorPane {

    private final int HEIGHT=200;
    private final int WIDTH=200;

    private static FlowPane gameTagsFlow;

    public CustomerCard(Customer customer){


        getStyleClass().add("card");
        setPrefSize(WIDTH,HEIGHT);

        setOnMouseClicked(event -> {

            new EditCustomerDialog(customer);

        });


        Label goodAccCustomer=new Label();
        goodAccCustomer.setPrefHeight(35);
        AnchorPane.setTopAnchor(goodAccCustomer,0.0);
        AnchorPane.setLeftAnchor(goodAccCustomer,0.0);
        AnchorPane.setRightAnchor(goodAccCustomer,0.0);
        goodAccCustomer.getStyleClass().add("customerCard_goodAccCustomer");


        Label lblName=new Label(customer.name);
        lblName.setPrefHeight(20);
        AnchorPane.setTopAnchor(lblName,
                AnchorPane.getTopAnchor(goodAccCustomer)+goodAccCustomer.getPrefHeight()+/*marginTop*/10);
        AnchorPane.setLeftAnchor(lblName,0.0);
        AnchorPane.setRightAnchor(lblName,0.0);
        lblName.setAlignment(Pos.CENTER);
        lblName.setPadding(new Insets(0,10,0,10));
        lblName.getStyleClass().add("size14");

        Label lblPhoneNumber=new Label(customer.phone);
        lblPhoneNumber.setPrefHeight(20);
        AnchorPane.setTopAnchor(lblPhoneNumber,
                AnchorPane.getTopAnchor(lblName)+lblName.getPrefHeight()+/*marginTop*/8);
        AnchorPane.setLeftAnchor(lblPhoneNumber,0.0);
        AnchorPane.setRightAnchor(lblPhoneNumber,0.0);
        lblPhoneNumber.setAlignment(Pos.CENTER);
        lblPhoneNumber.setPadding(new Insets(0,10,0,10));
        lblPhoneNumber.getStyleClass().add("size14");

        Label lblCredit=new Label();
        lblCredit.setPrefSize(WIDTH,20);
        AnchorPane.setTopAnchor(lblCredit,
                AnchorPane.getTopAnchor(lblPhoneNumber)+lblPhoneNumber.getPrefHeight()+/*marginTop*/5);
        AnchorPane.setLeftAnchor(lblCredit,0.0);
        AnchorPane.setRightAnchor(lblCredit,0.0);
        lblCredit.setText(customer.credit+"T");
        lblCredit.setAlignment(Pos.CENTER);
        lblCredit.setPadding(new Insets(0,10,0,10));
        lblCredit.getStyleClass().add("size20");


        gameTagsFlow =new FlowPane();
        gameTagsFlow.setHgap(5);
        gameTagsFlow.setVgap(5);
        for (String game:customer.games) {
            gameTagsFlow.getChildren().add(new GameTag(game));
        }

        ScrollPane gameTagsScroll=new ScrollPane(gameTagsFlow);
        gameTagsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gameTagsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        gameTagsScroll.setFitToWidth(true);
        gameTagsScroll.getStyleClass().add("customerCard_gameTagsScroll");
        AnchorPane.setTopAnchor(gameTagsScroll,
                AnchorPane.getTopAnchor(lblCredit)+lblCredit.getPrefHeight()+/*marginTop*/18);
        AnchorPane.setLeftAnchor(gameTagsScroll,10.0);
        AnchorPane.setRightAnchor(gameTagsScroll,10.0);
        AnchorPane.setBottomAnchor(gameTagsScroll,8.0);


        getChildren().addAll(goodAccCustomer,lblName,lblPhoneNumber,lblCredit,gameTagsScroll);
    }
}
