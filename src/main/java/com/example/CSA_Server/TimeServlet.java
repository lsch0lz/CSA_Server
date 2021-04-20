package com.example.CSA_Server;
import java.util.Date;

public class TimeServlet {

    public String getTime(){
        return new Date().toString();
    }
}
