package com.example.threadpool.thoughtworks.utils;

import com.example.threadpool.thoughtworks.Compute.service.AbstractTrainService;
import com.example.threadpool.thoughtworks.Compute.service.TrainService;

import java.util.ArrayList;


public class NumberTripsWithMaxStops extends AbstractTrainService {
    private int maxStops;
    private String start;
    private String dest;

    /**
     * Create a new command.
     *
     * @param service receiver which will be called from this command.
     */
    public NumberTripsWithMaxStops(TrainService service) {
        super(service);
    }

    /**
     * Set maximum hops number.
     *
     * @param maxStops maximum number of hops.
     */
    public void setMaxStops(String start, String dest, int maxStops) {
        this.maxStops = maxStops;
        this.start = start;
        this.dest = dest;
    }

    /**
     * Number of possible routes starting from start and ending at dest with a maximum number of stops.
     *
     * @return number of possible routes.
     */
    @Override
    public Integer execute() {
        return getTrainService().numberTripsWithMaxStops(start, dest, maxStops, new ArrayList<>());
    }
}
