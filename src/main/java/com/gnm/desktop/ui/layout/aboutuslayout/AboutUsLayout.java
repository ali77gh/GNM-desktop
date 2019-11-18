package com.gnm.desktop.ui.layout.aboutuslayout;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import static com.gnm.desktop.Main.APP;

public class AboutUsLayout extends ScrollPane {

    public AboutUsLayout() {

        var box = new VBox(
                50,
                getInfo(),
                getCopyRight(),
                getTechs(),
                getContactUs(),
                getDevelopers()
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
        Label title = new Label("Snack");
        title.setStyle("-fx-font-size: 20;");
        title.getStyleClass().addAll("vazir");

        //version
        Label version = new Label("نسخه ازمایشی ۱۰۱");
        version.getStyleClass().addAll("vazir");

        //logo
        Label appLogo = new Label();
        appLogo.getStyleClass().add("menuItem_icon");
        appLogo.getStyleClass().add("homeIcon");
        appLogo.setPrefSize(220, 220);
        appLogo.setMinSize(220, 220);

        //description
        Label description = new Label("این برنامه توسط تیم ما برای راحت تر شدن کار گیم نت ها نوشته شده و اسم ان هم بر گرفته از یک نوع خوراکی خوشمزه است");
        description.wrapTextProperty().setValue(true);
        description.setMaxWidth(600);
        description.getStyleClass().addAll("vazir");

        //layout
        VBox infoLayout = new VBox(30);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        infoLayout.setPrefSize(700, 460);
        infoLayout.getStyleClass().addAll("card", "padding20");
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
        title.getStyleClass().addAll("vazir");
        title.setStyle("-fx-font-size: 20;");

        //logo
        Label appLogo = new Label();
        appLogo.getStyleClass().add("menuItem_icon");
        appLogo.getStyleClass().add("copyRight");
        appLogo.setPrefSize(220, 220);
        appLogo.setMinSize(220, 220);
        appLogo.setRotate(180);

        //description
        Label description = new Label("برای استفاده از این برنامه باید ان را خریداری کرده باشید و در غیر این صورت به وبسایت مراجعه کنید. کلیه ی حقوق مادی و معنوی این برنامه محفوظ میباشد");
        description.wrapTextProperty().setValue(true);
        description.setMaxWidth(600);
        description.setAlignment(Pos.CENTER);
        description.getStyleClass().addAll("vazir");

        //layout
        VBox infoLayout = new VBox(30);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        infoLayout.setPrefSize(700, 420);
        infoLayout.getStyleClass().addAll("card", "padding20");
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
        title.getStyleClass().addAll("vazir");

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
        infoLayout.getStyleClass().addAll("card", "padding20");
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
        title.getStyleClass().addAll("vazir");

        //website
        Label website = new Label("website: snackapp.ir");
        website.setOnMouseClicked(mouseEvent -> {
            openWebsite();
        });

        //email
        Label email = new Label("email: snackdeveloper@gmail.com");
        email.setOnMouseClicked(mouseEvent -> {
            openEmail();
        });

        //layout
        VBox infoLayout = new VBox(10);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        infoLayout.setPrefSize(700, 140);
        infoLayout.getStyleClass().addAll("card", "padding20");
        infoLayout.getChildren().addAll(
                title,
                website,
                email
        );
        return infoLayout;
    }

    private Pane getDevelopers() {

        //title
        Label title = new Label("توسعه دهندگان:");
        title.setPadding(new Insets(0, 0, 20, 0));
        title.setStyle("-fx-font-size: 20;");
        title.getStyleClass().addAll("vazir");

        //website
        Label openAlisLinks = new Label("علی قهرمانی");
        openAlisLinks.getStyleClass().addAll("vazir");
        openAlisLinks.setOnMouseClicked(mouseEvent -> openAlisLinks());

        //email
        Label openMohammadsLinks = new Label("محمد باقرلو");
        openMohammadsLinks.getStyleClass().addAll("vazir");
        openMohammadsLinks.setOnMouseClicked(mouseEvent -> openMohammadsLinks());

        //layout
        VBox infoLayout = new VBox(10);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        infoLayout.setPrefSize(700, 140);
        infoLayout.getStyleClass().addAll("card", "padding20");
        infoLayout.getChildren().addAll(
                title,
                openAlisLinks,
                openMohammadsLinks
        );
        return infoLayout;
    }

    private static void openWebsite() {
        APP.getHostServices().showDocument("http://www.snackapp.ir");
    }

    private static void openEmail() {
        APP.getHostServices().showDocument("mailto:snackdeveloper@gmail.com");
    }

    private static void openAlisLinks() {
        APP.getHostServices().showDocument("https://github.com/ali77gh");
        APP.getHostServices().showDocument("https://www.instagram.com/ali77gh/");
    }

    private static void openMohammadsLinks() {
        APP.getHostServices().showDocument("https://github.com/bgl-mmd");
        APP.getHostServices().showDocument("https://www.instagram.com/bgl.mmd/");
    }
}

