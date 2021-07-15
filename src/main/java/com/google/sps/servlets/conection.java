package com.google.sps.servlets;

import java.sql.*;

public class conection {

    public Connection conn() throws SQLException {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql:///team5project?cloudSqlInstance=wramos-sps-summer21:us-central1:team5project&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=root&password=spsTeam5&useSSL=false");

        return conn;
    }
}
