package com.slavyanin.example.gpstracker;

public class Calculate {
    private double latitude1;
    private double latitude2;

    private double longitude1;
    private double longtitude2;

    private final double radius = 6371;

    private double deltaLatitude;
    private double deltaLongtitude;

    private double a, c, distance, var;

    private int degree;
    private int minute;
    private double second;
    private String formatGPS;

    MySettings settings = new MySettings();

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        this.latitude1 = lat1;
        this.latitude2 = lat2;
        this.longitude1 = lon1;
        this.longtitude2 = lon2;

        deltaLatitude = Math.toRadians(latitude2 - latitude1);
        deltaLongtitude = Math.toRadians(longtitude2 - longitude1);

        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2)
                + Math.cos(latitude1) * Math.cos(latitude2)
                * Math.sin(deltaLongtitude / 2) * Math.sin(deltaLongtitude / 2);


        c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        distance = c * radius;
        return distance;
    }

    public String getFormatGPS(double coordinate, String wsg) {
        var = coordinate < 0 ? coordinate * (-1) : coordinate;
        degree = (int) var;
        minute = (int) ((var - degree) * 60);
        second = ((((var - degree) * 60) - minute) * 60);

        if (settings.isConvertGPS() && coordinate < 0 && wsg.equals("latitude")) {
            formatGPS = String.format("%d\u00b0%d\'%.2f\"", degree, minute, second) + " S.";
            return formatGPS;
        }
        if (settings.isConvertGPS() && coordinate < 0 && wsg.equals("longtitude")) {
            formatGPS = String.format("%d\u00b0%d\'%.2f\"", degree, minute, second) + " W.";
            return formatGPS;
        }
        if (settings.isConvertGPS() && coordinate > 0 && wsg.equals("latitude")) {
            formatGPS = String.format("%d\u00b0%d\'%.2f\"", degree, minute, second) + " N.";
            return formatGPS;
        }
        if (settings.isConvertGPS() && coordinate > 0 && wsg.equals("longtitude")) {
            formatGPS = String.format("%d\u00b0%d\'%.2f\"", degree, minute, second) + " E.";
            return formatGPS;
        }

        return String.format("%.5f", coordinate);

    }
}
