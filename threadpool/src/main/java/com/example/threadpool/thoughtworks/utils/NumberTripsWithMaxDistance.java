package com.example.threadpool.thoughtworks.utils;

import com.example.threadpool.thoughtworks.Compute.service.AbstractTrainService;
import com.example.threadpool.thoughtworks.Compute.service.TrainService;

import java.util.ArrayList;


public class NumberTripsWithMaxDistance extends AbstractTrainService {
    private int maxDistance;
    private String start;
    private String dest;

    /**
     * Create a new command.
     *
     * @param service receiver which will be called from this command.
     */
    public NumberTripsWithMaxDistance(TrainService service) {
        super(service);
    }

    /**
     * Set maximum hops number.
     *
     * @param maxDistance maximum number of hops.
     */
    public void setMaxDistance(String start, String dest, int maxDistance) {
        this.maxDistance = maxDistance;
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
        return getTrainService().numberTripsWithMaxDistance(start, dest, 0, maxDistance);
    }
}
