package com.theara.payment.service.service;

import com.theara.payment.service.Dto.CreatePaymentDto;
import com.theara.payment.service.Dto.ResponseResult;
import com.theara.payment.service.Dto.UpdatePaymentDto;
import com.theara.payment.service.Dto.UserResponsePayment;
import com.theara.payment.service.FeignClient.ApiUser;
import com.theara.payment.service.constant.ResponseDTO;
import com.theara.payment.service.constant.Status;
import com.theara.payment.service.entities.PayMethod;
import com.theara.payment.service.entities.PayStatus;
import com.theara.payment.service.entities.Payment;
import com.theara.payment.service.exception.ExceptionNotFound;
import com.theara.payment.service.repository.PaymentRepository;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ApiUser apiUser;
    public ResponseDTO getAll(){
        try {
            List<Payment> payments = this.paymentRepository.findAll();
            List<ResponseResult> results = new ArrayList<>();

            for (Payment payment : payments) {
                ResponseResult result = new ResponseResult();
                UserResponsePayment user;
                try {
                    user = this.apiUser.getUserForPayment(payment.getUserId());
                } catch (FeignException e) {
                    throw new RuntimeException("Failed to retrieve user for payment ID: " + payment.getPayId(), e);
                }

                result.setUserResponsePayment(user);
                result.setAmount(payment.getAmount());
                result.setBalance(payment.getBalance());
                result.setPayMethod(payment.getPayMethod());
                result.setPayDate(payment.getPayDate());
                result.setPayStatus(payment.getPayStatus());
                results.add(result);
            }

            return new ResponseDTO("Payment is Found", "SUCCESS", 200, results);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch payment", "FAILED", 500);
        }
    }
    public ResponseResult getPaymentById(Integer id){
        Payment payment = this.checkPayment(id);

        ResponseResult result = new ResponseResult();
        UserResponsePayment user;

        try{
            user = this.apiUser.getUserForPayment(payment.getUserId());
        } catch (FeignException e) {
        throw  new RuntimeException("Failed to retrieve user: " + id);
        }

        result.setUserResponsePayment(user);
        result.setAmount(payment.getAmount());
        result.setBalance(payment.getBalance());
        result.setPayMethod(payment.getPayMethod());
        result.setPayDate(payment.getPayDate());
        result.setPayStatus(payment.getPayStatus());

        return result;
    }
    public ResponseDTO create(CreatePaymentDto createPaymentDto){
        try {
            Payment payment = mapper.map(createPaymentDto, Payment.class);
            UserResponsePayment user;
            double balance = 0.0;
            try {
                user = this.apiUser.getUserForPayment(createPaymentDto.getUserId());
            } catch (FeignException e) {
                throw new RuntimeException("Failed to retrieve user for payment ID: " + payment.getPayId(), e);
            }
            if (createPaymentDto.getAmount() > user.getTotalPayment())
                return new ResponseDTO("Amount payment must be less than or equal to total payment in user", Status.FAILED.value(), 500);

            balance = user.getTotalPayment() - createPaymentDto.getAmount();
            if (balance > 0) {
                payment.setPayStatus(PayStatus.UNPAID);
                payment.setPayMethod(PayMethod.INSTALLMENT);
            }else {
                payment.setPayStatus(PayStatus.PAID);
                payment.setPayMethod(PayMethod.FULL);
            }
            payment.setBalance(balance);
            payment = paymentRepository.save(payment);
            return new ResponseDTO("Save OK", payment);
        } catch (Exception e) {
            return new ResponseDTO("Failed to create payment", Status.FAILED.value(), 500);
        }
    }
    public ResponseDTO update(UpdatePaymentDto updatePaymentDto){
        try {
            this.checkPayment(updatePaymentDto.getPayId());
            Payment payment = mapper.map(updatePaymentDto, Payment.class);
            UserResponsePayment user;
            double balance =0.0;
            try {
                user = this.apiUser.getUserForPayment(updatePaymentDto.getUserId());
            } catch (FeignException e) {
                throw new RuntimeException("Failed to retrieve user for payment ID: " + payment.getPayId(), e);
            }
            if (updatePaymentDto.getAmount() > user.getTotalPayment()) {
                return new ResponseDTO("Amount payment must be less than or equal to total payment in user", Status.FAILED.value(), 500);
            }
            balance = user.getTotalPayment() - (payment.getAmount() + updatePaymentDto.getAmount());

            if (balance > 0) {
                payment.setPayStatus(PayStatus.UNPAID);
                payment.setPayMethod(PayMethod.INSTALLMENT);
            } else {
                payment.setPayStatus(PayStatus.PAID);
                payment.setPayMethod(PayMethod.FULL);
            }
            payment.setBalance(balance);
            payment = paymentRepository.save(payment);
            return new ResponseDTO("Update Successfully", payment);
        } catch (ExceptionNotFound e) {
            return new ResponseDTO(e.getMessage(), Status.FAILED.value(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseDTO("Failed to update payment", Status.FAILED.value(), 500);
        }
    }
    private Payment checkPayment(Integer id){
        Optional<Payment> payId = this.paymentRepository.findByPayId(id);
        if (payId.isPresent())
            return payId.get();
        throw new ExceptionNotFound("Payment Not Found", 404);
    }

}
