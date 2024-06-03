package com.theara.User.controller;

import com.theara.User.Dto.CreateUserDto;
import com.theara.User.Dto.UpdateUserDto;
import com.theara.User.Dto.UserResponsePayment;
import com.theara.User.constant.ResponseDTO;
import com.theara.User.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseDTO findUserByIsDelete(){
        return this.userService.getUserByIsDelete();
    }
    @GetMapping("/all")
    public ResponseDTO findAll(){
        return this.userService.getAll();
    }
    @GetMapping("/id/{id}")
    public ResponseDTO findUserById(@PathVariable Integer id){
        return this.userService.getUserById(id);
    }
    @GetMapping("/find/id/{id}")
    public UserResponsePayment getUserForPayment(@PathVariable Integer id){
        return this.userService.getUserForPayment(id);
    }
    @PostMapping("/create")
    public ResponseDTO create(@RequestBody CreateUserDto createUserDto){
        return this.userService.create(createUserDto);
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody UpdateUserDto updateUserDto){
        return this.userService.update(updateUserDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable Integer id){
        return this.userService.delete(id);
    }
}
