package com.cognizant.billpaymentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.billpaymentsystem.model.User;

/**
 * @author 810512
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
