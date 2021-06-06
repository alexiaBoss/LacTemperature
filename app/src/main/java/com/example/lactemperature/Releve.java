package com.example.lactemperature;

public class Releve {

    //constructuer de Releve
    public Releve(int jour, int mois, double tempA6h, double tempA12h, double tempA18h, double tempA24h, String nomLac) {
        this.jour = jour;
        this.mois = mois;
        this.tempA6h = tempA6h;
        this.tempA12h = tempA12h;
        this.tempA18h = tempA18h;
        this.tempA24h = tempA24h;
        this.nomLac = nomLac;
    }

    // attributs de la classe Releve
    protected int jour;
    protected int mois;
    protected double tempA6h;
    protected double tempA12h;
    protected double tempA18h;
    protected double tempA24h;
    protected String nomLac;


    //Accesseurs et Mutateurs
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

    public String getNomLac() {
        return nomLac;
    }

    public void setNomLac(String nomLac) {
        this.nomLac = nomLac;
    }

    @Override
    public String toString() {
        return "Releve{" +
                "jour=" + jour +
                ", mois=" + mois +
                ", tempA6h=" + tempA6h +
                ", tempA12h=" + tempA12h +
                ", tempA18h=" + tempA18h +
                ", tempA24h=" + tempA24h +
                ", nomLac='" + nomLac + '\'' +
                '}';
    }
}
