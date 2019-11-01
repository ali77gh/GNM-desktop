package com.gnm.desktop.ui.layout;

import com.gnm.desktop.ui.AppCSS;
import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import static com.gnm.desktop.Main.APP;

public class RegisterLayout {

    private static final int width = 900;
    private static final int height = 600;

    public static void show() {


        Pane layout = getLayout();

        AppCSS.load(layout);

        //scene
        Scene scene = new Scene(layout, width, height);

        //stage
        Stage pop = new Stage();
        pop.setScene(scene);
        pop.centerOnScreen();
        pop.initStyle(StageStyle.DECORATED);
        pop.setMaxWidth(width);
        pop.setMaxHeight(height);
        pop.setMinWidth(width);
        pop.setMinHeight(height);

        pop.show();
    }

    private static Pane getLayout() {
        return new HBox(
                getForm(),
                getTexture()
        );
    }

    private static Pane getForm() {

        //lock icon
        Label lockIcon = new Label();
        lockIcon.getStyleClass().addAll("lock", "menuItem_icon");
        lockIcon.setPrefSize(25, 30);

        //label
        Label title = new Label("برنامه نیاز به فعال سازی دارد");
        title.setStyle("-fx-font-size: 17;");
        title.getStyleClass().add("dialogText");
        title.setPadding(new Insets(0, 0, 60, 0));

        //label
        Label inputLabel = new Label("کلید خود را وارد کنید:");
        inputLabel.getStyleClass().add("dialogText");


        //input
        TextField input = new TextField();
        input.getStyleClass().add("textField");
        input.setPrefWidth(250);
        input.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);


        //button
        Button testKey = new Button("ذخیره کلید");
        testKey.getStyleClass().add("flatButton");
        testKey.setOnMouseClicked(event -> {

            //todo check key
            if (input.getText().equals("key")) {
                spine(lockIcon);
            } else {
                shake(lockIcon);
            }
        });

        Button generateKey = new Button("صدور کلید");
        generateKey.getStyleClass().add("flatButton");
        generateKey.setOnMouseClicked(mouseEvent -> {
            openWebsite();
        });


        //label
        Label yourHardwareId = new Label("شناسه سخت افزار شما: asdfghjklzxcvbnm");
        yourHardwareId.getStyleClass().add("dialogText");


        Label copy = new Label("کپی");
        copy.getStyleClass().add("flatButton");

        HBox hardwareId = new HBox(yourHardwareId, copy);
        hardwareId.setPadding(new Insets(190, 0, 20, 0));

        //layout
        var form = new VBox(15);
        form.setFillWidth(false);
        form.setPrefWidth(width / 2);
        form.setStyle("-fx-background-color: fx_lightBlack;");
        form.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        form.getChildren().addAll(
                lockIcon,
                title,
                inputLabel,
                input,
                new HBox(testKey, generateKey),
                hardwareId
        );
        form.setAlignment(Pos.BOTTOM_CENTER);
        return form;
    }

    private static Pane getTexture() {


        VBox texture = new VBox();
        texture.setPrefWidth(width / 2);
        texture.setStyle("-fx-background-color: fx_primary;");
        texture.setAlignment(Pos.CENTER);
        return texture;
    }

    private static void shake(Node node) {

        RotateTransition tt = new RotateTransition(Duration.millis(100));

        tt.stop();
        tt.setNode(node);
        tt.setToAngle(20);
        tt.setFromAngle(0);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.playFromStart();
    }

    private static void spine(Node node) {
        RotateTransition tt = new RotateTransition(Duration.millis(250));

        tt.stop();
        tt.setNode(node);
        tt.setToAngle(360);
        tt.setFromAngle(0);
        tt.playFromStart();
    }

    private static void openWebsite() {
        APP.getHostServices().showDocument("http://www.google.com");
    }
}
