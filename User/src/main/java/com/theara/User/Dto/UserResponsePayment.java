package com.theara.User.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponsePayment {
    private String userName;
    private String email;
    private Date registrationDate;
    private double amountCourse;
    private double promotionDiscount;
    private double totalPayment;
}
