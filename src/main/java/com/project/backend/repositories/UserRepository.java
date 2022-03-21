package com.project.backend.repositories;

import com.project.backend.keys.UserPK;
import com.project.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserPK> {
}
