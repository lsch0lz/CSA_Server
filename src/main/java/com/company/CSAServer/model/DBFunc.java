package com.company.CSAServer.model;
import com.company.CSAServer.secrets;

import java.sql.*;
import java.util.ArrayList;

public class DBFunc implements secrets {
    static long start = -1;

    /**
     * Methode stellt eine Verbindung zur DB her und gibt diese zurück.
     */
    public static Connection conn() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * Methode gibt ihr übergebenen String aus.
     */
    public static void print(String msg){
        System.out.println(msg);
    }

    /**
     * Methode startet eine Zeitstoppung.
     */
    public static void startTime() {
        start = System.nanoTime();
    }

    /**
     * Methode beenden eine Zeitstoppung und gibt die Differenz aus der Start- und Stopzeit aus.
     */
    public static void stopTime(){
        long stop = System.nanoTime();
        long time = stop - start;
        print("Zeit des Querys: " + time);
        print("");
    }

    /**
     * Methode lädt alle Kunden aus der Datenbank, speichert diese in Instanzen des Typs Kunde und speichert diese in
     * die ihr übergebene ArrayList des Typs Kunde.
     */
    public static void getKunden(ArrayList<Kunde> kunden) throws  SQLException {
        Connection conn = conn();
        String query = "SELECT * FROM Kunde";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);

