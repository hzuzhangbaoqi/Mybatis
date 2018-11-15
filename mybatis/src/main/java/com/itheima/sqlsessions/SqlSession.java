package com.itheima.sqlsessions;

import com.itheima.domain.Mapper;
import com.itheima.proxy.ProxyUtils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Map;

public class SqlSession {
    private Connection conn;
    private Map<String,Mapper> map;


    public SqlSession(Connection conn, Map<String, Mapper> map){
        this.conn = conn;
        this.map = map;
    }

    public <T> T getMapper(Class<T> c){
        return (T) Proxy.newProxyInstance(c.getClassLoader(),new Class[]{c},new ProxyUtils(conn,map));
    }
}
