package com.gnm.desktop.ui.layout.priceslayout;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.res.css.CSSStyler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupEditService {

    public PopupEditService(PricePerHour pph) {
        Stage pop = new Stage();

        Label lblServiceName = new Label("سرویس :");

        Label lblServicePrice = new Label("قیمت(تومن):");

        TextField txtServiceName = new TextField();

        TextField txtServicePrice = new TextField();

        Button btnEdit = new Button("ویرایش");

        btnEdit.setOnMouseClicked(event -> {
            pph.name=txtServiceName.getText();
            pph.pricePerHour=Integer.valueOf(txtServicePrice.getText());
            DB.Prices.Update(pph);
            PricesLayout.makeTimeBaseServiceCards();
            pop.close();
        });

        Button btnDelete = new Button("حذف");
        btnDelete.setOnMouseClicked(event -> {
            DB.Prices.Remove(pph.getId());
            //update service cards
            PricesLayout.makeTimeBaseServiceCards();
            pop.close();
        });


        Button btnCancel = new Button("انصراف");
        btnCancel.setOnMouseClicked(event -> pop.close());


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
        root.setStyle("-fx-background-radius: 20 20 20 20");
        root.setHgap(10);
        root.setVgap(15);


        Scene scene = new Scene(root, 400, 150);
        scene.getStylesheets().add(CSSStyler.get("popupPriceLayout.css"));


        pop.initModality(Modality.APPLICATION_MODAL);
        pop.setTitle("مشخصات سرویس");
        pop.setScene(scene);
        pop.setAlwaysOnTop(true);
        pop.centerOnScreen();
        pop.initStyle(StageStyle.UNIFIED);
        pop.show();
    }
}
