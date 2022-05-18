package com.project.backend.services;

import com.project.backend.models.User;
import com.project.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        List<User> data = repository.findAll();
        data.forEach((user -> {user.setPassword("masked");}));
        return data;
    }

    public User findById(Integer id){
        User item = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    //only system
    public User add(User user){
        isPresent(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegisDate(LocalDate.now());
        user.setTotalMile(0);
        user.setRole("ROLE_PASSENGER");
        repository.save(user);
        return user;
    }

    //TODO only admin can access
    public User update(User newItem,Integer id){
        User item = findById(id);
        item.setDOB(newItem.getDOB());
        item.setFirstName(newItem.getFirstName());
        item.setLastName(newItem.getLastName());
        item = repository.save(item);
        return item;
    }

    //TODO only admin can accss
    public User delete(Integer id){
        User item = findById(id);
        repository.delete(item);
        return item;
    }

    private void isPresent(String username){
        if(repository.findAll().stream().anyMatch((item) -> item.getUsername().equals(username))){
            throw new IllegalArgumentException("This item has been in db.");
        }
    }

}
