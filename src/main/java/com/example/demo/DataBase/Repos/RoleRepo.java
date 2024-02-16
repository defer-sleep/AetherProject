package com.example.demo.DataBase.Repos;

import com.example.demo.DataBase.Entities.User.ERole;
import com.example.demo.DataBase.Entities.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
