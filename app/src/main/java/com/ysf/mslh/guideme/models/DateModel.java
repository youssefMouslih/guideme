package com.ysf.mslh.guideme.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateModel {
    private Date date;
    private boolean isSelected;

    public DateModel(Date date, boolean isSelected) {
        this.date = date;
        this.isSelected = isSelected;
    }

    public Date getDate() {
        return date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    // Method to get the day of the week as a string
    public String getDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE", Locale.getDefault()); // "EEE" gives the abbreviated weekday name (e.g., Mon, Tue, Wed)
        return sdf.format(date); // Format the date to get the day of the week
    }
}
