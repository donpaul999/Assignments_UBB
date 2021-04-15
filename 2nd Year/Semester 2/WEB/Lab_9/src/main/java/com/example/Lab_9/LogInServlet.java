package com.example.Lab_9;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class LogInServlet extends HttpServlet {
    private String host;
    private String database;
    private String user;
    private String port;
    private String password;

    public void init() {
        String fName = "/Users/paulcolta/Desktop/GitHub/Assignments_FP/2nd Year/Semester 2/WEB/Lab_9/credentials.txt";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(isr);
        String line = null;
        Integer i = 0;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
                switch (i) {
                    case 0:
                        this.host = line;
                    case 1:
                        this.database = line;
                    case 2:
                        this.user = line;
                    case 3:
                        this.port = line;
                    case 4:
                        this.password = line;
                    default:
                        break;
                }
                i += 1;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String status = "NOT OK";
        String url = "jdbc:postgresql://ec2-52-50-171-4.eu-west-1.compute.amazonaws.com:5432/dfo8rh2he4q2fl";
        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);
        props.setProperty("ssl","true");
        try {
            Connection conn = DriverManager.getConnection(url, props);
            status = "OK";
        } catch (SQLException throwables) {
            status = throwables.toString();
        }

        request.setAttribute("status", status);
        request.getRequestDispatcher("log-in.jsp").forward(request, response);
    }

}
