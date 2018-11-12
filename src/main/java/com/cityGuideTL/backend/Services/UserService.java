package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//todo: access jpaapi through this service, commit failcheck code
public class UserService {
    @Autowired
   private UserRepository userRepository;

}
