package com.sharelt.api.sharelt_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sharelt.api.sharelt_api.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query(value = "SELECT u FROM User u WHERE u.name LIKE %:keyword% OR u.username LIKE %:keyword%")
    Page<User> findByNameOrUsername(@Param("keyword") String keyword, Pageable pageable);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
