package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Dao.FlickrDao;
import com.cityGuideTL.backend.Entities.City;
import com.cityGuideTL.backend.Entities.PhotosList;
import org.springframework.stereotype.Service;

@Service
public class FlickrService {
    private City city;
    private PhotosList photosList;
    private FlickrDao flickrDao = new FlickrDao();

    public City getMostVisitedCities(String city){

        this.city = flickrDao.getCity(city);

        photosList = flickrDao.getCityPhotos(this.city.getPlace_id());



        return this.city;
    }
}

