package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.core.calculator.CountBaseService;
import com.gnm.desktop.core.calculator.PaymentBaseService;
import com.gnm.desktop.core.calculator.Service;
import com.gnm.desktop.core.calculator.TimeBaseService;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.ActiveCustomer;
import com.gnm.desktop.data.model.SellLog;
import com.gnm.desktop.ui.layout.mainpanel.Toolbar;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Date;

public class RemoveActiveCustomerDialog extends BaseDialog {

    public RemoveActiveCustomerDialog(ActiveCustomer activeCustomer, RemoveActiveCustomerDialogCallback cb) {

        //label
        Label customerName = new Label("نام مشتری: " + activeCustomer.getCustomerName());
        customerName.getStyleClass().add("dialogText");
        customerName.setAlignment(Pos.CENTER_RIGHT);

        Label costSumLabel = new Label("مبلغ قابل پرداخت: " + activeCustomer.getCostSum());
        costSumLabel.getStyleClass().add("dialogText");
        costSumLabel.setAlignment(Pos.CENTER_RIGHT);

        CheckBox checkBox;
        if (activeCustomer.isGuest()) {
            checkBox = new CheckBox("کم کردن از اعتبار (مشتری مهمان است)");
            checkBox.setDisable(true);
        } else {
            //check credit
            if (DB.Customers.getById(activeCustomer.getCustomerId()).credit < activeCustomer.getCostSum())
                checkBox = new CheckBox("کم کردن از اعتبار (اعتبار کافی نیست)");
            else
                checkBox = new CheckBox("کم کردن از اعتبار");

        }
        checkBox.getStyleClass().add("dialogText");

        checkBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> {

            if (checkBox.isSelected()) {
                //check credit
                int credit = DB.Customers.getById(activeCustomer.getCustomerId()).credit;
                if (credit < activeCustomer.getCostSum()) {
                    costSumLabel.setText("مبلغ قابل پرداخت: " + String.valueOf(activeCustomer.getCostSum() - credit));
                } else {
                    costSumLabel.setText("مبلغ قابل پرداخت: " + 0);
                }
            } else {
                costSumLabel.setText("مبلغ قابل پرداخت: " + activeCustomer.getCostSum());
            }


        });

        //buttons
        Button btnConfirm = new Button("تمام");
        Button btnCancel = new Button("انصراف");
        btnConfirm.getStyleClass().add("flatButton");
        btnCancel.getStyleClass().add("flatButton");

        btnConfirm.setOnMouseClicked(event -> {

            //save in db
            int sumOfTimeAndCountBase = saveToSellLog(activeCustomer);

            //update toolbar
            Toolbar.ToolbarTodayIncome.Increase(sumOfTimeAndCountBase);

            //decrease credit
            if (checkBox.isSelected())
                DB.Customers.DecreaseCredit(activeCustomer.getCustomerId(), activeCustomer.getCostSum());

            //call back for ui changes
            cb.onDelete();
            this.close();
        });

        // keep things together
        var root = new VBox(15);
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        root.getChildren().addAll(
                customerName,
                costSumLabel,
                checkBox
        );
        var btns = new HBox(btnConfirm, btnCancel);
        btns.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        root.getChildren().add(btns);
        root.setAlignment(Pos.CENTER_LEFT);

        super.setup(root, btnCancel, "تمام شدن کار مشتری", 300, 150);
        show();
    }

    /**
     * @return sum of services (not payment)
     */
    private int saveToSellLog(ActiveCustomer activeCustomer) {

        int sumOfTimeAndCountBase = 0;
        for (Service i : activeCustomer.getServicesForLoop()) {

            SellLog sl;
            if (i instanceof CountBaseService) {
                sl = new SellLog(
                        activeCustomer.getId(),
                        new Date().getTime(),
                        i.getCurrentCost(),
                        ((CountBaseService) i).getServiceName()
                );
                sumOfTimeAndCountBase += i.getCurrentCost();
            } else if (i instanceof TimeBaseService) {
                sl = new SellLog(
                        activeCustomer.getId(),
                        new Date().getTime(),
                        i.getCurrentCost(),
                        ((TimeBaseService) i).getServiceName(),
                        (int) ((TimeBaseService) i).getActiveTimeInHours()
                );
                sumOfTimeAndCountBase += i.getCurrentCost();
            } else if (i instanceof PaymentBaseService) {
                continue; // ignore (don't store in db as sell log)
            } else
                throw new RuntimeException("invalid service type");


            DB.SellLogs.Insert(sl);
        }
        return sumOfTimeAndCountBase;
    }

    public interface RemoveActiveCustomerDialogCallback {
        void onDelete();
    }
}
