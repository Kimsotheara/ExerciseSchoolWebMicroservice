package com.theara.CategoryAndCourse.controller;

import com.theara.CategoryAndCourse.Dto.category.CreateCategoryDto;
import com.theara.CategoryAndCourse.Dto.category.UpdateCategoryDto;
import com.theara.CategoryAndCourse.constant.ResponseDTO;
import com.theara.CategoryAndCourse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cate")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseDTO findByIsDeleteFalse(){
        return this.categoryService.getAllByIsDeleteFalse();
    }

    @GetMapping("/all")
    public ResponseDTO findAll() {
        return this.categoryService.getAllCate();
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody CreateCategoryDto categoryDto) {
        return this.categoryService.createCate(categoryDto);
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody UpdateCategoryDto updateTodoDto) {
        return this.categoryService.updateCate(updateTodoDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.categoryService.delete(id);
    }
}
