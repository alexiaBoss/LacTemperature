package com.example.lactemperature;

public class Lac {
    public Lac(String nomLac, double longitude, double latitude) {
        this.nomLac = nomLac;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    protected String nomLac;
    protected double longitude;
    protected double latitude;

    public String getNomLac() {
        return nomLac;
    }

    public void setNomLac(String nomLac) {
        this.nomLac = nomLac;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

