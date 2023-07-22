package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.domain.Employee;

public interface EmployeeService extends IService<Employee> {
    Employee employeeLogin(Employee employee);
    Integer addEmployee(Employee employee, Long hostID);
    Page getPage(int currentPage, int pageSize, String name);
    Integer updateEmployee(Employee employee);
//    Integer updateEmployee(Employee employee, Long hostID);

}
