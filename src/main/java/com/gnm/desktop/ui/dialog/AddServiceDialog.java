package com.gnm.desktop.ui.dialog;


import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.ui.layout.priceslayout.PricesLayout;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddServiceDialog extends BaseDialog {


    public AddServiceDialog() {


        Label lblServiceName=new Label("سرویس :");

        Label lblServicePrice=new Label("قیمت(تومن):");

        TextField txtServiceName=new TextField();

        TextField txtServicePrice=new TextField();

        Button btnAccept=new Button("ثبت");

        btnAccept.setOnMouseClicked(event -> {
            DB.Prices.Insert(new PricePerHour(txtServiceName.getText(),Integer.valueOf(txtServicePrice.getText())));
            //update service cards
            PricesLayout.makeTimeBaseServiceCards();
            this.close();
        });

        Button btnCancel=new Button("انصراف");

        btnCancel.setOnMouseClicked(event -> this.close());

        GridPane root=new GridPane();
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.add(lblServiceName,0,0);
        root.add(txtServiceName,1,0);
        root.add(lblServicePrice,0,1);
        root.add(txtServicePrice,1,1);
        root.add(btnAccept,0,2);
        root.add(btnCancel,1,2);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(15);

        setup(root, btnCancel, 300, 150);
        show();
    }

}
