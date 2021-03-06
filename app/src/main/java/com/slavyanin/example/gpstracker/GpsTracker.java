package com.slavyanin.example.gpstracker;

public class GpsTracker {

    private String idTracker = TrackerTable.COLUMN_ID;
    private String name = TrackerTable.COLUMN_NAME;
    private String model = TrackerTable.COLUMN_MODEL;
    private String imei = TrackerTable.COLUMN_IMEI;
    private String cellNumber = TrackerTable.COLUMN_CELLNUMBER;
    private String password = TrackerTable.COLUMN_PASSWORD;

//    public GpsTracker(String name, String model, String imei, String cellNumber) {
//        this.name = name;
//        this.model = model;
//        this.imei = imei;
//        this.cellNumber = cellNumber;
//    }

    public String getIdTracker() {
        return idTracker;
    }

    public void setIdTracker(String idTracker) {
        this.idTracker = idTracker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpsTracker that = (GpsTracker) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;
        return cellNumber != null ? cellNumber.equals(that.cellNumber) : that.cellNumber == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (cellNumber != null ? cellNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GpsTracker{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", imei='" + imei + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                '}';
    }
}
