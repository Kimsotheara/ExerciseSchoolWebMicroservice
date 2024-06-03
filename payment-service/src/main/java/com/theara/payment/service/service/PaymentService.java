package com.theara.payment.service.service;

import com.theara.payment.service.Dto.CreatePaymentDto;
import com.theara.payment.service.Dto.UpdatePaymentDto;
import com.theara.payment.service.constant.ResponseDTO;
import com.theara.payment.service.constant.Status;
import com.theara.payment.service.entities.Payment;
import com.theara.payment.service.exception.ExceptionNotFound;
import com.theara.payment.service.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper mapper;
    public ResponseDTO getAll(){
        try{
        List<Payment> payments = this.paymentRepository.findAll();
        return new ResponseDTO("Payment is Found", payments);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch payment", Status.FAILED.value(), 500);
        }
    }
    public Payment getPaymentById(Integer id){
        return this.checkPayment(id);
    }
    public ResponseDTO create(CreatePaymentDto createPaymentDto){
        try {
            Payment payment = mapper.map(createPaymentDto, Payment.class);
            payment = paymentRepository.save(payment);
            return new ResponseDTO("Save OK", payment);
        } catch (Exception e) {
            return new ResponseDTO("Failed to create payment", Status.FAILED.value(), 500);
        }
    }
    public ResponseDTO update(UpdatePaymentDto updatePaymentDto){
        try{
            this.checkPayment(updatePaymentDto.getPayId());
            Payment payment = mapper.map(updatePaymentDto,Payment.class);
            payment = this.paymentRepository.save(payment);
            return new ResponseDTO("Update Successfully",payment);
        }catch (ExceptionNotFound e) {
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
