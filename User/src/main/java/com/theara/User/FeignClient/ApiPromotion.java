package com.theara.User.FeignClient;

import com.theara.User.Dto.PromotionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(value = "Promotion", url = "http://localhost:8089/promotion")
public interface ApiPromotion {
    @GetMapping("/id/{promId}")
    PromotionDto getPromotionById(@PathVariable Integer promId);
}
