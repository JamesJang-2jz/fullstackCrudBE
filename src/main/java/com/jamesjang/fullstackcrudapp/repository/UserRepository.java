package com.jamesjang.fullstackcrudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesjang.fullstackcrudapp.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
