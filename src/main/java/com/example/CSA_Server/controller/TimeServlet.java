package com.example.CSA_Server.controller;
import java.util.Date;

public class TimeServlet {

    public String getTime(){
        return new Date().toString();
    }
}
