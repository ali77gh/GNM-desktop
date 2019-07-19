package com.gnm.desktop.ui.layout.priceslayout;


import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.res.css.CSSStyler;
import com.gnm.desktop.ui.dialog.AddServiceDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;


public class PricesLayout extends AnchorPane {

    //TBS == TimeBaseService
    //CBS == CountBaseService
    private static FlowPane flowTBS;
    private static VBox addCardTBS;
    private Label lblAddSvg;
    private CBSCard listCardCBS;

    public PricesLayout() {

        //adding css file
        getStylesheets().add(CSSStyler.get("app.css"));
        getStylesheets().add(CSSStyler.get("icon.css"));

        setPadding(new Insets(0,40,0,50));



        /***************************************
         *                                     *
         *    TIME BASE SERVICE CARD VIEWS     *
         *                                     *
         ***************************************/
        flowTBS = new FlowPane(40, 40);
        AnchorPane.setLeftAnchor(flowTBS,500.0);
        AnchorPane.setRightAnchor(flowTBS,0.0);
        AnchorPane.setTopAnchor(flowTBS,50.0);


        //label svgpath add
        lblAddSvg =new Label();
        lblAddSvg.setPrefSize(40, 40);
        lblAddSvg.getStyleClass().add("plusIcon");

        //add addCardTBS
        addCardTBS =new VBox();
        addCardTBS.setPadding(new Insets(10,10,10,10));
        addCardTBS.setPrefSize(200, 100);
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
        AnchorPane.setTopAnchor(listCardCBS,50.0);
        AnchorPane.setLeftAnchor(listCardCBS,0.0);


        Refresh();
        getChildren().addAll(listCardCBS,flowTBS);
    }

    public static void Refresh() {

        flowTBS.getChildren().clear();

        List<PricePerHour> pricesService=DB.Prices.getAll();
        if (!pricesService.isEmpty()){
            for (PricePerHour p: pricesService){
                flowTBS.getChildren().add(new TBSCard(p));
            }
        }
        flowTBS.getChildren().add(addCardTBS);
    }

}
