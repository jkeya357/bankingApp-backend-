package com.nzimbu_yeno.nzimbu.repository;

import com.nzimbu_yeno.nzimbu.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByEmailOrUserName(String email, String userName);

    Optional<User> findUserByEmail(String email);

}
