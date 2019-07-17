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

public class EditServiceDialog extends BaseDialog {

    public EditServiceDialog(PricePerHour pph) {


        Label lblServiceName = new Label("سرویس :");

        Label lblServicePrice = new Label("قیمت(تومن):");

        TextField txtServiceName = new TextField();

        TextField txtServicePrice = new TextField();

        Button btnEdit = new Button("ویرایش");

        btnEdit.setOnMouseClicked(event -> {
            pph.name = txtServiceName.getText();
            pph.pricePerHour = Integer.valueOf(txtServicePrice.getText());
            DB.Prices.Update(pph);
            PricesLayout.makeTimeBaseServiceCards();
            close();
        });

        Button btnDelete = new Button("حذف");
        btnDelete.setOnMouseClicked(event -> {
            DB.Prices.Remove(pph.getId());
            //update service cards
            PricesLayout.makeTimeBaseServiceCards();
            close();
        });


        Button btnCancel = new Button("انصراف");


        GridPane root = new GridPane();
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.add(lblServiceName, 0, 0);
        root.add(txtServiceName, 1, 0);
        root.add(lblServicePrice, 0, 1);
        root.add(txtServicePrice, 1, 1);
        root.add(btnEdit, 0, 2);
        root.add(btnDelete, 1, 2);
        root.add(btnCancel,2,2);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(15);

        setup(root, btnCancel, 400, 150);
        show();
    }
}
