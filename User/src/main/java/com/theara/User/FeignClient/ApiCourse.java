package com.theara.User.FeignClient;

import com.theara.User.Dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(value = "CategoryAndCourse", url = "${api.gateway.url}")
public interface ApiCourse {
    @GetMapping("/course/id/{courseId}")
    CourseDto getCourseById(@PathVariable Integer courseId);
//    @GetMapping("/course/name/{courseId}")
//    Map<String,Object> getNameAndPriceCourseById(@PathVariable Integer courseId);
}
