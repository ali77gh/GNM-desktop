package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.CountBaseService;
import com.gnm.desktop.ui.dialog.AreYouSureDialog;
import com.gnm.desktop.ui.view.HalfCircle;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

class CountBaseListItem extends AnchorPane {

    private ServiceCardItemCallback callback;

    CountBaseListItem(CountBaseService countBaseService) {

        HalfCircle closeBtn = HalfCircle.getRemoveNewServiceCircle();
        closeBtn.setOnMouseClicked(mouseEvent -> {
            new AreYouSureDialog(() -> {
                callback.onDelete();
            });
        });
        AnchorPane.setRightAnchor(closeBtn, 0.0);


        Label name = new Label(countBaseService.getServiceName());
        name.getStyleClass().add("dialogText");
        AnchorPane.setRightAnchor(name, 40.0);
        AnchorPane.setTopAnchor(name, 10.0);

        HBox count = getPlusMinusNumber(countBaseService);
        AnchorPane.setRightAnchor(count, 165.0);
        AnchorPane.setTopAnchor(count, 10.0);

        Label paymentValue = new Label(countBaseService.getCurrentCost() + "T");
        paymentValue.getStyleClass().add("dialogText");
        AnchorPane.setLeftAnchor(paymentValue, 46.0);
        AnchorPane.setTopAnchor(paymentValue, 10.0);

        this.getChildren().addAll(
                closeBtn,
                name,
                count,
                paymentValue
        );
    }

    private HBox getPlusMinusNumber(CountBaseService countBaseService) {

        AnchorPane minus = getMinus();
        minus.setPrefSize(16, 16);
        minus.setMaxSize(16, 16);
        minus.setOnMouseClicked(mouseEvent -> {
            countBaseService.decreaseCount();
            callback.refreshRequest();
        });

        Label count = new Label(String.valueOf(countBaseService.getCount()));
//        count.getStyleClass().add("flatButton");
        count.setPadding(new Insets(0, 6, 0, 6));
        Label plus = new Label();
        plus.setPrefSize(16, 16);
        plus.setMaxSize(16, 16);
        plus.getStyleClass().add("plus");
        plus.setStyle("-fx-background-color : fx_primary;");
        plus.setOnMouseClicked(mouseEvent -> {
            countBaseService.increaseCount();
            callback.refreshRequest();
        });

        HBox hBox = new HBox(minus, count, plus);
        hBox.setFillHeight(false);
        return hBox;
    }

    private AnchorPane getMinus() {
        AnchorPane stackPane = new AnchorPane();

        Label minus = new Label();
        minus.setPrefSize(3, 3);
        minus.setMaxSize(3, 3);
        minus.setRotate(90);
        minus.getStyleClass().add("minus");
        minus.setStyle("-fx-background-color : fx_primary;");

        stackPane.getChildren().add(minus);
        return stackPane;
    }

    public void setCallback(ServiceCardItemCallback callback) {
        this.callback = callback;
    }
}
