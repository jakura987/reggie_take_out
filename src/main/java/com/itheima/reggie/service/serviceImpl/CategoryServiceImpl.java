package com.itheima.reggie.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.Exception.BusinessException;
import com.itheima.reggie.dao.CategoryDao;
import com.itheima.reggie.domain.Category;
import com.itheima.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao,Category> implements CategoryService  {
    @Autowired
    private CategoryDao categoryDao;

    /**
     *
     * @param category
     * @return
     */
    public int addCategory(Category category){
        List<Category> categoryList = categoryDao.selectList(null);
        for (Category cg :
                categoryList) {
            if(cg.getName().equals(category.getName())){
                throw new BusinessException(cg.getName() + " 已存在");
            }
        }
        return categoryDao.insert(category);
    }
}
