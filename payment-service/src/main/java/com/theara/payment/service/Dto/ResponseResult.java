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
public class ResponseResult {
    private UserResponsePayment userResponsePayment;
    private double amount;
    private double balance;
    private Date payDate;
    private PayStatus payStatus;
    private PayMethod payMethod;

}
