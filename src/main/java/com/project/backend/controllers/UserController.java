package com.project.backend.controllers;

import com.project.backend.models.Airport;
import com.project.backend.models.User;
import com.project.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("getAll")
    public List<User> getUser() {
        return userService.getUser();
    }
//    @GetMapping("get/{id}")
//    public User getUserById(@PathVariable Integer id){
//        return userService.getUserById(id);
//    }
//    @PostMapping("add")
//    public boolean addUser(@RequestBody User user){
//        return ;
//    }
//    @PutMapping("update/{id}")
//    public boolean updateUser(@RequestBody User user, @PathVariable Integer id){
//        return ;
//    }
//    @DeleteMapping("delete/{id}")
//    public boolean deleteUser(@PathVariable Integer id){
//        return ;
//    }
}
