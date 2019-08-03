package com.gnm.desktop.ui.layout.customerlayout;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.Customer;
import com.gnm.desktop.ui.dialog.AddCustomerDialog;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class CustomerLayout extends AnchorPane {


    private static FlowPane cardsFlow;
    private static TextField txtSearch;
    private static Label lblNotFound;

    public CustomerLayout() {

        //AnchorPane cant show items in center so putting items in hbox and hbox in anchorpane
        HBox centerItem=new HBox();
        centerItem.setStyle("-fx-background-color: transparent;");
        centerItem.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(centerItem,150.0);
        AnchorPane.setRightAnchor(centerItem,0.0);
        AnchorPane.setLeftAnchor(centerItem,0.0);


        Label addNewCustomer=new Label();
        AnchorPane.setRightAnchor(addNewCustomer,70.0);
        AnchorPane.setTopAnchor(addNewCustomer,40.0);
        addNewCustomer.setPrefSize(80,80);
        addNewCustomer.getStyleClass().add("addAccPlusIcon");
        addNewCustomer.setOnMouseClicked(event -> new AddCustomerDialog());



        /**********************************
         *                                *
         *        searchBar Layout        *
         *                                *
         **********************************/
        AnchorPane searchBar=new AnchorPane();
        searchBar.setPrefSize(400,35);
        searchBar.getStyleClass().add("customerLayout_searchBar");
        //search bar is in the centerItems


        Label searchIcon=new Label();
        searchIcon.setMaxWidth(18);
        searchIcon.getStyleClass().add("customerLayout_searchIcon");
        searchIcon.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        AnchorPane.setTopAnchor(searchIcon,10.0);
        AnchorPane.setRightAnchor(searchIcon,10.0);
        AnchorPane.setBottomAnchor(searchIcon,10.0);


        txtSearch=new TextField();
        txtSearch.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtSearch.getStyleClass().add("customerLayout_txtSearch");
        txtSearch.setPromptText("tel OR game");
        txtSearch.setFocusTraversable(false);
        AnchorPane.setTopAnchor(txtSearch,0.0);
        AnchorPane.setRightAnchor(txtSearch,40.0);
        AnchorPane.setBottomAnchor(txtSearch,0.0);
        AnchorPane.setLeftAnchor(txtSearch,3.0);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> Refresh());


        //add searchBar Items
        searchBar.getChildren().addAll(txtSearch,searchIcon);


        //adding center items
        centerItem.getChildren().addAll(searchBar);

        /**********************************
         *                                *
         *        cardsFlow Layout        *
         *                                *
         **********************************/

        cardsFlow=new FlowPane(50,50);
        cardsFlow.setAlignment(Pos.TOP_CENTER);
        cardsFlow.setPadding(new Insets(50,0,0,0));

        ScrollPane cardsFlowScroll=new ScrollPane(cardsFlow);
        AnchorPane.setTopAnchor(cardsFlowScroll,200.0);
        AnchorPane.setLeftAnchor(cardsFlowScroll,100.0);
        AnchorPane.setRightAnchor(cardsFlowScroll,100.0);
        AnchorPane.setBottomAnchor(cardsFlowScroll,10.0);
        cardsFlowScroll.setFitToWidth(true);
        cardsFlowScroll.getStyleClass().add("customerLayout_cardsFlowScroll");



        //Not Found
        HBox notFoundWarning=new HBox();
        notFoundWarning.setStyle("-fx-background-color: transparent;");
        notFoundWarning.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(notFoundWarning,300.0);
        AnchorPane.setLeftAnchor(notFoundWarning,100.0);
        AnchorPane.setRightAnchor(notFoundWarning,100.0);


        lblNotFound=new Label("چیزی یافت نشد :(");
        lblNotFound.getStyleClass().add("customerLayout_txtSearch");

        notFoundWarning.getChildren().add(lblNotFound);
        lblNotFound.setVisible(false);


        Refresh();
        getChildren().addAll(centerItem,notFoundWarning,cardsFlowScroll,addNewCustomer);
    }

    public static void Refresh(){

        cardsFlow.getChildren().clear();

        new Thread(()-> {
            List<Customer> list;

            if(txtSearch.getText().isEmpty()){
                list= DB.Customers.getAll();
            }else {
                try {
                    Integer.valueOf(txtSearch.getText());
                    list = DB.Customers.getCustomerByPhoneContains(txtSearch.getText());
                }catch (NumberFormatException e){
                    list = DB.Customers.searchByGame(txtSearch.getText());
                }
            }

            List<Customer> finalList = list;
            Platform.runLater(() -> {
                if (finalList.isEmpty()) {
                    lblNotFound.setVisible(true);
                }else {
                    lblNotFound.setVisible(false);
                    for (Customer c : finalList) {
                    cardsFlow.getChildren().add(new CustomerCard(c));
                    }
                }
            });

        }).start();

    }
}
