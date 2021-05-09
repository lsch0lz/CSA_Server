package com.company.CSAServer.model;

public class Artikel {
    private final long aID;
    private String bezeichnung;
    private String bildURL;
    private double preis;

    /**
     * Konstruktor um Entität aus der DB zu laden, also inklusive ID!
     */
    public Artikel(long aID, String bezeichnung, String bildURL, double preis) {
        this.aID = aID;
        this.bezeichnung = bezeichnung;
        this.bildURL = bildURL;
        this.preis = preis;
    }

    /**
     * Konstruktor um Entitaet in DB zu speichern, also OHNE ID!
     */
    public Artikel(String bezeichnung, String bildURL, double preis) {
        this.aID = -1;
        this.bezeichnung = bezeichnung;
        this.bildURL = bildURL;
        this.preis = preis;
    }

    public long getaID() {
        return aID;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBildURL() {
        return bildURL;
    }

    public void setBildURL(String bildURL) {
        this.bildURL = bildURL;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "aID=" + aID +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", bildURL='" + bildURL + '\'' +
                ", preis=" + preis +
                '}';
    }
}
