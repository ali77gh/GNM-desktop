package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.TimeBaseService;
import javafx.scene.layout.AnchorPane;

public class TimeBaseListItem extends AnchorPane {

    private ServiceCardItemCallback callback;

    TimeBaseListItem(TimeBaseService timeBaseService) {

    }

    public void setCallback(ServiceCardItemCallback callback) {
        this.callback = callback;
    }
}
