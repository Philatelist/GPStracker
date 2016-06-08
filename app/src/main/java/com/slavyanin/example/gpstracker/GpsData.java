package com.slavyanin.example.gpstracker;

public class GpsData {
    public double latitude = 37.37;
    public double longtitude = -122.04;
    public double speed = 0;
    public String date = "01.01.1900";
    public String time = "00:00";
    public String httpLink = "http://maps.google.com";
    public int locationAreaCode = 0;
    public int cellID = 0;
    public int mobileCountryCode = 0;
    public int mobileNetworkCode = 0;

//    public GpsData(double latitude, double longtitude, double speed, String date, String time, String httpLink, int locationAreaCode, int cellID, int mobileCountryCode, int mobileNetworkCode) {
//        this.latitude = latitude;
//        this.longtitude = longtitude;
//        this.speed = speed;
//        this.date = date;
//        this.time = time;
//        this.httpLink = httpLink;
//        this.locationAreaCode = locationAreaCode;
//        this.cellID = cellID;
//        this.mobileCountryCode = mobileCountryCode;
//        this.mobileNetworkCode = mobileNetworkCode;
//    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHttpLink() {
        return httpLink;
    }

    public void setHttpLink(String httpLink) {
        this.httpLink = httpLink;
    }

    public int getLocationAreaCode() {
        return locationAreaCode;
    }

    public void setLocationAreaCode(int locationAreaCode) {
        this.locationAreaCode = locationAreaCode;
    }

    public int getCellID() {
        return cellID;
    }

    public void setCellID(int cellID) {
        this.cellID = cellID;
    }

    public int getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(int mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public int getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public void setMobileNetworkCode(int mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpsData gpsData = (GpsData) o;

        if (Double.compare(gpsData.latitude, latitude) != 0) return false;
        if (Double.compare(gpsData.longtitude, longtitude) != 0) return false;
        if (Double.compare(gpsData.speed, speed) != 0) return false;
        if (locationAreaCode != gpsData.locationAreaCode) return false;
        if (cellID != gpsData.cellID) return false;
        if (mobileCountryCode != gpsData.mobileCountryCode) return false;
        if (mobileNetworkCode != gpsData.mobileNetworkCode) return false;
        if (!date.equals(gpsData.date)) return false;
        if (!time.equals(gpsData.time)) return false;
        return httpLink.equals(gpsData.httpLink);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longtitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + date.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + httpLink.hashCode();
        result = 31 * result + locationAreaCode;
        result = 31 * result + cellID;
        result = 31 * result + mobileCountryCode;
        result = 31 * result + mobileNetworkCode;
        return result;
    }

    @Override
    public String toString() {
        return "GpsData{" +
                "latitude=" + latitude +
                ", longtitude=" + longtitude +
                ", speed=" + speed +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", httpLink='" + httpLink + '\'' +
                ", locationAreaCode=" + locationAreaCode +
                ", cellID=" + cellID +
                ", mobileCountryCode=" + mobileCountryCode +
                ", mobileNetworkCode=" + mobileNetworkCode +
                '}';
    }
}
