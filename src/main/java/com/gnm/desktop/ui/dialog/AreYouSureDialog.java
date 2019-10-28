package com.gnm.desktop.ui.dialog;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AreYouSureDialog extends BaseDialog {

    public AreYouSureDialog(AreYouSureDialogCallback cb, String msg) {

        //label

        Label lblServiceName = new Label(msg);
        lblServiceName.getStyleClass().add("dialogText");
        lblServiceName.setAlignment(Pos.CENTER_RIGHT);


        Button btnConfirm = new Button("تایید");
        Button btnCancel = new Button("انصراف");
        btnConfirm.getStyleClass().add("flatButton");
        btnCancel.getStyleClass().add("flatButton");

        btnConfirm.setOnMouseClicked(event -> {
            cb.onConfirm();
            this.close();
        });

        var root = new VBox(15);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.getChildren().addAll(
                lblServiceName
        );
        var btns = new HBox(btnConfirm, btnCancel);
        btns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.getChildren().add(btns);
        root.setAlignment(Pos.CENTER_LEFT);


        super.setup(root, btnCancel, "تایید", 300, 90);
        show();
    }

    public AreYouSureDialog(AreYouSureDialogCallback cb) {
        this(cb, "آیا مطمئن هستید؟");
    }

    public interface AreYouSureDialogCallback {
        void onConfirm();
    }
}
