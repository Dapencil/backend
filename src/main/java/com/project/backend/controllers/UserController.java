package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Airport;
import com.project.backend.models.User;
import com.project.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getUser() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getUserById(@PathVariable Integer id){
        try{
            User item = userService.findById(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody User user){
        try{
            User item = userService.add(user);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable Integer id){
        try{
            User item = userService.update(user,id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteUser(@PathVariable Integer id){
        try{
            User item = userService.delete(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }
}
