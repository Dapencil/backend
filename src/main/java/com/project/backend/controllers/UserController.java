package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Airport;
import com.project.backend.models.ResponseModel.UserResponse;
import com.project.backend.models.User;
import com.project.backend.sec.CustomUserDetails;
import com.project.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public List<UserResponse> getUser() {
        List<User> data = userService.getAll();
        List<UserResponse> res = data.stream().map(UserResponse::new).collect(Collectors.toList());
        return res;
    }

    @GetMapping("/{id}")
    @PreAuthorize("@secService.isMyPage(authentication,#id) or hasRole('MANAGER')")
    @ResponseBody
    public ResponseEntity getUserById(@PathVariable Integer id){
        try{
            User item = userService.findById(id);
            return ResponseEntity.ok(new UserResponse(item));
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity getProfile(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            User item = userService.findById(details.getId());
            return ResponseEntity.ok(new UserResponse(item));
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody User user){
        try{
            User item = userService.add(user);
            return ResponseEntity.ok(new UserResponse(item));
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable Integer id){
        try{
            User item = userService.update(user,id);
            return ResponseEntity.ok(new UserResponse(item));
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteUser(@PathVariable Integer id){
        try{
            User item = userService.delete(id);
            return ResponseEntity.ok(new UserResponse(item));
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }
}
