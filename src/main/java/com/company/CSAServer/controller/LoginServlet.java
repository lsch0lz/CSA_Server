package com.company.CSAServer.controller;

import com.company.CSAServer.model.Artikel;
import com.company.CSAServer.model.DBFunc;
import com.company.CSAServer.model.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.CSAServer.model.DBFunc.getArtikel;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello World");
    }

    /**
     * Methode erhält einen Request seitens des login-jsp. Darin enthalten sind die vom Nutzer eingegebenen Nutzerdaten (username und password).
     * Die Methode übergibt diese Parameter an die loginKunde-Funktion der DBFunc-Klasse, welche ein Kunde-Objekt zurückgibt.
     * Ist dieses Objekt == null, so wurde kein Nutzer in der Datenbank mit den übergebenen Credentials gefunden. In diesem Fall wird eine Fehlermeldung an die aufrufende jsp-Seite weitergeleitet.
     * Wenn dieses Objekt != null, dann werden die in der Datenbank hinterlegten Artikel mittels der getArtikeö-Funktion der DBFunc-Klasse geladen und in eine neue Session gespeichert. Auch die Daten des eingeloggten Kunden werden dort gespeichert.
     * Schließlich veranlasst das Servlet eine Weiterleitung zum Bereich /loggedin/.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Kunde k = new DBFunc().loginKunde(username, password);

        //PrintWriter out = response.getWriter();
        //out.print("Hallo " + k.getVorname() + " " + k.getName());
        //out.print("Fehler beim login!");

        if(k != null && k.getUsername().equals(username) && k.getPassword().equals(password)) {
            ArrayList<Artikel> artikel = new ArrayList();
            getArtikel(artikel);

            HttpSession session = request.getSession();
            //String kunde = (String)request.getAttribute("kunde");

            session.setAttribute("kunde", k);
            session.setAttribute("artikel", artikel);

            response.sendRedirect(request.getContextPath() + "/loggedin/");

        } else {
            request.setAttribute("errorMessage", "Ungültige Nutzerdaten!");
            request.getRequestDispatcher("/").forward(request, response);
        }

    }

    public void destroy() {

    }

}
