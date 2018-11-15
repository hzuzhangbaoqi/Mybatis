package com.itheima.proxy;

import com.itheima.domain.Mapper;
import com.itheima.result.ResultHandle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Map;

public class ProxyUtils implements InvocationHandler {
    private PreparedStatement pstmt;
    private Map<String,Mapper> mapper;
    private Connection conn;

    public ProxyUtils(Connection conn, Map<String,Mapper> mapper) {
        this.conn = conn ;
        this.mapper = mapper;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        String declaringName = method.getDeclaringClass().getName();
        String key = declaringName + "." + methodName;
        if (!mapper.containsKey(key)) {
            System.out.println("非法参数");
            return null;
        }

        Mapper mapper = this.mapper.get(key);
        String sqlQuery = mapper.getSqlQuery();

        pstmt = conn.prepareStatement(sqlQuery);


        System.out.println(Arrays.toString(args));
        if(args!= null){
           for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i+1,args[i]);
            }
        }

        String resultType = mapper.getResultType();


        ResultSet resultSet = pstmt.executeQuery();

        return new ResultHandle(resultSet,resultType).handleResult();
    }
}
