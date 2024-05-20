package com.theara.CategoryAndCourse.Dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryDto {
    private String cateName;
    private String cateDesc;
    private boolean isDelete = false;
}
