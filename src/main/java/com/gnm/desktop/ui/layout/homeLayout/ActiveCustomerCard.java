package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.data.model.ActiveCustomer;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


class ActiveCustomerCard extends AnchorPane {

    private ActiveCustomerCardListener cb;

    ActiveCustomerCard(ActiveCustomer activeCustomer) {
        super();

        this.getStyleClass().add("pricesLayout_addCardTbs");

        this.setMaxSize(350, 100);
        this.setPrefSize(350, 100);

        Label label = new Label(activeCustomer.getCustomerName());


        this.getChildren().add(label);

        label.setOnMouseClicked(mouseEvent -> cb.onDelete());

    }

    public void setListener(ActiveCustomerCardListener cb) {
        this.cb = cb;
    }

    public interface ActiveCustomerCardListener {
        void onDelete();
    }
}
