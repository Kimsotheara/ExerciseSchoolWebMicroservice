package com.theara.User.FeignClient;

import com.theara.User.Dto.PromotionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Promotion", url = "${api.gateway.url}")
public interface ApiPromotion {
    @GetMapping("/promotion/id/{promId}")
    PromotionDto getPromotionById(@PathVariable Integer promId);
}
