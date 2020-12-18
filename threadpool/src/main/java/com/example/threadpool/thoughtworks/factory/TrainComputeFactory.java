package com.example.threadpool.thoughtworks.factory;

import com.example.threadpool.thoughtworks.Compute.Compute;

/**
 *功能描述
 * @author Barret
 * @date 11/21/2020
 * @return
 */
public interface TrainComputeFactory {

    Compute createCompute(String input);
}
