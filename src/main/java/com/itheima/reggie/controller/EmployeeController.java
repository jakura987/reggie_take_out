package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.Exception.BusinessException;
import com.itheima.reggie.common.R;
import com.itheima.reggie.domain.Employee;
import com.itheima.reggie.service.EmployeeService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import lombok.extern.slf4j.Slf4j;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param httpServletRequest
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest httpServletRequest, @RequestBody Employee employee) {
        Employee emp = employeeService.employeeLogin(employee);
        httpServletRequest.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        //获取当前管理员的id,用了threadLocal,下面步骤可以省略(hostId其实没有加进去)
        Long hostId = (Long) request.getSession().getAttribute("employee");
        employeeService.addEmployee(employee, hostId);
        return R.success("添加成功");
    }

    @GetMapping("/page")
    public R<Page> page(@RequestParam("page") int currentPage, int pageSize, String name) {
        Page employeeServicePage = employeeService.getPage(currentPage, pageSize, name);
        return R.success(employeeServicePage);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return R.success("修改成功");
    }

//    @PutMapping
//    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
//        Long hostID = (Long) request.getSession().getAttribute("employee");
//        employeeService.updateEmployee(employee, hostID);
//        return R.success("修改成功");
//    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if(employee != null){
            return R.success(employee);
        }
            return R.error("为查到该员工信息");
    }


}
