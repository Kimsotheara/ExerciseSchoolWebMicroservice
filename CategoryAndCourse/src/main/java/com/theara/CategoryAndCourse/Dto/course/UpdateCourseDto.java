package com.theara.CategoryAndCourse.Dto.course;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseDto extends CreateCourseDto{
    @Positive(message = "id must greater than 0!")
    @NotNull(message = "id must be not missing!")
    private Integer courseId;
}
