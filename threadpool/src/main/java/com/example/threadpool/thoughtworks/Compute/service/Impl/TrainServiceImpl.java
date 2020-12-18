package com.example.threadpool.thoughtworks.Compute.service.Impl;

import com.example.threadpool.thoughtworks.Compute.service.TrainService;

import java.util.HashMap;
import java.util.List;

/**
 * @author Barret
 * @description
 * @date 11/22/2020
 */
public class TrainServiceImpl implements TrainService {
    private int[][] map;

    public TrainServiceImpl(int[][] map) {
        this.map = map;
    }

    //给shortestPathLengthBetweenTwoTowns计算两个站点间最短距离的参数，shortestLength代表最短的距离权重
    public int shortestLength = Integer.MAX_VALUE;

    //查询两站点之间的距离不超过最大值的路线数量，默认为0
    public int numberTripsWithMaxDistanceCount = 0;

    /**
     * 功能描述 按层查询，使用DFS进行搜索，查到对应的终点站，看对应的距离是否最短，以start为路线起点
     *
     * @return int
     * @date 11/22/2020
     */
    @Override
    public int shortestPathLengthBetweenTwoTowns(String start, String dest, int cost) {
        //起始的花费距离为0，由调用方法传入
        if (start.endsWith(dest) && cost < shortestLength && cost > 0) {
            shortestLength = cost;
            return 0;
        }
        //查询路线对应的最后一个站点的名称
        char lastChar = start.charAt(start.length() - 1);
        int index = lastChar - 'A';
        for (int i = 0; i < map[index].length; i++) {
            char newChar = (char) (i + 'A');
            //下一节点的边缘权重，也就是实际到一下站点的实际长度
            int nextCost = map[index][i];
            if (nextCost > 0) {
                if (start.indexOf(newChar) > 0) {
                    continue;
                }
                //进行深度优先递归
                shortestPathLengthBetweenTwoTowns(start + newChar, dest, cost + nextCost);
            }
        }
        return shortestLength;
    }

    /**
     * 功能描述 传递一个字符串数组，代表站点的路线顺序
     *
     * @return int
     * @date 11/23/2020
     */
    @Override
    public int distanceBetweenTowns(String... townNames) {
        if (townNames.length < 2) {
            return 0;
        }
        int cost = 0;
        //路线的起点
        char fistStop = townNames[0].charAt(0);
        int row = fistStop - 'A';
        for (int i = 1; i < townNames.length; i++) {
            //下一站点
            char nextStop = townNames[i].charAt(0);
            int col = nextStop - 'A';
            if (map[row][col] == 0) {
                return -1;
            }
            //计算到下一站点的长度
            cost += map[row][col];
            //将一下站点置为下一次寻找的起始站点
            row = col;
        }
        return cost;
    }

    @Override
    public int numberTripsWithMaxStops(String start, String dest, int maxHops, List<HashMap<String, Integer>> list) {

        if (start.length() - 1 > maxHops) {
            return 0;
        }
        //如果路线总长度大于1，表示不是起始，并且结尾是和终点站相同，则成功找到一条路线
        if (start.length() > 1 && start.endsWith(dest)) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put(start, start.length());
            list.add(map);
        }
        //使用ASCII码的差值区分当前town在map中的第几行
        char lastChar = start.charAt(start.length() - 1);
        int lastNodeIndex = lastChar - 'A';

        for (int i = 0; i < map[lastNodeIndex].length; i++) {
            char newChar = (char) (i + 'A');
            if (map[lastNodeIndex][i] > 0) {
                numberTripsWithMaxStops(start + newChar, dest, maxHops, list);
            }
        }
        return list.size();
    }

    /**
     * 功能描述 经过指定的站点数量，用二维数组描述，则代表走过指定层数的循环
     *
     * @return int
     * @date 11/22/2020
     */
    @Override
    public int numberTripsWithSpecialStops(String start, String dest, int stops) {
        //设置起始站点
        String lastRoute = start;
        //stops代表层数，当走过指定层数，最后一层的站点名称与终点站点名称符合，则代表一条路线
        for (int stop = 0; stop < stops; stop++) {
            StringBuilder route = new StringBuilder();
            for (int i = 0; i < lastRoute.length(); i++) {
                //当前层的某个站点的是在二维数组中的第几行
                char c = lastRoute.charAt(i);
                int row = c - 'A';
                for (int j = 0; j < map[row].length; j++) {
                    if (map[row][j] > 0) {
                        //使用ASCII码进行ABCD的加减，表示二维数组中的行数
                        route.append((char) (j + 'A'));
                    }
                }
            }
            lastRoute = route.toString();
        }
        //最后一层站点数中包含了几个对应的终点站名称，则代表几条路线，使用split函数用其分开进行技术
        return lastRoute.split(dest).length - 1;
    }

    /**
     * 功能描述
     *
     * @return int
     * @date 11/22/2020
     */
    @Override
    public int numberTripsWithMaxDistance(String start, String dest, int cost, int maxDistance) {
        //最大距离
        if (cost >= maxDistance) {
            return 0;
        }
        if (cost > 0 && start.endsWith(dest)) {
            numberTripsWithMaxDistanceCount++;
        }
        char lastChar = start.charAt(start.length() - 1);
        int index = lastChar - 'A';
        for (int i = 0; i < map[index].length; i++) {
            char newChar = (char) (i + 'A');
            //表示二维数组中的值，也代表站点间的距离
            int next = map[index][i];
            if (next > 0) {
                //进行深度优先递归，将已形成的路线以及长度传到递归方法中
                numberTripsWithMaxDistance(start + newChar, dest, cost + next, maxDistance);
            }
        }
        return numberTripsWithMaxDistanceCount;
    }
}
