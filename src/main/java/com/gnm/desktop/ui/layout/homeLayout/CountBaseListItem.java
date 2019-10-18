package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.CountBaseService;
import javafx.scene.layout.AnchorPane;

class CountBaseListItem extends AnchorPane {

    private ServiceCardItemCallback callback;

    CountBaseListItem(CountBaseService countBaseService) {

    }

    public void setCallback(ServiceCardItemCallback callback) {
        this.callback = callback;
    }
}
