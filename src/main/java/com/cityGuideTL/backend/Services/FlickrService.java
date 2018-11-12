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

    public City getMostVisitedPhotosOfCity(String city){

        //Get city from Flickr
        this.city = flickrDao.getCity(city);

        //Get the photos of city from Flickr
        photosList = flickrDao.getCityPhotos(this.city.getPlace_id());

        //get the top five photos of photoslist and set them to the city
        this.city.setTopFivePhotos(ClusterService.getTopFivePhotos(photosList));

        return this.city;
    }
}

