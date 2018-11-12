package com.cityGuideTL.backend.Repository;

import com.cityGuideTL.backend.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
