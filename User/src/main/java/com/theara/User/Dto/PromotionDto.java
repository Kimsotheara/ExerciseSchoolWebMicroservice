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
public class PromotionDto {
    private Integer promId;
    private String promName;
    private String promDesc;
    private double discountAmount;
    private Date startDate;
    private Date endDate;
    private boolean isDelete = false;

}
