package com.example.threadpool.thoughtworks.utils;

import com.example.threadpool.thoughtworks.Compute.service.AbstractTrainService;
import com.example.threadpool.thoughtworks.Compute.service.TrainService;

/**
 * 功能描述 指定路线的距离
 *
 * @author Barret
 * @date 11/22/2020
 * @return
 */
public class ShortestPathBetweenTwoTowns extends AbstractTrainService {
    private String start;
    private String dest;

    public ShortestPathBetweenTwoTowns(TrainService service) {
        super(service);
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    @Override
    public Object execute() {
        return getTrainService().shortestPathLengthBetweenTwoTowns(start, dest,0);
    }
}
