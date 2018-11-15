package com.itheima.domain;

import java.io.Serializable;

public class Mapper implements Serializable {
    private String sqlQuery;
    private String resultType;

    public Mapper(String sqlQuery, String resultType) {
        this.sqlQuery = sqlQuery;
        this.resultType = resultType;
    }


    public Mapper() {
    }

    @Override
    public String toString() {
        return "Mapper{" +
                "sqlQuery='" + sqlQuery + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }
}
