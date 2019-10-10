package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.ThreadHelper;
import com.gnm.desktop.core.calculator.CountBaseService;
import com.gnm.desktop.core.calculator.Service;
import com.gnm.desktop.core.calculator.TimeBaseService;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.ActiveCustomer;
import com.gnm.desktop.ui.view.HalfCircle;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


class ActiveCustomerCard extends AnchorPane {

    private static final int REFRESH_LOOP_TIME = 10000;

    private ActiveCustomerCardListener cb;
    private ActiveCustomer activeCustomer;
    private VBox vBox;

    ActiveCustomerCard(ActiveCustomer activeCustomer) {
        vBox = new VBox(15);
        this.activeCustomer = activeCustomer;

        setupRoot();

        Render();
        new Thread(() -> {
            while (true) {
                ThreadHelper.sleep(REFRESH_LOOP_TIME);
                Platform.runLater(this::Render);
            }
        }).start();
    }

    private void Render() {

        vBox.getChildren().clear(); // clear all old views

        setupHeader(activeCustomer.getCustomerName());
        setupServices();
        setupCostSum();
        setupButtons();
    }

    private void update() {
        DB.ActiveCustomers.Update(activeCustomer);
        Render();
    }

    private void setupServices() {

        if (activeCustomer.getServices().size() == 0) {
            Label label = new Label();
            label.getStyleClass().add("dialogText");
            label.setText("سرویسی وجود ندارد");
            vBox.getChildren().add(label);
            return;
        }
        for (Service service : activeCustomer.getServices()) {


            if (service instanceof TimeBaseService) {

                TimeBaseListItem item = new TimeBaseListItem((TimeBaseService) service);
                item.setCallback(new ServiceCardCallback() {
                    @Override
                    public void onDelete() {

                    }

                    @Override
                    public void refreshRequest() {

                    }
                });
                vBox.getChildren().add(item);

            } else if (service instanceof CountBaseService) {

                CountBaseListItem item = new CountBaseListItem((CountBaseService) service);
                item.setCallback(new ServiceCardCallback() {
                    @Override
                    public void onDelete() {

                    }

                    @Override
                    public void refreshRequest() {

                    }
                });
                vBox.getChildren().add(item);

            }
        }
    }

    private void setupCostSum() {

        Pane line = new Pane();
        line.getStyleClass().add("tbsCard_line");
        line.setMaxWidth(290);
        line.setPrefWidth(290);
        line.setPrefHeight(1);
        AnchorPane.setTopAnchor(line, 8.0);
        AnchorPane.setRightAnchor(line, 16.0);

        Label plus = new Label();
        plus.setPrefSize(16, 16);
        plus.getStyleClass().add("plus");
        plus.setStyle("-fx-background-color : fx_primary;");
        AnchorPane.setTopAnchor(plus, 0.0);
        AnchorPane.setLeftAnchor(plus, 16.0);

        Label costSumLable = new Label();
        costSumLable.setText("جمع کل:");
        costSumLable.getStyleClass().add("dialogText");
        AnchorPane.setRightAnchor(costSumLable, 16.0);
        AnchorPane.setBottomAnchor(costSumLable, 0.0);

        Label costSum = new Label();
        costSum.setText(activeCustomer.getCostSum() + "T");
        costSum.getStyleClass().add("dialogText");
        AnchorPane.setLeftAnchor(costSum, 46.0);
        AnchorPane.setBottomAnchor(costSum, 0.0);


        AnchorPane parent = new AnchorPane(
                line,
                plus,
                costSumLable,
                costSum
        );
        parent.setMinHeight(40);

        vBox.getChildren().add(parent);
    }

    //close btn and add new service btn
    private void setupButtons() {

        Label close = new Label();
        close.setPrefWidth(15);
        close.setPrefHeight(15);
        close.getStyleClass().add("close_icon");
        close.getStyleClass().add("close");
        AnchorPane.setTopAnchor(close, 10.0);
        AnchorPane.setLeftAnchor(close, 10.0);
        this.getChildren().add(close);
        close.setOnMouseClicked(mouseEvent -> {
            //todo show dialog before delete
            cb.onDelete();
        });


        HalfCircle hc = HalfCircle.getAddNewServiceCircle();
        AnchorPane.setBottomAnchor(hc, 0.0);
        AnchorPane.setRightAnchor(hc, 145.0);
        hc.setOnMouseClicked(mouseEvent -> {
            //todo show dialog and add then call refresh()
        });
        this.getChildren().add(hc);
    }

    private void setupRoot() {
        vBox.setPadding(new Insets(20, 0, 45, 0));
        vBox.setMaxSize(350, VBox.USE_PREF_SIZE);
        vBox.setPrefSize(350, VBox.USE_PREF_SIZE);
        vBox.setAlignment(Pos.CENTER);
        vBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        this.getStyleClass().add("pricesLayout_addCardTbs");
        this.setMaxSize(350, VBox.USE_PREF_SIZE);
        this.setPrefSize(350, VBox.USE_PREF_SIZE);
        this.getChildren().add(vBox);
    }

    private void setupHeader(String customerName) {
        Pane line = new Pane();
        line.getStyleClass().add("tbsCard_line");
        line.setMaxWidth(150);
        line.setPrefHeight(2);

        Label label = new Label(customerName);
        label.setPrefHeight(20);
        label.getStyleClass().add("tbsCard_lblName");

        vBox.getChildren().addAll(
                label,
                line
        );
    }

    void setListener(ActiveCustomerCardListener cb) {
        this.cb = cb;
    }

    public interface ActiveCustomerCardListener {
        void onDelete();
    }
}
