package com.company.CSAServer.controller;

import com.company.CSAServer.model.DBFunc;
import com.company.CSAServer.model.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

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
            request.setAttribute("kunde", k);
            request.getRequestDispatcher("/loggedin/").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Ungültige Nutzerdaten!");
            request.getRequestDispatcher("/").forward(request, response);
        }

    }

    public void destroy() {

    }

}
