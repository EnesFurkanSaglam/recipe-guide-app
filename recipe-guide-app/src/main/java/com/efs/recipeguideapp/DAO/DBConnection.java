package com.efs.recipeguideapp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
        private String url = "jdbc:mysql://localhost:3306/recipe-guide-app";
        private String user = "root";
        private String password = "root";


        public Connection connect() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }
    }

