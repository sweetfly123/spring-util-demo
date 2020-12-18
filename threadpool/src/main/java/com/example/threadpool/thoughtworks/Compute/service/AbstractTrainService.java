package com.example.threadpool.thoughtworks.Compute.service;

import com.example.threadpool.thoughtworks.Compute.Compute;

/**
 *功能描述 该抽象类实现Compute接口，作为所有计算方式的顶级父类
 * @author Barret
 * @date 11/22/2020
 * @return
 */
public abstract class AbstractTrainService implements Compute {
    private TrainService trainService;

    public AbstractTrainService(TrainService trainService){
        this.trainService = trainService;
    }

    public TrainService getTrainService() {
        return trainService;
    }
}
