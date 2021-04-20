package com.company.CSAServer.controller;
import java.util.Date;

public class TimeServlet {

    public String getTime(){
        return new Date().toString();
    }
}
