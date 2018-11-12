package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Dao.CitiesDao;
import com.cityGuideTL.backend.Entities.City;
import com.cityGuideTL.backend.Models.Cities;
import com.cityGuideTL.backend.Services.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.places.Place;
import com.flickr4java.flickr.places.PlacesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/flickr")
public class FlickrController implements  CommandLineRunner{

    @Autowired
    private FlickrService flickrService;

    @Autowired
    private CitiesDao citiesDao;


    @RequestMapping(value = "/search/{city}", method = RequestMethod.GET)
    public List<Place> search(@PathVariable("city") String city) {

        Flickr f = new Flickr("e6ca2e5d10eb5160e389625f9bfa5439", "", new REST());
try {
    PlacesList<Place> placesList = f.getPlacesInterface().find(city);
    Place place = placesList.get(0);

    SearchParameters params = new SearchParameters();

    params.setPlaceId(place.getPlaceId());
    params.setHasGeo(true);
    Set<String> extras = new HashSet<String>();
    extras.add("geo");
    extras.add("tags");
    params.setExtras(extras);

    PhotoList<Photo> photos = f.getPhotosInterface().search(params, 100, 1);

    for(int i = 0; i < photos.size(); ++i) {
        Photo photo = photos.get(i);

        photo.getGeoData().getLatitude();
        photo.getGeoData().getLatitude();
    }
    PlacesList<Place> interesting = f.getPlacesInterface().getTopPlacesList(7, null, place.getPlaceId(), null);

    List<Place> places = new LinkedList<Place>();

    for (int i = 0; i < Math.min(5, interesting.size()); ++i) {
        ((LinkedList<Place>) places).addLast(interesting.get(i));
    }

    return places;

} catch (Exception e) {
 System.out.println(e.getMessage());
}
return null;


    }

    @RequestMapping(value = "/{city}", method = RequestMethod.GET)
    public  City getMostVisitedPlaces(@PathVariable("city") String city){
        return flickrService.getMostVisitedPhotosOfCity(city);
    }


    @Override
    public void run(String... args) throws Exception {
        Cities citiesHib = getCities();
        citiesDao.createCities(citiesHib);
    }

    private Cities getCities() {
        Cities cities = new Cities();
        cities.setPlace_id("dasdasa");
        cities.setLatitude("31214");
        cities.setLongitude("14134");

        return cities;
    }
}
