package com.hoperun.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by hoperun on 16-9-13.
 */
public class ApplicationInfor {
    public Drawable appIcon;
    public String appLabel;
    public double runningMem;

    public ApplicationInfor() {
    }

    public ApplicationInfor(Drawable appIcon, String appLabel, double runningMem) {
        this.appIcon = appIcon;
        this.appLabel = appLabel;
        this.runningMem = runningMem;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public String getAppLabel() {
        return appLabel;
    }

    public double getRunningMem() {
        return runningMem;
    }

    public void setRunningMem(double runningMem) {
        this.runningMem = runningMem;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    @Override
    public String toString() {
        return "ApplicationInfor{" +
                "appIcon=" + appIcon +
                ", appLabel='" + appLabel + '\'' +
                ", runningMem=" + runningMem +
                '}';
    }
}
