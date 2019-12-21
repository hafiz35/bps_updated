/**
 * 
 */
package com.cognizant.billpaymentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.billpaymentsystem.model.Role;

/**
 * @author 810512
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRoleId(int roleId);
}
