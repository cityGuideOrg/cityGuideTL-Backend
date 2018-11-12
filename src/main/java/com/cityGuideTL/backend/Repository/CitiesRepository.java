package com.cityGuideTL.backend.Repository;

import com.cityGuideTL.backend.Models.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<CityModel,Integer> {
}
