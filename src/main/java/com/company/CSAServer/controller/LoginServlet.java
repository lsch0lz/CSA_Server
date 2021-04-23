package com.company.CSAServer.controller;

import com.company.CSAServer.model.Artikel;
import com.company.CSAServer.model.DBFunc;
import com.company.CSAServer.model.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.company.CSAServer.model.DBFunc.getArtikel;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Hello World");
    }

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
