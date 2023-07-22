package com.itheima.reggie.controller;

import com.itheima.reggie.Exception.BusinessException;
import com.itheima.reggie.common.R;
import com.itheima.reggie.domain.Category;
import com.itheima.reggie.domain.Employee;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(BusinessException.class)
    public R<Employee> doBusinessException(BusinessException ex){
        return R.error(ex.getMessage());
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<Category> doSqlIntegrityException(SQLIntegrityConstraintViolationException ex){
        return R.error(ex.getMessage());
    }


//    @ExceptionHandler
    public R<Employee> doAllException(Exception ex){
        return R.error("没招了");
    }
}
