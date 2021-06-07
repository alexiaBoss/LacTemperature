package com.example.lactemperature;

public class Historique {

    // constructeur de l'historique
    public Historique(String nomLac, double longitude, double latitude, int jour, int mois, double tempA6h, double tempA12h, double tempA18h, double tempA24h) {
        this.nomLac = nomLac;
        this.longitude = longitude;
        this.latitude = latitude;
        this.jour = jour;
        this.mois = mois;
        this.tempA6h = tempA6h;
        this.tempA12h = tempA12h;
        this.tempA18h = tempA18h;
        this.tempA24h = tempA24h;
    }

    // attributs de l'historique
    protected String nomLac;
    protected double longitude;
    protected double latitude;
    protected int jour;
    protected int mois;
    protected double tempA6h;
    protected double tempA12h;
    protected double tempA18h;
    protected double tempA24h;


    //Accesseurs et Mutateurs
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

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public double getTempA6h() {
        return tempA6h;
    }

    public void setTempA6h(double tempA6h) {
        this.tempA6h = tempA6h;
    }

    public double getTempA12h() {
        return tempA12h;
    }

    public void setTempA12h(double tempA12h) {
        this.tempA12h = tempA12h;
    }

    public double getTempA18h() {
        return tempA18h;
    }

    public void setTempA18h(double tempA18h) {
        this.tempA18h = tempA18h;
    }

    public double getTempA24h() {
        return tempA24h;
    }

    public void setTempA24h(double tempA24h) {
        this.tempA24h = tempA24h;
    }
}
