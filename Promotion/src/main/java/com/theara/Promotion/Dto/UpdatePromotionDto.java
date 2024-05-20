package com.theara.Promotion.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePromotionDto extends CreatePromotionDto{
    @Positive(message = "id must greater than 0!")
    @NotNull(message = "id must be not missing!")
    private Integer promId;
}
