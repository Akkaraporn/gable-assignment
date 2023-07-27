package com.artassingment.akkaraporn.user;

import com.artassingment.akkaraporn.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<Object> getAllUsers(@RequestParam int offset,int pageSize) {
        int thisOffset = offset <= 0 ? offset: 0;
        int thisPageSize = pageSize <= 0 ? pageSize: 25;
        Page<UserEntity> users = userService.getAllUsers( thisOffset, thisPageSize);
        return ResponseHandler.generateResponse("Successfully", HttpStatus.OK, users);
    }
    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        UserQueryRepo user = userService.getUserById(id,"Customer");
        return ResponseHandler.generateResponse("Successfully", HttpStatus.OK, user);
    }
    @GetMapping("/getStaff/{id}")
    public ResponseEntity<Object> getStaffById(@PathVariable Long id) {
        UserQueryRepo user = userService.getUserById(id,"Staff");
        return ResponseHandler.generateResponse("Successfully", HttpStatus.OK, user);
    }
    @GetMapping("/getManager/{id}")
    public ResponseEntity<Object> getManagerById(@PathVariable Long id) {
        UserQueryRepo user = userService.getUserById(id,"Manager");
        return ResponseHandler.generateResponse("Successfully", HttpStatus.OK, user);
    }


    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseHandler.generateResponse("Created successfully", HttpStatus.CREATED);
    }

}
