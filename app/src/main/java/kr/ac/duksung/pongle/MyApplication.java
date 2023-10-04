package kr.ac.duksung.pongle;

import android.app.Application;

public class MyApplication extends Application {
    private String stdName;
    private String orderID;
    private String stdID;

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String value) {
        this.stdName = value;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String value) {
        this.orderID = value;
    }

    public String getStdID() {
        return stdID;
    }

    public void setStdID(String value) {
        this.stdID = value;
    }
}
