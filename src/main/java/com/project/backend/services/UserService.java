package com.project.backend.services;

import com.project.backend.models.User;
import com.project.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getUser() {
        return repository.findAll();
    }

//    public User getUserById(Integer id) {
//        return repository.findById(id).get();
//    }
}
