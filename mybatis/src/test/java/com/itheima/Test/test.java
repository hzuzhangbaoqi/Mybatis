package com.itheima.Test;

import com.itheima.dao.ICourseDao;
import com.itheima.sqlsessions.SqlSession;
import com.itheima.sqlsessions.SqlSessionFactory;

import java.util.List;

public class test {
    public static void main(String[] args) throws ClassNotFoundException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory("SqlMapConfig.xml");
        SqlSession session = sqlSessionFactory.getSession();
        ICourseDao mapper = session.getMapper(ICourseDao.class);
        List<?> list1 = mapper.findAll();
        List<?> list2 = mapper.findOne("1001");
        System.out.println(list1);
        System.out.println(list2);
    }
}
