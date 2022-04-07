package com.project.backend.repositories;

import com.project.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where username = :userName",nativeQuery = true)
    Optional<User> findByUserName(@Param("userName") String userName);
}
