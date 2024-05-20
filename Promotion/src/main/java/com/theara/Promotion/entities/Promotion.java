package com.theara.Promotion.entities;

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
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer promId;
    private String promName;
    private String promDesc;
    private double discountAmount;
    private Date startDate;
    private Date endDate;
    private Integer courseId;
    private boolean isDelete = false;

}
