package com.gnm.desktop.ui.layout;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AboutUsLayout extends ScrollPane {

    public AboutUsLayout() {

        var box = new VBox(
                50,
                getInfo(),
                getCopyRight(),
                getTechs(),
                getContactUs()
        );
        box.setPadding(new Insets(50, 50, 50, 50));
        box.setAlignment(Pos.CENTER);
        box.setFillWidth(false);
        this.getStyleClass().add("report_scrollPane");
        this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        this.setFitToWidth(true);
        this.setContent(box);
    }

    private Pane getInfo() {

        //title
        Label title = new Label("نام برنامه اینجا");
        title.setStyle("-fx-font-size: 20;");

        //version
        Label version = new Label("نسخه ازمایشی ۱۰۱");

        //logo
        Label appLogo = new Label();
        appLogo.getStyleClass().add("menuItem_icon");
        appLogo.getStyleClass().add("homeIcon"); // todo logo goes here
        appLogo.setPrefSize(220, 220);
        appLogo.setMinSize(220, 220);

        //description
        Label description = new Label("توضیحات اینجا میاد چند خط هم هست خیلی طولانی ممکنه باشه که کسی شک نکنه توضیحات اینجا میاد چند خط هم هست خیلی طولانی ممکنه باشه که کسی شک نکنه توضیحات اینجا میاد چند خط هم هست خیلی طولانی ممکنه باشه که کسی شک نکنه توضیحات اینجا میاد چند خط هم هست خیلی طولانی ممکنه باشه که کسی شک نکنه توضیحات اینجا میاد چند خط هم هست خیلی طولانی ممکنه باشه که کسی شک نکنه");
        description.wrapTextProperty().setValue(true);
        description.setMaxWidth(600);

        //layout
        VBox infoLayout = new VBox(30);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        infoLayout.setPrefSize(700, 440);
        infoLayout.getStyleClass().add("reportCard");
        infoLayout.getChildren().addAll(
                title,
                version,
                appLogo,
                description
        );
        return infoLayout;
    }

    private Pane getCopyRight() {
        //title
        Label title = new Label("کپی رایت");
        title.setStyle("-fx-font-size: 20;");

        //logo
        Label appLogo = new Label();
        appLogo.getStyleClass().add("menuItem_icon");
        appLogo.getStyleClass().add("copyRight");
        appLogo.setPrefSize(220, 220);
        appLogo.setMinSize(220, 220);
        appLogo.setRotate(180);

        //description
        Label description = new Label("کلیه حقوق محفوظ میباشد");
        description.wrapTextProperty().setValue(true);
        description.setMaxWidth(600);
        description.setAlignment(Pos.CENTER);

        //layout
        VBox infoLayout = new VBox(30);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        infoLayout.setPrefSize(700, 400);
        infoLayout.getStyleClass().add("reportCard");
        infoLayout.getChildren().addAll(
                title,
                appLogo,
                description
        );
        return infoLayout;
    }

    private Pane getTechs() {

        //title
        Label title = new Label("تکنولوژی ها");
        title.setPadding(new Insets(0, 0, 20, 0));
        title.setStyle("-fx-font-size: 20;");

        //java
        Label language = new Label("language: java 12");

        //jfx
        Label javafx = new Label("graphical library: open jfx 12");

        //sqlite
        Label DBMS = new Label("DBMS: Sqlite");

        //layout
        VBox infoLayout = new VBox(10);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        infoLayout.setPrefSize(700, 160);
        infoLayout.getStyleClass().add("reportCard");
        infoLayout.getChildren().addAll(
                title,
                language,
                javafx,
                DBMS
        );
        return infoLayout;
    }

    private Pane getContactUs() {

        //title
        Label title = new Label("ارتباط با ما");
        title.setPadding(new Insets(0, 0, 20, 0));
        title.setStyle("-fx-font-size: 20;");

        //website
        Label website = new Label("website: felan.ir");

        //email
        Label email = new Label("email: felan@gmail.com");

        //layout
        VBox infoLayout = new VBox(10);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        infoLayout.setPrefSize(700, 140);
        infoLayout.getStyleClass().add("reportCard");
        infoLayout.getChildren().addAll(
                title,
                website,
                email
        );
        return infoLayout;
    }
}

