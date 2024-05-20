package com.theara.CategoryAndCourse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String courseName;
    private String courseDesc;
    @Enumerated(EnumType.STRING)
    private CourseTeach courseTeach;
    private double coursePrice;
    private String courseEmail;
    private boolean isDelete = false;
    private boolean isPromotion = false;
    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

}
