package com.codefellowsec.repositories;

import com.codefellowsec.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}