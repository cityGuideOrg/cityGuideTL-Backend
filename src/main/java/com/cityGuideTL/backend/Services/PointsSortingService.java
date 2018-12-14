package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Entities.Photo;
import com.cityGuideTL.backend.Entities.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class PointsSortingService {
    public static void sortByDistance(ArrayList<Photo> arrayList,  Double longitude, Double latitude){
        HashMap<Photo,Double> distanceMap = new HashMap<>();
        Point point = new Point(longitude,latitude);

        for(Photo photo : arrayList){

            Double distance = HaversineDistanceService.distance(photo.getPoint(),point);
            distanceMap.put(photo,distance);
        }

        arrayList.sort(new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                if(distanceMap.get(o1) > distanceMap.get(o2))
                    return 1;
                else if(distanceMap.get(o1) < distanceMap.get(o2))
                    return -1;
                else
                    return 0;

            }

        });
    }

    public static void sortByPopularity(ArrayList<Photo> arrayList){
        Collections.sort(arrayList);
        Collections.reverse(arrayList);
    }
}
