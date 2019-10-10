package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.ActiveCustomer;
import com.gnm.desktop.ui.dialog.AddActiveCustomerDialog;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class HomeLayout extends AnchorPane {

    private FlowPane flowPane;
    private ScrollPane scrollPane;
    private Label nothingToShow;

    public HomeLayout() {

        setupNothingToShow();//this should call before others because this view is under(z index) others

        // load data
        var activeCustomers = getActiveCustomers();

        //setup parent layout
        configScrollPane();
        configFlowPane();
        this.getChildren().add(scrollPane);
        scrollPane.setContent(flowPane);

        // render data (add cards)
        for (ActiveCustomer a : activeCustomers) {
            AddCard(a);
        }

        // add last card (for new Active customer)
        flowPane.getChildren().add(getNewCustomerCard());
    }

    private void configScrollPane() {

        var scrollPane = new ScrollPane();
        // render basics
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStyleClass().add("report_scrollPane");
        scrollPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        this.scrollPane = scrollPane;
    }

    private void configFlowPane() {
        var flowPane = new FlowPane();
        flowPane.setPadding(new Insets(60, 60, 60, 60));
        flowPane.setHgap(60);
        flowPane.setVgap(60);
        flowPane.setRowValignment(VPos.TOP);
        flowPane.setAlignment(Pos.CENTER);
        this.flowPane = flowPane;
    }

    private static List<ActiveCustomer> getActiveCustomers() {
        return DB.ActiveCustomers.getAll();
    }

    private Node getNewCustomerCard() {

        //card
        VBox newCustomerCard = new VBox();
        newCustomerCard.setPadding(new Insets(10, 10, 10, 10));
        newCustomerCard.setMaxSize(350, 100);
        newCustomerCard.setPrefSize(350, 100);
        newCustomerCard.setAlignment(Pos.CENTER);
        newCustomerCard.getStyleClass().add("pricesLayout_addCardTbs");

        //icon
        Label lblAddSvg = new Label();
        lblAddSvg.setPrefSize(40, 40);
        lblAddSvg.getStyleClass().add("plusIcon");
        lblAddSvg.getStyleClass().add("plus");

        newCustomerCard.getChildren().add(lblAddSvg);

        newCustomerCard.setOnMouseClicked(mouseEvent -> {
            new AddActiveCustomerDialog(activeCustomer -> {

                // plus card should be last item so:
                var temp = flowPane.getChildren().remove(flowPane.getChildren().size() - 1);// remove plus card
                AddCard(activeCustomer); //add customer card
                flowPane.getChildren().add(temp);//add plus card again
            });
        });
        return newCustomerCard;
    }

    private void AddCard(ActiveCustomer activeCustomer) {

        ActiveCustomerCard activeCustomerCard = new ActiveCustomerCard(activeCustomer);
        activeCustomerCard.setListener(() -> {
            //delete request
            flowPane.getChildren().remove(activeCustomerCard);

            DB.ActiveCustomers.Remove(activeCustomer.getId());

            if (flowPane.getChildren().size() == 1)
                nothingToShow.setVisible(true);
        });

        flowPane.getChildren().add(activeCustomerCard);

        nothingToShow.setVisible(false);//when active customer add then active customers size is not 0
    }

    private void setupNothingToShow() {
        nothingToShow = new Label("مشتری فعالی برای نمایش وجود ندارد");
        nothingToShow.setAlignment(Pos.CENTER);
        nothingToShow.getStyleClass().add("dialogText");
        AnchorPane.setTopAnchor(nothingToShow, 0.0);
        AnchorPane.setLeftAnchor(nothingToShow, 0.0);
        AnchorPane.setRightAnchor(nothingToShow, 0.0);
        AnchorPane.setBottomAnchor(nothingToShow, 0.0);
        this.getChildren().add(nothingToShow);
    }
}
