package com.cityGuideTL.backend.Repository;

import com.cityGuideTL.backend.Models.PhotoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<PhotoModel,Integer> {
}
