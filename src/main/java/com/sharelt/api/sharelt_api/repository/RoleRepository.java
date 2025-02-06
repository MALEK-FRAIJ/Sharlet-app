package com.sharelt.api.sharelt_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharelt.api.sharelt_api.entity.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    
}
