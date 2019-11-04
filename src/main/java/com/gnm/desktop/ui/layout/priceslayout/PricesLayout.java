package com.gnm.desktop.ui.layout.priceslayout;


import com.gnm.desktop.core.AppRefresh;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.ui.dialog.AddServiceDialog;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

import static com.gnm.desktop.ui.layout.rightMenu.Items.PRICES;


public class PricesLayout extends AnchorPane {

    //TBS == TimeBaseService
    //CBS == CountBaseService
    private static FlowPane flowTBS;
    private static VBox addCardTBS;
    private Label lblAddSvg;
    private CBSCard listCardCBS;

    public PricesLayout() {

        setPadding(new Insets(40,40,0,50));



        /***************************************
         *                                     *
         *    TIME BASE SERVICE CARD VIEWS     *
         *                                     *
         ***************************************/
        flowTBS = new FlowPane(30, 30);
        var scrollTBS = new ScrollPane();
        scrollTBS.setFitToWidth(true);
        scrollTBS.getStyleClass().add("report_scrollPane");
        AnchorPane.setLeftAnchor(scrollTBS, 500.0);
        AnchorPane.setRightAnchor(scrollTBS, 0.0);
        AnchorPane.setTopAnchor(scrollTBS, 60.0);
        AnchorPane.setBottomAnchor(scrollTBS, 0.0);
//        flowTBS.setAlignment(Pos.TOP_RIGHT);
        flowTBS.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        flowTBS.setPadding(new Insets(10, 0, 0, 10));
        scrollTBS.setContent(flowTBS);


        //label svgpath add
        lblAddSvg = new Label();
        lblAddSvg.setPrefSize(40, 40);
        lblAddSvg.getStyleClass().add("plusIcon");
        lblAddSvg.getStyleClass().add("plus");

        //add addCardTBS
        addCardTBS = new VBox();
        addCardTBS.setPadding(new Insets(10,10,10,10));
        addCardTBS.setPrefSize(180, 100);
        addCardTBS.setAlignment(Pos.CENTER);
        addCardTBS.getStyleClass().add("pricesLayout_addCardTbs");

        addCardTBS.getChildren().add(lblAddSvg);
        flowTBS.getChildren().add(addCardTBS);


        addCardTBS.setOnMouseClicked(event -> new AddServiceDialog(DB.Prices));


        /***************************************
         *                                     *
         *    COUNT BASE SERVICE CARD VIEWS    *
         *                                     *
         ***************************************/
        listCardCBS = new CBSCard();
        AnchorPane.setTopAnchor(listCardCBS, 60.0);
        AnchorPane.setLeftAnchor(listCardCBS,0.0);


        //titles
        var CBSTitle = new Label("سرویس های بر پایه تعداد");
        CBSTitle.getStyleClass().add("tbsCard_lblName");
        AnchorPane.setTopAnchor(CBSTitle, 0.0);
        AnchorPane.setLeftAnchor(CBSTitle, 260.0);

        var TBSTitle = new Label("سرویس های بر پایه زمان");
        TBSTitle.getStyleClass().add("tbsCard_lblName");
        AnchorPane.setRightAnchor(TBSTitle, 0.0);
        AnchorPane.setTopAnchor(TBSTitle, 0.0);

        Refresh();
        AppRefresh.registerLayout(PRICES, () -> {
            Refresh();
            CBSCard.Refresh();
        });

        getChildren().addAll(listCardCBS, scrollTBS, CBSTitle, TBSTitle);
    }

    public static void Refresh() {

        flowTBS.getChildren().clear();

        new Thread(() -> {
            List<PricePerHour> pricesService = DB.Prices.getAll();

            Platform.runLater(() -> {
                if (!pricesService.isEmpty()){
                    for (PricePerHour p: pricesService){
                        flowTBS.getChildren().add(new TBSCard(p));
                    }
                }
                flowTBS.getChildren().add(addCardTBS);
            });

        }).start();
    }

}
