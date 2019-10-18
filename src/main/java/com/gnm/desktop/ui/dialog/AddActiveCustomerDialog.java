package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.core.Validation;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.ActiveCustomer;
import com.gnm.desktop.data.model.Customer;
import com.gnm.desktop.ui.view.AutoCompleteTextField;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddActiveCustomerDialog extends BaseDialog {

    private static final int STATE_GUEST = 0;
    private static final int STATE_NON_GUEST = 1;

    private int state = STATE_GUEST; // default

    public AddActiveCustomerDialog(ActiveCustomerDialogCallback cb) {

        //radio btns
        RadioButton radioButton1 = new RadioButton("مهمان");
        RadioButton radioButton2 = new RadioButton("مشتری ثابت");
        radioButton1.setStyle("-fx-text-fill : #FFFFFF");
        radioButton2.setStyle("-fx-text-fill : #FFFFFF");
        ToggleGroup radioGroup = new ToggleGroup();
        radioButton1.setToggleGroup(radioGroup);
        radioButton2.setToggleGroup(radioGroup);
        HBox radioBox = new HBox(10, radioButton1, radioButton2);
        radioButton1.setSelected(true); // default


        //label
        Label label = new Label("نام مشتری :");
        label.getStyleClass().add("dialogText");
        label.setAlignment(Pos.CENTER_RIGHT);

        Label customerName = new Label("");
        customerName.setStyle("-fx-text-fill : #FFFFFF");
        label.setAlignment(Pos.CENTER_RIGHT);


        //input
        AutoCompleteTextField input = new AutoCompleteTextField();
        input.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        input.getStyleClass().add("textField");


        //error
        Label inputErr = new Label("خطا");
        inputErr.getStyleClass().add("textError");
        inputErr.setVisible(false);


        //buttons
        Button btnAdd = new Button("اضافه کردن");
        btnAdd.getStyleClass().add("flatButton");
        btnAdd.setOnMouseClicked(event -> {

            ActiveCustomer ac;

            switch (state) {

                case STATE_GUEST:
                    if (Validation.checkEmpty(input, inputErr)) {
                        ac = new ActiveCustomer(input.getText());
                    } else
                        return; // don't let code continue
                    break;

                case STATE_NON_GUEST:
                    Customer customer = DB.Customers.getByPhone(input.getText().replace(" ", ""));

                    if (Validation.checkPhoneNumberExist(input, inputErr, customer)) {
                        ac = new ActiveCustomer(customer.getId(), customer.name);
                    } else
                        return; // don't let code continue

                    break;

                default:
                    throw new RuntimeException("invalid state");
            }

            DB.ActiveCustomers.Insert(ac);
            cb.onNewActiveCustomer(ac);
            this.close();
        });

        Button btnCancel = new Button("انصراف");
        btnCancel.getStyleClass().add("flatButton");


        // get things together

        input.textProperty().addListener((observableValue, s, t1) -> {
            customerName.setText(""); // clear
            if (state == STATE_NON_GUEST) {
                Customer customer = DB.Customers.getByPhone(input.getText());
                if (customer != null) {
                    customerName.setText("نام مشتری: " + customer.name);
                }
            }
        });

        labelToRadioBind(input, label, radioButton1, radioButton2);
        var root = new VBox(15);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.getChildren().addAll(
                radioBox,
                label,
                input,
                inputErr,
                customerName
        );
        var btns = new HBox(btnAdd, btnCancel);
        btns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.getChildren().add(btns);
        root.setAlignment(Pos.CENTER_LEFT);

        setup(root, btnCancel, "افزودن مشتری فعال جدید", 300, 220);
        show();
    }

    private void labelToRadioBind(AutoCompleteTextField input, Label label, RadioButton r1, RadioButton r2) {

        r1.setOnMouseClicked(mouseEvent -> {
            label.setText("نام مشتری :");
            state = STATE_GUEST;
            input.getEntries().clear();
        });

        r2.setOnMouseClicked(mouseEvent -> {
            label.setText("شماره تلفن مشتری :");
            state = STATE_NON_GUEST;
            input.getEntries().addAll(DB.Customers.getAllPhones());
        });
    }

    public interface ActiveCustomerDialogCallback {
        void onNewActiveCustomer(ActiveCustomer activeCustomer);
    }
}
