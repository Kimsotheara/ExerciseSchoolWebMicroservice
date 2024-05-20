package com.theara.CategoryAndCourse.Dto.course;

import com.theara.CategoryAndCourse.entities.Category;
import com.theara.CategoryAndCourse.entities.CourseTeach;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseDto {
    private String courseName;
    private String courseDesc;
    private CourseTeach courseTeach;
    private String courseEmail;
    private Category category;
    private double coursePrice;
}
