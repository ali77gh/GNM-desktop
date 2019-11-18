package com.gnm.desktop.ui.layout;

import com.gnm.desktop.core.Registration;
import com.gnm.desktop.core.SystemDetails;
import com.gnm.desktop.core.ThreadHelper;
import com.gnm.desktop.data.FileIO;
import com.gnm.desktop.ui.AppCSS;
import com.gnm.desktop.ui.view.RandomTriangles;
import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;

import static com.gnm.desktop.Main.APP;

public class RegisterLayout {

    public static final int width = 900;
    public static final int height = 600;

    private static String systemDetails = SystemDetails.getHardwareId();
    private static Stage stage;
    private static Label lockIcon;
    private static RegisterLayoutCallback cb;

    public static void show(RegisterLayoutCallback callback) {

        cb = callback;

        Pane layout = getLayout();

        AppCSS.load(layout);

        //scene
        Scene scene = new Scene(layout, width, height);

        //stage
        stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaxWidth(width);
        stage.setMaxHeight(height);
        stage.setMinWidth(width);
        stage.setMinHeight(height);

        stage.show();
    }

    private static Pane getLayout() {
        return new HBox(
                getForm(),
                getTexture()
        );
    }

    private static Pane getTexture() {

        StackPane texture = new StackPane();
        texture.setPrefWidth(width / 2);
        texture.setStyle("-fx-background-color: fx_primary;");
        texture.setAlignment(Pos.CENTER);

        Label appName = new Label("Snack");
        appName.setStyle("-fx-font-size: 37;");
        appName.getStyleClass().addAll("dialogText");
        appName.setPadding(new Insets(15, 0, 0, 0));

        texture.getChildren().addAll(
                RandomTriangles.getForRegister(),
                appName);
        return texture;
    }

    private static Pane getForm() {

        Label space = new Label();
        space.setPrefHeight(120);

        //lock icon
        lockIcon = new Label();
        lockIcon.getStyleClass().addAll("lock", "menuItem_icon");
        lockIcon.setPrefSize(25, 30);


        //label
        Label title = new Label("برنامه نیاز به فعال سازی دارد");
        title.setStyle("-fx-font-size: 17;");
        title.getStyleClass().addAll("dialogText");
        title.setPadding(new Insets(15, 0, 0, 0));

        // checkbox
        CheckBox checkBox = new CheckBox("کلید دارم");
        checkBox.getStyleClass().add("dialogText");

        // dynamic layout
        StackPane dynamicLayout = new StackPane();

        checkBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            dynamicLayout.getChildren().clear();
            dynamicLayout.getChildren().add(checkBox.isSelected() ? getIHaveKeyLayout() : getIHaveNoKeyLayout());
        });
        //default
        dynamicLayout.getChildren().add(getIHaveNoKeyLayout());

        //layout
        var form = new VBox(15);
        form.setFillWidth(false);
        form.setPrefWidth(width / 2);
        form.setStyle("-fx-background-color: fx_lightBlack;");
        form.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        form.getChildren().addAll(
                space,
                lockIcon,
                title,
                checkBox,
                dynamicLayout
        );
        form.setAlignment(Pos.TOP_CENTER);
        return form;
    }

    private static Pane getIHaveNoKeyLayout() {

        //label
        Label yourHardwareId = new Label("شناسه سخت افزار شما: " + systemDetails);
        yourHardwareId.getStyleClass().addAll("dialogText");

        Label copy = new Label("کپی");
        copy.getStyleClass().addAll("flatButton");
        copy.setOnMouseClicked(mouseEvent -> {
            copyToClipboardText(systemDetails);
        });

        HBox hardwareId = new HBox(yourHardwareId, copy);
        hardwareId.setPadding(new Insets(30, 0, 0, 0));

        Label openWebsite = new Label("صدور کلید");
        openWebsite.getStyleClass().addAll("flatButton");
        openWebsite.setOnMouseClicked(mouseEvent -> {
            openWebsite();
        });

        VBox vBox = new VBox(
                hardwareId,
                openWebsite
        );
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private static Pane getIHaveKeyLayout() {

        //label
        Label yourHardwareId = new Label("کلید معتبر وجود ندارد: ");
        yourHardwareId.getStyleClass().addAll("dialogText");

        Label copy = new Label("انتخاب کلید");
        copy.getStyleClass().addAll("flatButton");
        copy.setOnMouseClicked(mouseEvent -> {
            importKey();

            ThreadHelper.delayInUI(1000, () -> {
                if (Registration.verify()) {
                    spine(lockIcon);
                    ThreadHelper.delayInUI(1000, () -> {
                        cb.onRegistered();
                        stage.close();
                    });
                } else {
                    shake(lockIcon);
                }
            });
        });

        HBox hardwareId = new HBox(yourHardwareId, copy);
        hardwareId.setPadding(new Insets(30, 0, 0, 0));

        return hardwareId;
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
        APP.getHostServices().showDocument("http://www.snackapp.ir");//todo set prices path
    }

    private static void importKey() {

        File file = new FileChooser().showOpenDialog(stage);

        FileIO.CopySignFileHere(file);
    }

    private static void copyToClipboardText(String s) {

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        content.putString(s);
        clipboard.setContent(content);

    }

    public interface RegisterLayoutCallback {
        void onRegistered();
    }
}
