package com.theara.CategoryAndCourse.service;

import com.theara.CategoryAndCourse.Dto.course.CreateCourseDto;
import com.theara.CategoryAndCourse.Dto.course.UpdateCourseDto;
import com.theara.CategoryAndCourse.constant.ResponseDTO;
import com.theara.CategoryAndCourse.constant.Status;
import com.theara.CategoryAndCourse.entities.Course;
import com.theara.CategoryAndCourse.exception.ExceptionNotFound;
import com.theara.CategoryAndCourse.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper mapper;

    public ResponseDTO getAllByIsDeleteFalse(){
        try {
            Iterable<Course> courses = courseRepository.findAllByIsDelete();
            return new ResponseDTO("OK", courses);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch courses", Status.FAILED.value(), 500);
        }
    }
    public Course getCourseById(Integer id){
        return this.checkCourse(id);
    }
    public Map<String,Object> getCourseNameAndPriceById(Integer id){
        try {
            return this.courseRepository.findCourseNameAndPriceById(id);
        }catch (Exception e){
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("error", "Failed to fetch courses");
            return errorMap;
        }
    }
    public ResponseDTO getAll() {
        try {
            List<Course> courses = courseRepository.findAll();
            return new ResponseDTO("OK", courses);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch courses", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO create(CreateCourseDto createCourseDto) {
        try {
            Course course = mapper.map(createCourseDto, Course.class);
            course = courseRepository.save(course);
            return new ResponseDTO("Save OK", course);
        } catch (Exception e) {
            return new ResponseDTO("Failed to create course", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO update(UpdateCourseDto updateCourseDto){
        try{
            this.checkCourse(updateCourseDto.getCourseId());
            Course course = mapper.map(updateCourseDto,Course.class);
            course = this.courseRepository.save(course);
            return new ResponseDTO("Update Successfully",course);
        }catch (ExceptionNotFound e) {
            return new ResponseDTO(e.getMessage(), Status.FAILED.value(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseDTO("Failed to update Course", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO delete(Integer id){
        Course course = this.checkCourse(id);
        course.setDelete(true);
        this.courseRepository.save(course);
        return new ResponseDTO("delete OK");
    }
    private Course checkCourse(Integer id){
        Optional<Course> course = this.courseRepository.findByCourseId(id);
        if (course.isPresent())
           return course.get();
        throw new ExceptionNotFound("Course Not Found", 404);
    }
}
