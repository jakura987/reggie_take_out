package com.itheima.reggie;

import com.itheima.reggie.domain.Category;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReggieTakeOutApplicationTests {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CategoryService categoryService;

    @Test
    void contextLoads(){
    }

    @Test
    void employeeLoginTest(){
//        Employee employee = new Employee();
//        employee.setUsername("admin");
//        employee.setPassword("e10adc3949ba59abbe56e057f20f883e");
//        R<Employee> r = employeeService.employeeLogin(employee);
//        System.out.println(r);
    }
    @Test
    void PaginationTest(){
//        Page page = employeeService.page(1, 2, null);
//        System.out.println(page.getSize() + "\n" +page.getTotal() + "\n" + page.getPages());
//        System.out.println(page.getRecords());
    }
    @Test
    void updateEmployeeStatusTest(){
        //wang
//        1682261683338498050
//        Employee employee = new Employee();
//        employee.setId(1682261683338498050L);
//        employee.setStatus(0);
//        employee.setUpdateUser(1L);
//        employeeService.updateEmployee(employee);
    }

    @Test
    void mybatisAutoIncrementTest(){
//        User user = new User();
//        user.setName("Tom");
//        user.setPassword("123");
//        userDao.insertUser(user);
//        userService.updateUser(user);


    }
    @Test
    void categoryTest(){
        Category category = new Category();
        category.setName("闽南菜");
        category.setType(1);
        categoryService.save(category);

    }

}
