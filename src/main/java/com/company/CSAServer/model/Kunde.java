package com.company.CSAServer.model;

public class Kunde {
    private final long kID;
    private String username;
    private String password;
    private String name;
    private String vorname;
    private int plz;
    private String ort;
    private String land;
    private String strasseHnr;

    /**
     * Konstruktor um die Entität aus der DB zu laden!
     */
    public Kunde(long kID, String username, String password, String name, String vorname, int plz, String ort, String land, String strasseHnr) {
        this.kID = kID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.vorname = vorname;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
        this.strasseHnr = strasseHnr;
    }

    /**
     * Konstruktor um Entitaet in DB zu speichern, also OHNE ID!
     */
    public Kunde(String username, String password, String name, String vorname, int plz, String ort, String land, String strasseHnr) {
        this.kID = -1;
        this.username = username;
        this.password = password;
        this.name = name;
        this.vorname = vorname;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
        this.strasseHnr = strasseHnr;
    }

    public long getkID() {
        return kID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getStrasseHnr() {
        return strasseHnr;
    }

    public void setStrasseHnr(String strasseHnr) {
        this.strasseHnr = strasseHnr;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "kID=" + kID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", plz=" + plz +
                ", ort='" + ort + '\'' +
                ", land='" + land + '\'' +
                ", strasseHnr='" + strasseHnr + '\'' +
                '}';
    }

}
