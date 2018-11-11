package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Entities.City;
import com.cityGuideTL.backend.Entities.Photo;
import com.cityGuideTL.backend.Entities.Point;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class FlickrServiceTest {
    @Test
    public void getParthenonForAthens(){
        FlickrService flickrService = new FlickrService();
        City city;
        city = flickrService.getMostVisitedPhotosOfCity("Athens");
        int count = 0;
        for(Photo photo : city.getTopFivePhotos()){
            if (HaversineDistanceService.distance(photo.getPoint(),new Point(37.971744, 23.726384)) < 0.1)
                count++;
        }
        assertTrue(count>0);
    }
}