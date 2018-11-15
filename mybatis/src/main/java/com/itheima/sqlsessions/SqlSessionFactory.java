package com.itheima.sqlsessions;

import com.itheima.domain.Config;
import com.itheima.io.Resource;
import com.itheima.utils.Conn;
import com.itheima.utils.xmlReadUtils;
import org.dom4j.DocumentException;

import java.io.InputStream;
import java.sql.Connection;

public class SqlSessionFactory {
    private Config cfg;

    public SqlSessionFactory(String path) {
        try {
            InputStream resource = Resource.getResource(path);
            xmlReadUtils readUtils = new xmlReadUtils(resource);
            cfg = readUtils.xmlReadConfig();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


    public SqlSession getSession() throws ClassNotFoundException {
        Conn conn = new Conn(cfg);
        Connection connection = conn.getConnection();
        return new SqlSession(connection,cfg.getMap());
    }


}
