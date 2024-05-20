package com.theara.User.Dto;

import com.theara.User.entities.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userName;
    private String email;
    private Date registrationDate;
    private List<CourseDto> courseDtoList;
    private PromotionDto promotionDto;
    private UserType userType;
    private boolean isDelete = false;
}
