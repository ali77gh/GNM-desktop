package com.gnm.desktop.ui.layout.priceslayout;


import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.res.css.CSSStyler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;


public class PricesLayout extends VBox{

    //TBS == TimeBaseService
    //CBS == CountBaseService
    private static FlowPane flowTBS, flowCBS;
    private static VBox addCardTBS, addCardCBS;
    private Label lblAddSvg1,lblAddSvg2;

    public PricesLayout() {

        //adding css files
        getStylesheets().add(CSSStyler.get("SvgPathe.css"));
        getStylesheets().add(CSSStyler.get("BackgroundColor.css"));



        /***************************************
         *                                     *
         *    TIME BASE SERVICE CARD VIEWS     *
         *                                     *
         ***************************************/
        flowTBS =new FlowPane(40,40);

        flowTBS.setPadding(new Insets(50,50,50,50));

        //label svgpath add
        lblAddSvg1 =new Label();
        lblAddSvg1.getStyleClass().add("add");
        lblAddSvg1.setPrefSize(50 , 50);
        lblAddSvg1.getStyleClass().add("pricesAddCard");
        lblAddSvg1.getStyleClass().add("black");

        //add addCardTBS
        addCardTBS =new VBox();
        addCardTBS.setPadding(new Insets(10,10,10,10));
        addCardTBS.setPrefSize(150, 100);
        addCardTBS.setAlignment(Pos.CENTER);
        addCardTBS.setStyle("-fx-background-color: #fff;"+
                "-fx-background-radius: 20");

        addCardTBS.getChildren().add(lblAddSvg1);
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
        flowCBS =new FlowPane(40,40);

        flowCBS.setPadding(new Insets(50,50,50,50));


        //label svgpath add
        lblAddSvg2=new Label();
        lblAddSvg2.getStyleClass().add("add");
        lblAddSvg2.setPrefSize(50 , 50);
        lblAddSvg2.getStyleClass().add("pricesAddCard");
        lblAddSvg2.getStyleClass().add("black");


        //add addCardTBS
        addCardCBS =new VBox();
        addCardCBS.setPadding(new Insets(10,10,10,10));
        addCardCBS.setPrefSize(150, 100);
        addCardCBS.setAlignment(Pos.CENTER);
        addCardCBS.setStyle("-fx-background-color: #fff;"+
                "-fx-background-radius: 20");

        addCardCBS.getChildren().add(lblAddSvg2);
        flowCBS.getChildren().add(addCardCBS);


        makeTimeBaseServiceCards();
        getChildren().addAll(flowTBS,seprator,flowCBS);
    }

    public static void makeTimeBaseServiceCards(){

        flowTBS.getChildren().clear();

        List<PricePerHour> pricesService=DB.Prices.getAll();
        if (!pricesService.isEmpty()){
            for (PricePerHour p: pricesService){
                flowTBS.getChildren().add(new PriceCard(p));
            }
        }
        flowTBS.getChildren().add(addCardTBS);
    }

}
