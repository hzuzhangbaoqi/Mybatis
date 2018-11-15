package com.itheima.dao;

import java.util.List;

public interface ICourseDao {

    List<?> findAll();

    List<?> findOne(String c_id);

}
