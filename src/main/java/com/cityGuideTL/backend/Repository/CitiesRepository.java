package com.cityGuideTL.backend.Repository;

import com.cityGuideTL.backend.Models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<City,Integer> {
}
