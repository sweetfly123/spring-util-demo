package com.example.threadpool.thoughtworks.Compute.service;

import java.util.HashMap;
import java.util.List;

/**
 * 功能描述
 *
 * @author Barret
 * @date 11/22/2020
 * @return
 */
public interface TrainService {
    /**
     * 功能描述 两个站之间最短的距离，计算出距离长度
     *
     * @return int
     * @date 11/22/2020
     */
    int shortestPathLengthBetweenTwoTowns(String start, String dest, int cost);

    /**
     * 功能描述 指定路线的长度
     *
     * @return int
     * @date 11/22/2020
     */
    int distanceBetweenTowns(String... townNames);

    /**
     * 功能描述 最大站点数间的路线数量
     *
     * @return int
     * @date 11/23/2020
     */
    int numberTripsWithMaxStops(String start, String dest, int maxHops, List<HashMap<String, Integer>> list);

    /**
     * 功能描述 给定指定数的站点，查询对应的路线的数量
     *
     * @return int
     * @date 11/23/2020
     */
    int numberTripsWithSpecialStops(String start, String dest, int stops);

    /**
     * 功能描述 查询最大距离间的路线数量
     *
     * @return int
     * @date 11/23/2020
     */
    int numberTripsWithMaxDistance(String start, String dest, int cost, int maxDistance);
}
