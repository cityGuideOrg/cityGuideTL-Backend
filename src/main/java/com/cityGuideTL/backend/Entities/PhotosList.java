package com.cityGuideTL.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.util.ArrayList;

@JsonComponent
public class PhotosList {
    @JsonProperty("photo")
    private ArrayList<Photo> photosList;
    @JsonProperty("page")
    private String page;
    @JsonProperty("pages")
    private String pages;
    @JsonProperty("perpage")
    private String perpage;
    @JsonProperty("total")
    private String total;

    public PhotosList(ArrayList<Photo> photosList, String page, String pages, String perpage, String total) {
        this.photosList = photosList;
        this.page = page;
        this.pages = pages;
        this.perpage = perpage;
        this.total = total;
    }


    public PhotosList(String page) {
        this.page = page;
    }
    public void clear(){
        photosList.clear();
    }

    public PhotosList() {
        photosList = new ArrayList<>();
    }

    public Photo getPhotoFromList(int index){
        return photosList.get(index);
    }
    public void addPhotoToList(Photo photo){
        photosList.add(photo);
    }

    public ArrayList<Photo> getPhotosList() {
        return photosList;
    }

    public void setPhotosList(ArrayList<Photo> photosList) {
        this.photosList.addAll(photosList);
    }

    public String getPage() {
        return page;
    }

    public String getPages() {
        return pages;
    }

}
