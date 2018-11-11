package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Entities.Photo;
import com.cityGuideTL.backend.Entities.PhotosList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

@Service
public class ClusterService {

    public static ArrayList<Photo> getTopFivePhotos(PhotosList photosList){
        // Count the number of close photos
        for(Photo startPhoto : photosList.getPhotosList()){
            for (Iterator<Photo> it = photosList.getPhotosList().iterator(); it.hasNext();) {
                double distance = HaversineDistanceService.distance(startPhoto.getPoint(),it.next().getPoint());
                if (distance < 0.1d) {
                    startPhoto.increaseTotalNearPhotosByOne();
                }
            }
        }

        //Sort the list base on number of close photos
        Collections.sort(photosList.getPhotosList(), Collections.reverseOrder());

        ArrayList<Photo> topFivePhotos = new ArrayList<>();

        topFivePhotos.add(photosList.getPhotoFromList(0));
        int i = 0;
        int y = 1;

        //Exclude the photos that are close to an already topPhoto
        while(topFivePhotos.size() < 5 && y<photosList.getPhotosList().size()-1){
            double distance = HaversineDistanceService.distance(topFivePhotos.get(i).getPoint(),photosList.getPhotoFromList(y).getPoint());
            if(distance > 0.5){
                if (isFarFromOtherPhotos(topFivePhotos,photosList.getPhotoFromList(y))){
                    topFivePhotos.add(photosList.getPhotoFromList(y));
                    i++;
                }
            }
            y++;
        }
        return topFivePhotos;
    }
    private static boolean isFarFromOtherPhotos(ArrayList<Photo> otherPhotos,Photo photo){
        int count = 0;
        for(Photo otherPhoto : otherPhotos){
            double distance = HaversineDistanceService.distance(otherPhoto.getPoint(),photo.getPoint());
            if(distance > 0.5){
                count++;
            }
        }
        if (count < otherPhotos.size()){
            return false;
        }
        return true;
    }
}
