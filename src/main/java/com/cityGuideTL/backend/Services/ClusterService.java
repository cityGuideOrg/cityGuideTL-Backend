package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Entities.Photo;
import com.cityGuideTL.backend.Entities.PhotosList;
import org.springframework.stereotype.Service;
import pember.kmeans.geo.Cluster;
import pember.kmeans.geo.GeographicPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class ClusterService {
    private PhotosList photosList;
    private List<Cluster> clusters ;
    private ArrayList<GeographicPoint> points = new ArrayList<>();

    public ClusterService(PhotosList photosList){
        this.photosList = photosList;
        for(Photo photo : photosList.getPhotosList()){
            points.add(photo.getPoint());
        }

    }

    public List<Cluster> getClusters(int k) {
       double distance = HaversineDistanceService.distance(photosList.getPhotoFromList(0).getPoint(),photosList.getPhotoFromList(1).getPoint());
        clusters = pember.kmeans.geo.ClusterService.cluster(points,k);
        cluster();
        return clusters;
    }

    public void cluster(){
        for(Photo startPhoto : photosList.getPhotosList()){
            for (Iterator<Photo> it = photosList.getPhotosList().iterator(); it.hasNext();) {
                double distance = HaversineDistanceService.distance(startPhoto.getPoint(),it.next().getPoint());
                if (distance < 0.1d) {
                    startPhoto.increaseTotalNearPhotosByOne();
                }
            }
        }

        Collections.sort(photosList.getPhotosList(), Collections.reverseOrder());
        ArrayList<Photo> topFivePhotos = new ArrayList<>();

        topFivePhotos.add(photosList.getPhotoFromList(0));
        int i = 0;
        int y = 1;
        while(topFivePhotos.size() < 5 && y<photosList.getPhotosList().size()-1){
            double distance = HaversineDistanceService.distance(topFivePhotos.get(i).getPoint(),photosList.getPhotoFromList(y).getPoint());
            if(distance > 0.150){
                topFivePhotos.add(photosList.getPhotoFromList(y));
                i++;
            }
            y++;
        }
        System.out.println("hello world");
    }
}
