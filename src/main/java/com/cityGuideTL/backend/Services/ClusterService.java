package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Entities.Photo;
import com.cityGuideTL.backend.Entities.PhotosList;
import org.springframework.stereotype.Service;
import pember.kmeans.geo.Cluster;
import pember.kmeans.geo.GeographicPoint;

import java.util.ArrayList;
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
        clusters = pember.kmeans.geo.ClusterService.cluster(points,k);
        return clusters;
    }
}
