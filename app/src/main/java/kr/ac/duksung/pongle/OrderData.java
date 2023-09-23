package kr.ac.duksung.pongle;

import android.widget.TextView;

import org.w3c.dom.Text;

public class OrderData {
    private static final OrderData instance = new OrderData();
    private String seatID;
    private String menuID;
    private String stdID;

    private OrderData() {}

    public static OrderData getInstance() {
        return instance;
    }

    public String getSeatID() {
        return seatID;
    }

    // Setter for name
    public void setSeatID(String seatID) {
        this.seatID = seatID;
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