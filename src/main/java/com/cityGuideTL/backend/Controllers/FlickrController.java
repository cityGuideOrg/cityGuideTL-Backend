package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Entities.City;
import com.cityGuideTL.backend.Repository.CitiesRepository;
import com.cityGuideTL.backend.Services.FlickrService;
import com.cityGuideTL.backend.Services.MySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flickr")
public class FlickrController {
    @Autowired
    private CitiesRepository citiesRepository;

    @Autowired
    private FlickrService flickrService;
    @Autowired
    private MySqlService mySqlService;
    @RequestMapping(value = "/{city}", method = RequestMethod.GET)
    public void getMostVisitedPlaces(@PathVariable("city") String city) {
    }


        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        public City getCity (@PathVariable("id") Integer id){

            return mySqlService.getCityById(id);
        }
        @RequestMapping(value = "/write/{city}", method = RequestMethod.POST)
        public void writeCityToDB (@PathVariable("city") String city){
            City filledCity = flickrService.getMostVisitedPhotosOfCity(city);
            mySqlService.writeCityToDB(filledCity);
        }

}


