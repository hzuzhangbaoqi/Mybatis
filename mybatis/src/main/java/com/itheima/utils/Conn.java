package com.itheima.utils;

import com.itheima.domain.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private Config cfg;

    public Conn(){};

    public Conn(Config cfg){
        this.cfg = cfg ;
    }

    public Connection getConnection() throws ClassNotFoundException {


        Connection conn = null;

        Class.forName(cfg.getDriver());
        try {
            conn =  DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(cfg);

        return conn;
    }

}
