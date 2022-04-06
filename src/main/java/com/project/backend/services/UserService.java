package com.project.backend.services;

import com.project.backend.models.User;
import com.project.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

//    public User findById(Integer id){
//        User item = repository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
//        return item;
//    }

//    public User getUserById(Integer id) {
//        return repository.findById(id).get();
//    }
}
