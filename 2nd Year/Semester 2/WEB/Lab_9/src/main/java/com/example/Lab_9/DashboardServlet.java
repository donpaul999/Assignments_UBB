package com.example.Lab_9;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DashboardServlet extends HttpServlet {
    private String host;
    private String database;
    private String user;
    private String port;
    private String password;
    private Connection conn;
    private String status;
    private String url;

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
        status = "NOT OK";
        url = "jdbc:postgresql://ec2-52-50-171-4.eu-west-1.compute.amazonaws.com:5432/dfo8rh2he4q2fl";
        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);
        props.setProperty("ssl", "true");
        props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, props);
            status = "OK";
        } catch (SQLException | ClassNotFoundException throwables) {
            status = throwables.toString();
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("pageTitle", "Dashboard - Transportation routes");
        HttpSession session = request.getSession();

        if(session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }

        try{
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM cities");
            ResultSet rs = pst.executeQuery();
            List<City> cities = new ArrayList<>();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getString("id"));
                city.setName(rs.getString("name"));
                cities.add(city);
            }
            request.setAttribute("citiesList", cities);
        } catch (SQLException ex) {
            status = ex.toString();
        }

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
