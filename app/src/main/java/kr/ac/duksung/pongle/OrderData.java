package kr.ac.duksung.pongle;

import android.widget.TextView;

import org.w3c.dom.Text;

public class OrderData {
    private static final OrderData instance = new OrderData();
    private String seatID;
    private String menuID;
    private String stdID;

    private String orderDate;

    OrderData() {}

    public static OrderData getInstance() {
        return instance;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getStdID() {
        return stdID;
    }

    public void setStdID(String stdID) {
        this.stdID = stdID;
    }
}