        while(result.next()) {
            String name = result.getString("Name");
            String vorname = result.getString("Vorname");
            int plz = result.getInt("PLZ");
            String ort = result.getString("Ort");
            String land = result.getString("Land");
            int k_id = result.getInt("K-ID");
            String strassehnr = result.getString("StrasseHnr");
            String username = result.getString("username");
            String password = result.getString("password");

            kunden.add(new Kunde(k_id, username, password, name, vorname, plz, ort, land, strassehnr));
        }
        conn.close();
    }

    /**
     * Methode lädt alle Bestellungen aus der Datenbank, speichert diese in Instanzen des Typs Bestellung und speichert diese in
     * die ihr übergebene ArrayList des Typs Bestellung.
     */
    public static void getBestellungen(ArrayList<Bestellung> bestellungen) throws  SQLException {
        Connection conn = conn();
        String query = "SELECT * FROM Bestellung";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);

        while(result.next()) {
            long bID = result.getLong("B-ID");
            long kID = result.getLong("K-ID");

            bestellungen.add(new Bestellung(bID, kID));
        }
        conn.close();
    }

    /**
     * Methode lädt alle "Beinhaltet"-Einträge aus der Datenbank, speichert diese in Instanzen des Typs Beinhaltet und speichert diese in
     * die ihr übergebene ArrayList des Typs Beinhaltet.
     */
    public static void getBeinhaltet(ArrayList<Beinhaltet> beinhaltet) throws  SQLException {
        Connection conn = conn();
        String query = "SELECT * FROM Beinhaltet";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);

        while(result.next()) {
            long aID = result.getLong("A-ID");
            long bID = result.getLong("B-ID");

            beinhaltet.add(new Beinhaltet(aID, bID));
        }
        conn.close();
    }

    /**
     * Methode lädt alle Artikel aus der Datenbank, speichert diese in Instanzen des Typs Artikel und speichert diese in
     * die ihr übergebene ArrayList des Typs Artikel.
     */
    public static void getArtikel(ArrayList<Artikel> artikel) {

        try {
            Connection conn = conn();
            String query = "SELECT * FROM Artikel";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()) {
                long aID = result.getLong("A-ID");
                String bezeichnung = result.getString("Bezeichnung");
                String bildURL = result.getString("BildURL");
                double preis = result.getDouble("Preis");

                artikel.add(new Artikel(aID, bezeichnung, bildURL, preis));
            }
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Methode speichert die ihr übergenene Instanz des Typs Kunde mittels eines PreparedStaitments in die Datenbank.
     */
    public static void insertKundePS(Kunde kunde) throws SQLException {
        Connection conn = conn();
        String insert = "INSERT INTO Kunde (Name, Vorname, PLZ, Ort, Land, StrasseHnr, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(insert);

        statement.setString(1, kunde.getName());
        statement.setString(2, kunde.getVorname());
        statement.setInt(3, kunde.getPlz());
        statement.setString(4, kunde.getOrt());
        statement.setString(5, kunde.getLand());
        statement.setString(6, kunde.getStrasseHnr());
        statement.setString(7, kunde.getUsername());
        statement.setString(8, kunde.getPassword());

        statement.executeUpdate();
        conn.close();
    }

    /**
     * Methode speichert die ihr übergenene Instanz des Typs Bestellung mittels eines PreparedStaitments in die Datenbank.
     */
    public static void insertBestellungPS(Bestellung bestellung) throws SQLException {
        Connection conn = conn();
        String insert = "INSERT INTO Bestellung (`K-ID`) VALUES (?)";

        PreparedStatement statement = conn.prepareStatement(insert);

        statement.setLong(1, bestellung.getkID());

        statement.executeUpdate();
        conn.close();
    }

    /**
     * Methode speichert die ihr übergenene Instanz des Typs Beinhaltet mittels eines PreparedStaitments in die Datenbank.
     */
    public static void insertBeinhaltetPS(Beinhaltet beinhaltet) throws SQLException {
        Connection conn = conn();
        String insert = "INSERT INTO Beinhaltet (`A-ID`, `B-ID`) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(insert);

        statement.setLong(1, beinhaltet.getaID());
        statement.setLong(2, beinhaltet.getbID());

        statement.executeUpdate();
        conn.close();
    }

    /**
     * Methode speichert die ihr übergenene Instanz des Typs Artikel mittels eines PreparedStaitments in die Datenbank.
     */
    public static void insertArtikelPS(Artikel artikel) throws SQLException {
        Connection conn = conn();
        String insert = "INSERT INTO Artikel (Bezeichnung, BildURL, Preis) VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(insert);

        statement.setString(1, artikel.getBezeichnung());
        statement.setString(2, artikel.getBildURL());
        statement.setDouble(3, artikel.getPreis());

        statement.executeUpdate();
        conn.close();
    }

    /**
     * Methode leert die Datenbanktabelle mit den ihr übergebenen Tabellennamen.
     */
    public static void emptyTable(String table) throws SQLException {
        Connection conn = conn();

        String delete = "TRUNCATE TABLE `" + table + "`;";
        Statement stmt = conn.createStatement();
        stmt.execute(delete);

        conn.close();
    }

    /**
     * Diese Methode aktualisiert alle Parameter der ihr übergebenen Entität.
     */
    public static void updateEntity(Object entity) throws Exception {
        Connection conn = conn();
        String update = "";

        String praefix = "com.company.CSAServer.model.";

        //System.out.println(entity.getClass());

        if(entity.getClass().getName().equals(praefix + "Kunde")) {
            Kunde kunde = (Kunde) entity;
            update = "UPDATE Kunde SET Name='" + kunde.getName() + "', Vorname='" + kunde.getVorname() + "', PLZ= " + kunde.getPlz() + ", Ort='" + kunde.getOrt() + "', Land='" + kunde.getLand() + "', StrasseHnr='" + kunde.getStrasseHnr() + "', username='" + kunde.getUsername() + "', password='" + kunde.getPassword() + "' WHERE `K-ID`=" + kunde.getkID() + ";";

        } else if(entity.getClass().getName().equals(praefix + "Bestellung")) {
            throw new Exception("Entitaet noch nicht implementiert!");

        } else if(entity.getClass().getName().equals(praefix + "Beinhaltet")) {
            throw new Exception("Entitaet noch nicht implementiert!");

        } else if(entity.getClass().getName().equals(praefix + "Artikel")) {
            Artikel artikel = (Artikel) entity;
            update = "UPDATE Artikel SET Bezeichnung='" + artikel.getBezeichnung() + "', BildURL='" + artikel.getBildURL() + "', Preis=" + artikel.getPreis() + " WHERE `A-ID`=" + artikel.getaID() + ";";

        } else {
            throw new Exception("Keine gültige Entitaet übergeben!");

        }

        Statement stmt = conn.createStatement();
        stmt.execute(update);
        conn.close();
    }

    /**
     * Methode prüft, ob ein Kunde mit den ihr übergebenen Parametern in der DB existiert.
     * Falls nein, wird eine leere Referenz des Typs Kunde zurückgegeben.
     * Falls ja, wird eine neue Instanz des Typs Kunde mit all den zu diesem gehörogen Attributen zurückgegeben.
     * @param givenUsername = vom Nutzer eingegebener Nutzername
     * @param givenPassword = vom Nutzer eingegebenes Passwort
     */
    public static Kunde loginKunde(String givenUsername, String givenPassword) {
        Kunde loggedin = null;

        try {
            Connection conn = conn();
            String query = "SELECT * FROM Kunde WHERE username='" + givenUsername + "' AND password='" + givenPassword + "';";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()) {
                String name = result.getString("Name");
                String vorname = result.getString("Vorname");
                int plz = result.getInt("PLZ");
                String ort = result.getString("Ort");
                String land = result.getString("Land");
                int k_id = result.getInt("K-ID");
                String strassehnr = result.getString("StrasseHnr");
                String username = result.getString("username");
                String password = result.getString("password");

                loggedin = new Kunde(k_id, username, password, name, vorname, plz, ort, land, strassehnr);

            }
            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return loggedin;
    }
}
