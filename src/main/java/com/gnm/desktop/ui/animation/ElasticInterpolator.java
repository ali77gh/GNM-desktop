package com.gnm.desktop.ui.animation;

import javafx.animation.Interpolator;

public class ElasticInterpolator extends Interpolator {

    private double a = 1;
    private double p = 0.8;
    private double s = p / 4;


    @Override
    protected double curve(double t) {
        if (t == 0.0 || t == 1.0)
            return t;

        return a * Math.pow(2.0, -10 * t) * Math.sin((t - s) * (2 * Math.PI) / p) + 1;
    }
}
