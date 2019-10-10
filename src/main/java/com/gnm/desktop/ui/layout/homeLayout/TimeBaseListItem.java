package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.TimeBaseService;
import javafx.scene.layout.AnchorPane;

public class TimeBaseListItem extends AnchorPane {

    private ServiceCardCallback callback;

    TimeBaseListItem(TimeBaseService timeBaseService) {

    }

    public void setCallback(ServiceCardCallback callback) {
        this.callback = callback;
    }
}
