package com.theara.CategoryAndCourse.repository;

import com.theara.CategoryAndCourse.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Optional<Course> findByCourseId(Integer id);
    @Query(value = "SELECT * FROM course WHERE is_delete=false",nativeQuery = true)
    Iterable<Course> findAllByIsDelete();

    @Query(value = "SELECT course_name,course_price FROM course WHERE course_id = ?1", nativeQuery = true)
    Map<String,Object> findCourseNameAndPriceById(Integer id);
}
