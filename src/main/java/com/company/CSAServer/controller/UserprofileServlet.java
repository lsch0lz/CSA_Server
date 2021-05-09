package com.company.CSAServer.controller;

import com.company.CSAServer.model.Artikel;
import com.company.CSAServer.model.DBFunc;
import com.company.CSAServer.model.Kunde;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.CSAServer.model.DBFunc.getArtikel;
import static com.company.CSAServer.model.DBFunc.updateEntity;

@WebServlet(name = "UserprofileServlet", value = "/UserprofileServlet")
public class UserprofileServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello World");
    }

    /**
     * Diese Methode erhält die im Profil-jsp eingegebenen Nutzerdaten zur Aktualisierung übergeben.
     * Daraus wird ein neues Objekt Kunde erstellt und der updateEntity-Methode der DBFunc-Klasse übergeben.
     * Sobalt dies geschehen ist, wird der neue Kunde in die aktuelle Session gespeichert und das Servlet veranlasst
     * eine Weiterleitung zur aufrufenden Seite inklusive einer positiven Rückmeldung zur Anzeige an den Nutzer.
     *
     * Sollte die Aktualisierung der Nutzerdaten nicht erfolgt sein, wird eine Fehlermeldung zur Anzeige an den Nutzer an die aufrufende Seite zurückgegeben.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long kID = new Long(request.getParameter("kID"));
        String vorname = request.getParameter("prename");
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String passwort = request.getParameter("password");
        String strasseHnr = request.getParameter("street");
        String ort = request.getParameter("city");
        int plz = new Integer(request.getParameter("postalcode"));
        String land = request.getParameter("state");

        Kunde k = new Kunde(kID, username, passwort, name, vorname, plz, ort, land, strasseHnr);

        //PrintWriter out = response.getWriter();
        //out.print("Hallo " + k.getVorname() + " " + k.getName());
        //out.print("Fehler beim login!");

        if(k != null) {
            try {
                System.out.println(k.toString());
                updateEntity(k);

                HttpSession session = request.getSession();

                session.removeAttribute("kunde");
                session.setAttribute("kunde", k);

                request.removeAttribute("successMessage");
                request.setAttribute("successMessage", "Profil erfolgreich aktualisiert!");

                request.getRequestDispatcher("/loggedin/profile/").forward(request, response);
            } catch(Exception e) {
                e.printStackTrace();

                request.removeAttribute("errorMessage");
                request.setAttribute("errorMessage", "Fehler bei der Aktualisierung des Profils!");

                request.getRequestDispatcher("/loggedin/profile/").forward(request, response);
            }

        } else {
            request.removeAttribute("errorMessage");
            request.setAttribute("errorMessage", "Ungültige Nutzerdaten! Bitte erneut versuchen...");

            request.getRequestDispatcher("/loggedin/profile/").forward(request, response);
        }

    }

    public void destroy() {

    }

}
