package com.itheima.result;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultHandle {

    private ResultSet resultSet;
    private String resultType;

    public ResultHandle() {
    }

    public ResultHandle(ResultSet resultSet, String resultType) {
        this.resultSet = resultSet;
        this.resultType = resultType;
    }


    public List<?> handleResult() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        List<Object> list = new ArrayList<>();
        ResultSetMetaData data = resultSet.getMetaData();
        int columnCount = data.getColumnCount();

        //拿到列名
        List<String> colNameList = new ArrayList<>();
        for(int i = 1; i <= columnCount;i++){
            String columnLabel = data.getColumnLabel(i);
            String s = columnLabel.substring(0, 1).toUpperCase() + columnLabel.substring(1);
            colNameList.add(s);
        }




        while(resultSet.next()){
            //根据反射拿到方法名数组
            Class clazz = Class.forName(resultType);
            Object obj = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();

            for (int i = 0;i<colNameList.size();i++){
                for (Method method : methods) {
                    String colName = "set" + colNameList.get(i);
                    if(colName!=null && method.getName()!=null){
                        if(colName.equals(method.getName())){
                            Object objectValue = resultSet.getObject(colNameList.get(i));
                            method.invoke(obj,objectValue);
                        }

                    }
                }
            }
            list.add(obj);
        }

        return list;

    }


}
