package com.itheima.reggie.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.Exception.BusinessException;
import com.itheima.reggie.dao.EmployeeDao;
import com.itheima.reggie.domain.Employee;
import com.itheima.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 员工登录
     *
     * @param employee
     * @return
     */
    @Override
    public Employee employeeLogin(Employee employee) throws BusinessException {
        //md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //查找
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeDao.selectOne(lqw);
        if (emp == null) {
            throw new BusinessException("用户名不存在");
        }
        if (!emp.getPassword().equals(password)) {
            throw new BusinessException("密码错误");
        }
        if (emp.getStatus() == 0) {
            throw new BusinessException("账号已禁");
        }
        return emp;
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @Override
    public Integer addEmployee(Employee employee, Long hostID) {
        List<Employee> employeeList = employeeDao.selectList(null);
        for (Employee emp :
                employeeList) {
            if (emp.getUsername().equals(employee.getUsername())) {
                throw new BusinessException("用户名 " + emp.getUsername() +" 已使用");
            }
        }
        //设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setCreateTime(new Date());tablefield代替
//        employee.setUpdateTime(new Date());
//        employee.setCreateUser(hostID);
//        employee.setUpdateUser(hostID);
        return employeeDao.insert(employee);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public Page<Employee> getPage(int currentPage, int pageSize, String name) {
        Page<Employee> pageInfo = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null,Employee::getName,name);
        lqw.orderByDesc(Employee::getUpdateTime);
        return employeeDao.selectPage(pageInfo, lqw);
    }

    @Override
    public Integer updateEmployee(Employee employee) {
        return employeeDao.updateById(employee);
    }

//    @Override
//    public Integer updateEmployee(Employee employee, Long hostID) {
//        employee.setUpdateTime(new Date());tablefield代替
//        employee.setUpdateUser(hostID);
//        return employeeDao.updateById(employee);
//    }


}
