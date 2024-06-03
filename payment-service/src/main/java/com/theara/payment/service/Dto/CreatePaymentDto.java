package com.theara.payment.service.Dto;

import com.theara.payment.service.entities.PayMethod;
import com.theara.payment.service.entities.PayStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentDto {
    private Integer userId;
    private double aDouble;
    private Date payDate;
    private PayStatus payStatus;
    private PayMethod payMethod;

}
