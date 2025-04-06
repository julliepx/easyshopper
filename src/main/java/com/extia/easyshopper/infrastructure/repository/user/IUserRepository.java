package com.extia.easyshopper.infrastructure.repository.user;

import com.extia.easyshopper.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
