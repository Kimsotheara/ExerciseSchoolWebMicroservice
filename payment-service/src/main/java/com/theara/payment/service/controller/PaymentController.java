package com.theara.payment.service.controller;

import com.theara.payment.service.Dto.CreatePaymentDto;
import com.theara.payment.service.Dto.ResponseResult;
import com.theara.payment.service.Dto.UpdatePaymentDto;
import com.theara.payment.service.constant.ResponseDTO;
import com.theara.payment.service.entities.Payment;
import com.theara.payment.service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseDTO findAll(){
        return this.paymentService.getAll();
    }
    @GetMapping("/id/{id}")
    public ResponseResult findPaymentById(@PathVariable Integer id){
        return this.paymentService.getPaymentById(id);
    }
    @PostMapping("/create")
    public ResponseDTO create(@RequestBody CreatePaymentDto createPaymentDto){
        return this.paymentService.create(createPaymentDto);
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody UpdatePaymentDto updatePaymentDto){
        return this.paymentService.update(updatePaymentDto);
    }
}
