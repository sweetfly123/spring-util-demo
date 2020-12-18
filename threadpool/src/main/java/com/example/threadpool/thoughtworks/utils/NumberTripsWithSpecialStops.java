package com.example.threadpool.thoughtworks.utils;

import com.example.threadpool.thoughtworks.Compute.service.AbstractTrainService;
import com.example.threadpool.thoughtworks.Compute.service.TrainService;

import java.util.ArrayList;


public class NumberTripsWithSpecialStops extends AbstractTrainService {
    private int specialStops;
    private String start;
    private String dest;

    /**
     * Create a new command.
     *
     * @param service receiver which will be called from this command.
     */
    public NumberTripsWithSpecialStops(TrainService service) {
        super(service);
    }

    /**
     * Set maximum hops number.
     *
     * @param specialStops maximum number of hops.
     */
    public void setSpecialStops(String start, String dest, int specialStops) {
        this.specialStops = specialStops;
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
        return getTrainService().numberTripsWithSpecialStops(start, dest, specialStops);
    }
}
