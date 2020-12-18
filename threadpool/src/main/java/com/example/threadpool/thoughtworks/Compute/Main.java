package com.example.threadpool.thoughtworks.Compute;

import com.example.threadpool.thoughtworks.Compute.service.Impl.TrainServiceImpl;
import com.example.threadpool.thoughtworks.Compute.service.TrainService;
import com.example.threadpool.thoughtworks.utils.*;

import java.io.FileNotFoundException;

/**
 * @author Barret
 * @description
 * @date 11/22/2020
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph();
        graph.init("threadpool/src/main/resources/graph.txt");
        int[][] map = graph.getMap();
        TrainService trainService = new TrainServiceImpl(map);
//        TrainComputeFactory trainComputeFactory = new TrainServiceComputeFactory(trainService);
//        NumberTripsWithMaxStops compute = new NumberTripsWithMaxStops(trainService);
//        NumberTripsWithSpecialStops numberTripsWithSpecialStops = new NumberTripsWithSpecialStops(trainService);
//        numberTripsWithSpecialStops.setSpecialStops("A","C",4);
        DistanceBetweenTowns shortestPathBetweenTwoTowns = new DistanceBetweenTowns(trainService);
        shortestPathBetweenTwoTowns.setTownNames(new String[]{"A","E","D"});
        Object execute = shortestPathBetweenTwoTowns.execute();
        System.out.println(execute);
//        Object execute = compute.execute();
//        compute.execute();

    }
}
