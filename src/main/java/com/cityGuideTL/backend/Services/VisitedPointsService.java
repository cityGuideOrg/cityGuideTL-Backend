package com.cityGuideTL.backend.Services;




import com.cityGuideTL.backend.Models.VisitedPointsModel;
import com.cityGuideTL.backend.Repository.VisitedPointsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
//TODO:map entities to models here
@Service
public class VisitedPointsService {
    @Autowired
    private VisitedPointsRepository VisitedPointsRepository;


    public VisitedPointsModel addVisitedPoint(VisitedPointsModel visitedPoint){
        return VisitedPointsRepository.save(visitedPoint);
    }


    public void deleteVisitedPoint(Integer id) {
        VisitedPointsModel visitedPoint;
        try {
            visitedPoint = VisitedPointsRepository.findById(id).orElseThrow(IOException::new);
        } catch (IOException e) {

            System.out.println("visited point not found");
            visitedPoint = null;
        }
        VisitedPointsRepository.delete(visitedPoint);
    }

    //token
    //identifyontoken
    //place by userid
    public List<VisitedPointsModel> getVisitedPointByUserId(Integer id) {
        List<VisitedPointsModel> visitedPoints;
        try {
            VisitedPointsModel sample = new VisitedPointsModel();
sample.setUser_id(id);
Example<VisitedPointsModel> example = Example.of(sample);         
            visitedPoints = VisitedPointsRepository.findAll(example);
        } catch(Exception e) {
            System.out.println(e);
            visitedPoints = null;
        }
        return visitedPoints;
        }


    public VisitedPointsModel getVisitedPoint(Integer id) {
        VisitedPointsModel visitedPoint;
        try {
            visitedPoint = VisitedPointsRepository.findById(id).orElseThrow(IOException::new);
        } catch (IOException e) {
            System.out.println("visited point not found");
            visitedPoint = null;
        }
        return visitedPoint;
    }


    public void updateVisitedPoint(Integer id, VisitedPointsModel visitedPoint) {
        try {
            VisitedPointsRepository.findById(id).orElseThrow(IOException::new);
            VisitedPointsRepository.save(visitedPoint);
        } catch(IOException e) {
            System.out.println("City of " + id.toString() + " not found");
        }
        
        
    }

    public List<VisitedPointsModel> getAll(){
        List<VisitedPointsModel> visitedPoints;
        try {
            visitedPoints = VisitedPointsRepository.findAll();
        } catch(Exception e) {
            System.out.println(e);
            visitedPoints = null;
        }
        return visitedPoints;
    }




}
