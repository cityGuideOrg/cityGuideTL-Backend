package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Dao.CityMySqlDao;
import com.cityGuideTL.backend.Entities.City;
import com.cityGuideTL.backend.Entities.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MySqlService {

    @Autowired
    private CityMySqlDao cityMySqlDao ;


    public void writeCityToDB(City city){
        Set photos = new HashSet<Photo>(city.getTopFivePhotos());

        cityMySqlDao.saveCity(city,photos);

//        return "OK";
    }
}
