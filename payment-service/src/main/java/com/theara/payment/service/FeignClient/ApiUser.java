package com.theara.payment.service.FeignClient;

import com.theara.payment.service.Dto.UserResponsePayment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "User", url = "${api.gateway.url}")
public interface ApiUser {
    @GetMapping("/user/find/id/{id}")
    UserResponsePayment getUserForPayment(@PathVariable Integer id);
}
