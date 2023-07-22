package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.domain.Category;
import com.itheima.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody Category category) {
        categoryService.addCategory(category);
        return R.success("新增分类成功");
    }

    @GetMapping("/page")
    public R<Page> page(@RequestParam("page") int currentPage, int pageSize){
        Page<Category> categoryPage = new Page<>();

        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.orderByAsc(Category::getSort);

        Page<Category> pageInfo = categoryService.page(categoryPage, lqw);
        return R.success(pageInfo);
    }

    @DeleteMapping
    public R<String> delete(Long ids){
        categoryService.removeById(ids);
        return R.success("删除成功");
    }
}
