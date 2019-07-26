package com.gnm.desktop.ui.layout.customerlayout;

import com.gnm.desktop.data.model.Customer;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CustomerCard extends VBox {


    private final int HEIGHT=360;
    private final int WIDTH=180;
    public CustomerCard(Customer customer){



        super(5);
        setStyle("-fx-background-color: #202225;"+
                "-fx-background-radius: 20;");


        Label goodAccCustomer=new Label();
        goodAccCustomer.setPrefSize(WIDTH,HEIGHT/6.4);
        goodAccCustomer.setStyle("-fx-background-color: #ff5555;"+
                "-fx-background-radius: 20 20 0 0;");


        Label lblName=new Label(customer.name);
        lblName.setPrefSize(WIDTH,HEIGHT/10.6);
        lblName.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        lblName.setAlignment(Pos.BOTTOM_CENTER);
        lblName.setPadding(new Insets(0,10,0,10));
        lblName.setStyle("-fx-text-fill: #fff;");

        Label lblPhoneNumber=new Label(customer.phone);
        lblPhoneNumber.setPrefSize(WIDTH,HEIGHT/8);
        lblPhoneNumber.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        lblPhoneNumber.setAlignment(Pos.CENTER);
        lblPhoneNumber.setPadding(new Insets(0,10,0,10));
        lblPhoneNumber.setStyle("-fx-text-fill: #fff");

        Label lblCredit=new Label();
        lblCredit.setText(customer.credit+"T");
        lblCredit.setPrefSize(WIDTH,HEIGHT/8);
        lblCredit.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        lblCredit.setAlignment(Pos.TOP_CENTER);
        lblCredit.setPadding(new Insets(0,10,0,10));
        lblCredit.setStyle("-fx-text-fill: #fff");



        getChildren().addAll(goodAccCustomer,lblName,lblPhoneNumber,lblCredit);


    }
}
