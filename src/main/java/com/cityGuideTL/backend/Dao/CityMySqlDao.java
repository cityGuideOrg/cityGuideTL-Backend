package com.cityGuideTL.backend.Dao;

import com.cityGuideTL.backend.Entities.City;
import com.cityGuideTL.backend.Entities.Photo;
import com.cityGuideTL.backend.Models.CityModel;
import com.cityGuideTL.backend.Models.PhotoModel;
import com.cityGuideTL.backend.Repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//TODO:stop using mysql namespaces, and mapping entities to models here
//use the CityService class for such operations.
@Repository("CitiesRepository")
public class CityMySqlDao {
    @Autowired
    private CitiesRepository citiesRepository;

    public void saveCity(City city, Set<Photo> photos){
        CityModel modelCityModel = entityToModel(city,photos);
        citiesRepository.save(modelCityModel);
    }

    public void deleteCity(Integer id){
        CityModel cityModel;
        try {
            cityModel = citiesRepository.findById(id).orElseThrow(IOException::new);
        } catch (IOException e) {

            System.out.println("cityModel not found");
            cityModel =null;
        }
        citiesRepository.delete(cityModel);
    }

    public City getCityById(Integer id){
        CityModel cityModel;
        try {
            cityModel = citiesRepository.findById(id).orElseThrow(IOException::new);

        } catch (IOException e) {
            System.out.println("CityModel not found");
            cityModel = null;
        }
        return modelToEntity(cityModel);
    }

    public List<CityModel> getAllCities(){
        return citiesRepository.findAll();
    }


    private CityModel entityToModel(City city, Set<Photo> photos){
        CityModel modelCityModel = new CityModel();

        modelCityModel.setContent(city.getContent());
        modelCityModel.setWoe_name(city.getWoe_name());
        modelCityModel.setPlaceId(city.getPlace_id());
        modelCityModel.setLongitude(city.getLongitude());
        modelCityModel.setLatitude(city.getLatitude());
        Set<PhotoModel> photoModels = new HashSet<>();
        for(Photo photo : photos){
            PhotoModel photoModel = new PhotoModel(photo.getPhotoID(),photo.getLongitude(), photo.getLatitude(), photo.getTotalNearPhotos(),modelCityModel);

            photoModels.add(photoModel);
        }
        modelCityModel.setPhotoModels(photoModels);


        return modelCityModel;
    }

    private City modelToEntity(CityModel modelCityModel){
        City city = new City();
        city.setContent(modelCityModel.getContent());
        city.setPlace_id(modelCityModel.getPlaceId());
        city.setLatitude(modelCityModel.getLatitude());
        city.setLongitude(modelCityModel.getLongitude());
        city.setWoe_name(modelCityModel.getWoe_name());
        Set<Photo> photoSet = new HashSet<>();
        for(PhotoModel photoModel : modelCityModel.getPhotoModels()){
            Photo photo = new Photo(photoModel.getPhotoID(), photoModel.getLongitude(), photoModel.getLatitude(), photoModel.getTotalNearPhotos());
            photoSet.add(photo);
        }
        city.setTopFivePhotos(new ArrayList<>(photoSet));
        city.sortPointsArrayList();


        return city;
    }
}
