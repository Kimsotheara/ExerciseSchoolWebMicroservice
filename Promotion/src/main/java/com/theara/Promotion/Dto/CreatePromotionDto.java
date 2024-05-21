package com.theara.Promotion.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromotionDto {
    private String promName;
    private String promDesc;
    private double discountAmount;
    private Date startDate;
    private Date endDate;
    //private Integer courseId;
    private boolean isDelete = false;

}
