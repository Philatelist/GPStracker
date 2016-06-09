package com.slavyanin.example.gpstracker;

public class MySettings {

    private int mapType = 1;
    private boolean mapZoomControl = false;
    private boolean mapCompas = true;

    private int mapZoom = 17;
    private int mapBearing = 0;
    private int mapTilt = 30;

    private boolean myLocation = true;

    private boolean convertGPS = true;

    public boolean isConvertGPS() {
        return convertGPS;
    }

    public void setConvertGPS(boolean convertGPS) {
        this.convertGPS = convertGPS;
    }

    public boolean isMyLocation() {
        return myLocation;
    }

    public void setMyLocation(boolean myLocation) {
        this.myLocation = myLocation;
    }

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

    public boolean isMapZoomControl() {
        return mapZoomControl;
    }

    public void setMapZoomControl(boolean mapZoomControl) {
        //false or true
        this.mapZoomControl = mapZoomControl;
    }

    public boolean isMapCompas() {
        return mapCompas;
    }

    public void setMapCompas(boolean mapCompas) {
        //false or true
        this.mapCompas = mapCompas;
    }

    public int getMapZoom() {
        return mapZoom;
    }

    public void setMapZoom(int mapZoom) {
        this.mapZoom = mapZoom;
    }

    public int getMapBearing() {
        return mapBearing;
    }

    public void setMapBearing(int mapBearing) {
        this.mapBearing = mapBearing;
    }

    public int getMapTilt() {
        return mapTilt;
    }

    public void setMapTilt(int mapTilt) {
        this.mapTilt = mapTilt;
    }
}
