package com.cityGuideTL.backend.Dao;

import com.cityGuideTL.backend.Entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class FlickrDao {
    private String apiKey = "e6ca2e5d10eb5160e389625f9bfa5439";
    PhotosList photosList = new PhotosList();
    public City getCity(String query){
        //HTTP request string
        final String url = "https://api.flickr.com/services/rest/?method=flickr.places.find&api_key="+apiKey+"&query="+query+"&format=json";

        //Get the json as string and format it to a valid form cutting the first 24 characters
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(url, String.class);
        jsonString = jsonString.substring(24);

        //Mapping the json string to Array List of cities
        CitiesList citiesList ;
        ObjectMapper mapper = new ObjectMapper();
        try {
            citiesList = mapper.readValue(jsonString, CitiesList.class);
        }
        catch (Exception e){
            System.out.println(e.toString());
            citiesList = new CitiesList();
        }

        return citiesList.getCityFromList(0);
    }
    public PhotosList getCityPhotos(String cityID){
        photosList.clear();
        int currentPage =1;
        int totalPages = 5;
        long minUploadDate = System.currentTimeMillis()/1000L-18748800L;

        String jsonString="";
        while(currentPage<=10 && currentPage<totalPages){
            //HTTP request string
            final String url ="https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+apiKey+"&min_upload_date="+minUploadDate+"&place_id="+cityID+"&extras=geo&format=json&nojsoncallback=1&per_page=500&page="+currentPage;

            //Get the json as string and format it to a valid form cutting the first 24 characters
            RestTemplate restTemplate = new RestTemplate();
            jsonString = restTemplate.getForObject(url, String.class);
            jsonString = jsonString.substring(10);

            //Mapping the json string to Array List of cities
            ObjectMapper mapper = new ObjectMapper();

            try {
                PhotosList tempPhotosList;
                tempPhotosList = mapper.readValue(jsonString, PhotosList.class);

                //Converting the String long,lat to Points
                for(Photo photo : tempPhotosList.getPhotosList()){
                    photo.setPoint(new Point(photo.getLatitude(),photo.getLongitude()));
                }

                photosList.setPhotosList(tempPhotosList.getPhotosList());
                currentPage = Integer.parseInt(tempPhotosList.getPage());
                currentPage++;
                totalPages = Integer.parseInt(tempPhotosList.getPages());
            }
            catch (Exception e){
                System.out.println(e.toString());
                photosList = new PhotosList();
            }
        }
        return photosList;
    }
}
