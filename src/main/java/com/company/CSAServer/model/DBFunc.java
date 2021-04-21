package com.company.CSAServer.model;
import com.company.CSAServer.secrets;

import java.sql.*;
import java.util.ArrayList;

public class DBFunc implements secrets {
    static long start = -1;

    public static Connection conn() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void print(String msg){
        System.out.println(msg);
    }

    public static void startTime() {
        start = System.nanoTime();
    }

    public static void stopTime(){
        long stop = System.nanoTime();
        long time = stop - start;
        print("Zeit des Querys: " + time);
        print("");
    }

    //select
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

    public static void getArtikel(ArrayList<Artikel> artikel) throws  SQLException {
        Connection conn = conn();
        String query = "SELECT * FROM Artikel";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);

        while(result.next()) {
            long aID = result.getLong("A-ID");
            String bezeichnung = result.getString("Bezeichnung");
            double preis = result.getDouble("Preis");

            artikel.add(new Artikel(aID, bezeichnung, preis));
        }
        conn.close();
    }

    //insert PreparedStatement
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

    public static void insertBestellungPS(Bestellung bestellung) throws SQLException {
        Connection conn = conn();
        String insert = "INSERT INTO Bestellung (`K-ID`) VALUES (?)";

        PreparedStatement statement = conn.prepareStatement(insert);

        statement.setLong(1, bestellung.getkID());

        statement.executeUpdate();
        conn.close();
    }

    public static void insertBeinhaltetPS(Beinhaltet beinhaltet) throws SQLException {
        Connection conn = conn();
        String insert = "INSERT INTO Beinhaltet (`A-ID`, `B-ID`) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(insert);

        statement.setLong(1, beinhaltet.getaID());
        statement.setLong(2, beinhaltet.getbID());

        statement.executeUpdate();
        conn.close();
    }

    public static void insertArtikelPS(Artikel artikel) throws SQLException {
        Connection conn = conn();
        String insert = "INSERT INTO Artikel (Bezeichnung, Preis) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(insert);

        statement.setString(1, artikel.getBezeichnung());
        statement.setDouble(2, artikel.getPreis());

        statement.executeUpdate();
        conn.close();
    }

    //delete Statement
    public static void emptyTable(String table) throws SQLException {
        Connection conn = conn();

        String delete = "TRUNCATE TABLE `" + table + "`;";
        Statement stmt = conn.createStatement();
        stmt.execute(delete);

        conn.close();
    }

    //update Statement
    public static void updateEntity(Object entity) throws Exception {
        Connection conn = conn();
        String update = "";

        String praefix = "uebung3.DTO.";

        if(entity.getClass().getName().equals(praefix + "Kunde")) {
            Kunde kunde = (Kunde) entity;
            update = "UPDATE Kunde SET Name='" + kunde.getName() + "', Vorname='" + kunde.getVorname() + "', PLZ= " + kunde.getPlz() + ", Ort='" + kunde.getOrt() + "', Land='" + kunde.getLand() + "', StrasseHnr='" + kunde.getStrasseHnr() + "', username='" + kunde.getUsername() + "', password='" + kunde.getPassword() + "' WHERE `K-ID`=" + kunde.getkID() + ";";

        } else if(entity.getClass().getName().equals(praefix + "Bestellung")) {
            throw new Exception("Entitaet noch nicht implementiert!");

        } else if(entity.getClass().getName().equals(praefix + "Beinhaltet")) {
            throw new Exception("Entitaet noch nicht implementiert!");

        } else if(entity.getClass().getName().equals(praefix + "Artikel")) {
            Artikel artikel = (Artikel) entity;
            update = "UPDATE Artikel SET Bezeichnung='" + artikel.getBezeichnung() + "', Preis=" + artikel.getPreis() + " WHERE `A-ID`=" + artikel.getaID() + ";";

        } else {
            throw new Exception("Keine gültige Entitaet übergeben!");

        }

        Statement stmt = conn.createStatement();
        stmt.execute(update);
        conn.close();
    }

    public Kunde loginKunde(String givenUsername, String givenPassword) {
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
