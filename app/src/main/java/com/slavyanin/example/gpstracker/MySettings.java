package com.slavyanin.example.gpstracker;

public class MySettings {

    private int mapType = 1;
    private boolean zoomControl = false;


    public int getMapType() {
        return mapType;
    }

    public void setMapType(int mapType) {
        //        "0" = MAP_TYPE_NONE;
        //        "1" = MAP_TYPE_NORMAL;
        //        "2" = MAP_TYPE_TERRAIN;
        //        "3" = MAP_TYPE_SATELLITE;
        //        "4" = MAP_TYPE_HYBRID;
        this.mapType = mapType;
    }

    public boolean isZoomControl() {
        return zoomControl;
    }

    public void setZoomControl(boolean zoomControl) {
        // false or true
        this.zoomControl = zoomControl;
    }
}
