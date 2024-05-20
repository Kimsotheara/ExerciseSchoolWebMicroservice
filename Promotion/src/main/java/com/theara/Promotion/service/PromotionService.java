package com.theara.Promotion.service;

import com.theara.Promotion.Dto.CreatePromotionDto;
import com.theara.Promotion.Dto.UpdatePromotionDto;
import com.theara.Promotion.constant.ResponseDTO;
import com.theara.Promotion.constant.Status;
import com.theara.Promotion.entities.Promotion;
import com.theara.Promotion.exception.ExceptionNotFound;
import com.theara.Promotion.repository.PromotionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ModelMapper mapper;
    public ResponseDTO getAll(){
        try{
        List<Promotion> promotions = this.promotionRepository.findAll();
        return new ResponseDTO("Promotion is Found", promotions);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch promotions", Status.FAILED.value(), 500);
        }
    }
    public Promotion getPromotionById(Integer id){
        return this.checkPromotion(id);
    }
    public ResponseDTO getPromotionByIsDelete(){
        try{
            Iterable<Promotion> promotions = this.promotionRepository.findPromotionIsDeleteFalse();
            return new ResponseDTO("Promotion Found", promotions);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch promotions", Status.FAILED.value(), 500);
        }
    }
    public ResponseDTO create(CreatePromotionDto createPromotionDto){
        try {
            Promotion promotion = mapper.map(createPromotionDto, Promotion.class);
            promotion = promotionRepository.save(promotion);
            return new ResponseDTO("Save OK", promotion);
        } catch (Exception e) {
            return new ResponseDTO("Failed to create promotion", Status.FAILED.value(), 500);
        }
    }
    public ResponseDTO update(UpdatePromotionDto updatePromotionDto){
        try{
            this.checkPromotion(updatePromotionDto.getPromId());
            Promotion promotion = mapper.map(updatePromotionDto,Promotion.class);
            promotion = this.promotionRepository.save(promotion);
            return new ResponseDTO("Update Successfully",promotion);
        }catch (ExceptionNotFound e) {
            return new ResponseDTO(e.getMessage(), Status.FAILED.value(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseDTO("Failed to update promotion", Status.FAILED.value(), 500);
        }
    }
    public ResponseDTO delete(Integer id){
        Promotion promotion = this.checkPromotion(id);
        promotion.setDelete(true);
        this.promotionRepository.save(promotion);
        return new ResponseDTO("delete OK");
    }

    private Promotion checkPromotion(Integer id){
        Optional<Promotion> promotionId = this.promotionRepository.findByPromId(id);
        if (promotionId.isPresent())
            return promotionId.get();
        throw new ExceptionNotFound("Promotion Not Found", 404);
    }

}
