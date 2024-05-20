package com.theara.Promotion.controller;

import com.theara.Promotion.Dto.CreatePromotionDto;
import com.theara.Promotion.Dto.UpdatePromotionDto;
import com.theara.Promotion.constant.ResponseDTO;
import com.theara.Promotion.entities.Promotion;
import com.theara.Promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public ResponseDTO findPromotionByIsDelete(){
        return this.promotionService.getPromotionByIsDelete();
    }
    @GetMapping("/all")
    public ResponseDTO findAll(){
        return this.promotionService.getAll();
    }
    @GetMapping("/id/{id}")
    public Promotion findPromotionById(@PathVariable Integer id){
        return this.promotionService.getPromotionById(id);
    }
    @PostMapping("/create")
    public ResponseDTO create(@RequestBody CreatePromotionDto createPromotionDto){
        return this.promotionService.create(createPromotionDto);
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody UpdatePromotionDto updatePromotionDto){
        return this.promotionService.update(updatePromotionDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable Integer id){
        return this.promotionService.delete(id);
    }
}
