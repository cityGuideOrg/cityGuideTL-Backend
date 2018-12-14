package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Entities.Photo;
import com.cityGuideTL.backend.Entities.Point;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class PointsSortingServiceTest {

    @Test
    public void sortByDistance() {
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo(23.736090+"",37.975526+""));
        photos.add(new Photo(23.726388+"",37.971666+""));
        photos.add(new Photo(23.723452+"",37.975641+""));
        photos.add(new Photo(23.730028+"",37.991703+""));
        Photo serresPhoto= new Photo(23.549168+"",41.089627+"");
        photos.add(serresPhoto);


        Point point = new Point(41.087137,23.547022);

        PointsSortingService.sortByDistance(photos,point.getLongitude().doubleValue(),point.getLatitude().doubleValue());

        assertTrue(photos.get(0).hashCode() == serresPhoto.hashCode());

    }
}