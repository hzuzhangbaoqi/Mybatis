package com.itheima.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Config implements Serializable {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Map<String,Mapper> map = new HashMap<String, Mapper>();

    public Config() {
    }

    @Override
    public String toString() {
        return "Config{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", map=" + map +
                '}';
    }

    public Config(String driver, String url, String username, String password, Map<String, Mapper> map) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.map = map;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Mapper> getMap() {
        return map;
    }

    public void setMap(Map<String, Mapper> map) {
        this.map = map;
    }
}
