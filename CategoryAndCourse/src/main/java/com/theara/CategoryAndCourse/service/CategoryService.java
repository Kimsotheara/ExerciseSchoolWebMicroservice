package com.theara.CategoryAndCourse.service;

import com.theara.CategoryAndCourse.Dto.category.CreateCategoryDto;
import com.theara.CategoryAndCourse.Dto.category.UpdateCategoryDto;
import com.theara.CategoryAndCourse.constant.ResponseDTO;
import com.theara.CategoryAndCourse.constant.Status;
import com.theara.CategoryAndCourse.entities.Category;
import com.theara.CategoryAndCourse.exception.ExceptionNotFound;
import com.theara.CategoryAndCourse.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;

    public ResponseDTO getAllByIsDeleteFalse(){
        try {
            Iterable<Category> categories = categoryRepository.findAllByIsDelete();
            return new ResponseDTO("OK", categories);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch category", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO getAllCate() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return new ResponseDTO("OK", categories);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch category", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO createCate(CreateCategoryDto createCategoryDto) {
        try {
            Category category = mapper.map(createCategoryDto, Category.class);
            category = categoryRepository.save(category);
            return new ResponseDTO("Save OK", category);
        } catch (Exception e) {
            return new ResponseDTO("Failed to create category", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO updateCate(UpdateCategoryDto updateCategoryDto){
        try{
            Optional<Category> cateId = this.categoryRepository.findCateById(updateCategoryDto.getCateId());
            if (cateId.isEmpty())
                throw new ExceptionNotFound("Category Not Found", 404);
            Category cate = mapper.map(updateCategoryDto,Category.class);
            cate = this.categoryRepository.save(cate);
            return new ResponseDTO("Update Successfully",cate);
        }catch (ExceptionNotFound e) {
            return new ResponseDTO(e.getMessage(), Status.FAILED.value(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseDTO("Failed to update Category", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO delete(Integer id){
        Optional<Category> cateId = this.categoryRepository.findCateById(id);
        if (cateId.isEmpty())
            throw new ExceptionNotFound("Category Not Found", 404);
        Category category = cateId.get();
        category.setDelete(true);
        this.categoryRepository.save(category);
        return new ResponseDTO("delete OK");
    }
}
