package com.gnm.desktop.ui.layout.priceslayout;


import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.res.css.CSSStyler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

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

        setPadding(new Insets(0,40,0,50));



        /***************************************
         *                                     *
         *    TIME BASE SERVICE CARD VIEWS     *
         *                                     *
         ***************************************/
        flowTBS =new FlowPane(40,40);
        AnchorPane.setLeftAnchor(flowTBS,500.0);
        AnchorPane.setRightAnchor(flowTBS,0.0);
        AnchorPane.setTopAnchor(flowTBS,50.0);


        //label svgpath add
        lblAddSvg =new Label();
        lblAddSvg.setPrefSize(50 , 50);
        lblAddSvg.getStyleClass().add("priceslayout_lblAddsvg");

        //add addCardTBS
        addCardTBS =new VBox();
        addCardTBS.setPadding(new Insets(10,10,10,10));
        addCardTBS.setPrefSize(200, 100);
        addCardTBS.setAlignment(Pos.CENTER);
        addCardTBS.getStyleClass().add("priceslayout_addCardtbs");

        addCardTBS.getChildren().add(lblAddSvg);
        flowTBS.getChildren().add(addCardTBS);


        addCardTBS.setOnMouseClicked(event -> new PopupAddService());



        //  Seprator Line
        Pane seprator=new Pane();
        seprator.setStyle("-fx-background-color: black;");
        seprator.setPrefHeight(2);



        /***************************************
         *                                     *
         *    COUNT BASE SERVICE CARD VIEWS    *
         *                                     *
         ***************************************/
        listCardCBS=new CBSCard();
        AnchorPane.setTopAnchor(listCardCBS,50.0);
        AnchorPane.setLeftAnchor(listCardCBS,0.0);


        makeTimeBaseServiceCards();
        getChildren().addAll(listCardCBS,flowTBS);
    }

    public static void makeTimeBaseServiceCards(){

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
