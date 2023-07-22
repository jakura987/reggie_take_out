package com.itheima.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.domain.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {
    @Insert("INSERT INTO employee (username, password) VALUES (#{username}, #{password})")
    int insertEmployee(Employee employee);
}
