package com.aceappltd.highspaceapp.data;

import android.graphics.Bitmap;

public class Events {

    private String name;
    private String type;
    private String location;
    private String dateTime;
    private String iconURL;

    public Bitmap iconBitmap;

    public Events(String name, String type, String location, String dateTime, String iconURL) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.dateTime = dateTime;
        this.iconURL = iconURL;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getIconURL() {
        return iconURL;
    }

    public Bitmap getIconBitmap() {
        return iconBitmap;
    }
}
