package com.cityGuideTL.backend.Repository;

import com.cityGuideTL.backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

}
