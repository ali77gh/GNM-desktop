package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.CountBaseService;
import javafx.scene.layout.AnchorPane;

public class CountBaseListItem extends AnchorPane {

    private ServiceCardCallback callback;

    CountBaseListItem(CountBaseService countBaseService) {

    }

    public void setCallback(ServiceCardCallback callback) {
        this.callback = callback;
    }
}
