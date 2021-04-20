package com.example.CSA_Server.model;
import com.example.CSA_Server.model.Bestellung;
import com.example.CSA_Server.secrets;

import java.sql.*;

public class DBFunc implements secrets {

    public static Connection conn() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Verbindung wurde hergestellt");
        return conn;
    }

    public static String getBestellung() throws SQLException{
        Connection conn = conn();
        String query = "SELECT * FROM Bestellung";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);
        Bestellung b1 = null;
        while(result.next()) {
            long bID = result.getLong("B-ID");
            long kID = result.getLong("K-ID");
            b1 = new Bestellung(bID, kID);
        }
        String string = String.valueOf(b1);
        conn.close();
        //System.out.println(string);
        return string;
    }



}
