package com.theara.CategoryAndCourse.controller;

import com.theara.CategoryAndCourse.Dto.course.CreateCourseDto;
import com.theara.CategoryAndCourse.Dto.course.UpdateCourseDto;
import com.theara.CategoryAndCourse.constant.ResponseDTO;
import com.theara.CategoryAndCourse.entities.Course;
import com.theara.CategoryAndCourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseDTO findByIsDeleteFalse(){
        return this.courseService.getAllByIsDeleteFalse();
    }
    @GetMapping("/id/{id}")
    public Course getCourseById(@PathVariable Integer id){
        return this.courseService.getCourseById(id);
    }
    @GetMapping("/name/{id}")
    public Map<String,Object> getCourseNameAndPriceById(@PathVariable Integer id){
        return this.courseService.getCourseNameAndPriceById(id);
    }
    @GetMapping("/all")
    public ResponseDTO findAll() {
        return this.courseService.getAll();
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody CreateCourseDto categoryDto) {
        return this.courseService.create(categoryDto);
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody UpdateCourseDto updateCourseDto) {
        return this.courseService.update(updateCourseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.courseService.delete(id);
    }
}
