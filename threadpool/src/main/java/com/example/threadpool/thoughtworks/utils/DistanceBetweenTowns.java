package com.example.threadpool.thoughtworks.utils;

import com.example.threadpool.thoughtworks.Compute.service.AbstractTrainService;
import com.example.threadpool.thoughtworks.Compute.service.TrainService;

import java.util.ArrayList;

/**
 * 功能描述 指定路线的距离
 *
 * @author Barret
 * @date 11/22/2020
 * @return
 */
public class DistanceBetweenTowns extends AbstractTrainService {
    private String[] townNames;

    /**
     * Create a new command.
     *
     * @param service receiver which will be called from this command.
     */
    public DistanceBetweenTowns(TrainService service) {
        super(service);
    }

    public void setTownNames(String[] townNames) {
        this.townNames = townNames;
    }

    @Override
    public Object execute() {
        int result = getTrainService().distanceBetweenTowns(townNames);
        if (result == -1) {
            return "no such route";
        }
        return result;
    }
}
