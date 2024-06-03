package com.theara.payment.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payId;
    private Integer userId;
    private double amount;
    private double balance;
    private Date payDate;
    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

}
