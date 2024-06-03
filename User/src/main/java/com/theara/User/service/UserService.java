package com.theara.User.service;

import com.theara.User.Dto.*;
import com.theara.User.FeignClient.ApiCourse;
import com.theara.User.FeignClient.ApiPromotion;
import com.theara.User.constant.ResponseDTO;
import com.theara.User.constant.Status;
import com.theara.User.entities.User;
import com.theara.User.exception.ExceptionNotFound;
import com.theara.User.repository.UserRepository;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ApiPromotion apiPromotion;
    @Autowired
    private ApiCourse apiCourse;
    public ResponseDTO getAll(){
        try{
        List<User> users = this.userRepository.findAll();
        return new ResponseDTO("Users is Found", users);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch user", Status.FAILED.value(), 500);
        }
    }
    public UserResponsePayment getUserForPayment(Integer id){
        User user = this.checkUser(id);

        if (user.getCourseId().isEmpty()) {
            throw new RuntimeException("Failed to retrieve course details for user id: " + id);
        }
        double totalCoursePrice = 0.0;
        for (Integer courseId : user.getCourseId()) {
            CourseDto course = this.apiCourse.getCourseById(courseId);
            totalCoursePrice += course.getCoursePrice();
        }
        PromotionDto promotion;
        double promotionDis = 0.0;
        try {
            promotion = this.apiPromotion.getPromotionById(user.getPromId());
            promotionDis += promotion.getDiscountAmount();
        } catch (FeignException e) {
            throw  new RuntimeException("Failed to retrieve promotion for user id: " + id);
        }
        double total = totalCoursePrice - promotionDis;

        UserResponsePayment response = new UserResponsePayment();
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setRegistrationDate(user.getRegistrationDate());
        response.setAmountCourse(totalCoursePrice);
        response.setPromotionDiscount(promotionDis);
        response.setTotalPayment(total);
        return response;

    }
    public ResponseDTO getUserById(Integer id){
        User user = this.checkUser(id);

        PromotionDto promotion;
        try {
            promotion = this.apiPromotion.getPromotionById(user.getPromId());
        } catch (FeignException e) {
            return new ResponseDTO("Failed to retrieve promotion for user id: " + id, Status.FAILED.value(), 500);
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserType(user.getUserType());
        userResponse.setDelete(user.isDelete());

        // Constructing the courseId response
        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Integer courseId : user.getCourseId()) {
            CourseDto courseDto = new CourseDto();
            // Assuming you have a method to get course details by courseId
            CourseDto course = this.apiCourse.getCourseById(courseId);
            courseDto.setCourseName(course.getCourseName());
            courseDto.setCoursePrice(course.getCoursePrice());
            courseDtoList.add(courseDto);
        }
        if (courseDtoList.size() != user.getCourseId().size()) {
            return new ResponseDTO("Failed to retrieve course details for user id: " + id, Status.FAILED.value(), 500);
        }

        userResponse.setCourseDtoList(courseDtoList);
        userResponse.setRegistrationDate(user.getRegistrationDate());
        userResponse.setPromotionDto(promotion);
        return new ResponseDTO("User Found",Status.SUCCESS.value(), 200, userResponse);
    }
    public ResponseDTO getUserByIsDelete(){
        try{
            Iterable<User> users = this.userRepository.findUserIsDeleteFalse();
            return new ResponseDTO("User Found", users);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch user", Status.FAILED.value(), 500);
        }
    }
    public ResponseDTO create(CreateUserDto createUserDto){
        try {
            User user = mapper.map(createUserDto, User.class);
            user = userRepository.save(user);
            return new ResponseDTO("Save OK", user);
        } catch (Exception e) {
            return new ResponseDTO("Failed to create user", Status.FAILED.value(), 500);
        }
    }
    public ResponseDTO update(UpdateUserDto updateUserDto){
        try{
            this.checkUser(updateUserDto.getUserId());
            User user = mapper.map(updateUserDto,User.class);
            user = this.userRepository.save(user);
            return new ResponseDTO("Update Successfully",user);
        }catch (ExceptionNotFound e) {
            return new ResponseDTO(e.getMessage(), Status.FAILED.value(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseDTO("Failed to update user", Status.FAILED.value(), 500);
        }
    }
    public ResponseDTO delete(Integer id){
        User user = this.checkUser(id);
        user.setDelete(true);
        this.userRepository.save(user);
        return new ResponseDTO("delete OK");
    }

    private User checkUser(Integer id){
        Optional<User> userId = this.userRepository.findByUserId(id);
        if (userId.isPresent())
            return userId.get();
        throw new ExceptionNotFound("User Not Found", 404);
    }

}
