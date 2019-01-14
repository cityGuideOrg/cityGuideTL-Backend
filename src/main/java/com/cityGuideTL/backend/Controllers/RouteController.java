package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Models.BestRouteModel;
import com.cityGuideTL.backend.Entities.Cost;
import com.cityGuideTL.backend.Entities.Point;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
//todo: write a follow up for visit recursion
@RestController
@RequestMapping(path="/route")
public class RouteController {
    //1st:parameter from is the starting point of the route, which is the position of the user
    //2nd:parameter where is a list of all points that the user has to pass from
    //3rd:parameter is necessary for the recursiveness of the function
    public Cost Visit(Point from, List<Point> where, Cost cost) {
       if(cost == null) {
            try {
                for(Point point : where) {
                    if(point.equals(from)) {
                            throw new Exception();
                        }
                    }
            }catch (Exception e) {
                return new Cost(0);
            }
        }
        List<Cost> costs = new LinkedList<Cost>(); //linked list of all costs
        //first run-through
        if(cost == null) {
            cost = new Cost();
            cost.pointsTaken = new LinkedList<Point>(); //points visited
            cost.pointsTaken.add(from); //you add the starting point so it will be not visited
            cost.cost = 0; //no cost for starting point
        } else {
            List<Point> pointsTaken = cost.pointsTaken.subList(0, cost.pointsTaken.size());
            pointsTaken.add(from);
            double distanceCost = cost.cost;

            cost = new Cost(); // we create a new instance and copy data from other
            // just not to touch data of other runs
            cost.cost = distanceCost;
            cost.pointsTaken = pointsTaken;

            // everyplace visited already
            if(cost.pointsTaken.size() == where.size() + 1) {
                return cost; // return the cost
            }
         //   cost.cost += 5;
        }

        for(Point point : where)  {
            int taken = 0;
            //if we have visited or not
            for(Point visitedPlaces : cost.pointsTaken) {
                if(visitedPlaces.getLatitude().equals(point.getLatitude()) && visitedPlaces.getLongitude().equals(point.getLongitude())) {
                    taken = 1;
                    break;
                }

            }
            //if we have visited already
            if(taken == 1)
                continue;

            cost.cost += from.distance(point);

            Cost newCost = Visit(point, where, cost);

            if(newCost != null && newCost.pointsTaken.size() == where.size() + 1) {

                ((LinkedList<Cost>) costs).addLast(newCost);
            }

        }



        double minCost = -1;
        Cost minValue = null;

        // costs array contains in the first recursuin  if A starts with 8 different points
        // and we find minimum cost among them
        for(Cost all_cost : costs) {
            if( minCost == -1 || all_cost.cost < minCost ) {
                minValue = all_cost;
                minCost = all_cost.cost;
            }
        }
        return minValue;
    }
    @PostMapping()
    public @ResponseBody
    Cost findRoute(@RequestBody BestRouteModel body) {
        return Visit(body.startingPoint, body.placesNeedsToBeVisited, null);
    }
}
