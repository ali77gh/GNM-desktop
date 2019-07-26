package com.gnm.desktop.ui.layout.customerlayout;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.Customer;
import com.gnm.desktop.res.css.CSSStyler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.List;

public class CustomerLayout extends AnchorPane {


    private static FlowPane cardsFlow;
    private static TextField txtSearch;
    private static Label lblNotFound;

    public CustomerLayout() {

        //load css files
        getStylesheets().add(CSSStyler.get("app.css"));
        getStylesheets().add(CSSStyler.get("icon.css"));


        //AnchorPane cant show items in center so putting items in hbox and hbox in anchorpane
        HBox centerItem=new HBox();
        centerItem.setStyle("-fx-background-color: transparent;");
        centerItem.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(centerItem,150.0);
        AnchorPane.setRightAnchor(centerItem,0.0);
        AnchorPane.setLeftAnchor(centerItem,0.0);


        /**********************************
         *                                *
         *        searchBar Layout        *
         *                                *
         **********************************/
        AnchorPane searchBar=new AnchorPane();
        searchBar.setPrefSize(400,35);
        searchBar.getStyleClass().add("customerLayout_searchBar");


        Label searchIcon=new Label();
        searchIcon.setPrefSize(20,20);
        searchIcon.getStyleClass().add("customerLayout_searchIcon");
        searchIcon.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        AnchorPane.setTopAnchor(searchIcon,10.0);
        AnchorPane.setRightAnchor(searchIcon,10.0);
        AnchorPane.setBottomAnchor(searchIcon,10.0);


        txtSearch=new TextField();
        AnchorPane.setTopAnchor(searchIcon,10.0);
        txtSearch.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtSearch.getStyleClass().add("customerLayout_txtSearch");
        txtSearch.setPromptText("09XXXXXXXXX");
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

        cardsFlow=new FlowPane(30,40);
        AnchorPane.setTopAnchor(cardsFlow,250.0);
        AnchorPane.setLeftAnchor(cardsFlow,100.0);
        AnchorPane.setRightAnchor(cardsFlow,100.0);



        //Not Found
        HBox addNewCustomer=new HBox();
        addNewCustomer.setStyle("-fx-background-color: transparent;");
        addNewCustomer.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(addNewCustomer,300.0);
        AnchorPane.setLeftAnchor(addNewCustomer,100.0);
        AnchorPane.setRightAnchor(addNewCustomer,100.0);

        lblNotFound=new Label("چیزی یافت نشد :(");
        lblNotFound.getStyleClass().add("customerLayout_txtSearch");

        addNewCustomer.getChildren().add(lblNotFound);
        lblNotFound.setVisible(false);


        Refresh();
        getChildren().addAll(centerItem,addNewCustomer,cardsFlow);
    }

    public static void Refresh(){

        cardsFlow.getChildren().clear();

        List<Customer> list;

        if(txtSearch.getText().isEmpty()){
            list= DB.Customers.getAll();
        }else {
            list=DB.Customers.getCustomerByPhoneContains(txtSearch.getText());
        }


        if (list.isEmpty()) {
            lblNotFound.setVisible(true);
        }else {
            lblNotFound.setVisible(false);
            for (Customer c : list) {
                cardsFlow.getChildren().add(new CustomerCard(c));
            }
        }

    }
}
