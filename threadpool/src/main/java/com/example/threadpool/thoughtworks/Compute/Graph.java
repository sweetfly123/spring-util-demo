package com.example.threadpool.thoughtworks.Compute;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 功能描述 使用二维数组表示图
 *
 * @author Barret
 * @date 11/22/2020
 * @return
 */
public class Graph {
    private int[][] map;

    /**
     * 功能描述 初始化地图；
     *
     * @return void
     * @date 11/22/2020
     */
    public void init(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(path));
        //存入站点的名称
        Set<Character> set = new HashSet<>();
        //记录本文的内容，用于初始化数组
        List<String> list = new ArrayList<>();
        while (in.hasNext()) {
            String input = in.next();
            if (input.length() < 3) {
                throw new FileNotFoundException("input args error");
            }
            //将输入站点的名字加入到HashSet中，最后通过set的大小则知道数组容量
            set.add(input.charAt(0));
            set.add(input.charAt(1));
            list.add(input);
        }
        int[][] newMap = new int[set.size()][set.size()];
        for (String str : list) {
            //将二维数组赋值
            newMap[str.charAt(0) - 'A'][str.charAt(1) - 'A'] = Integer.parseInt(str.substring(2));
        }
        /*int[][] newMap = {
                {-1, 5, -1, 5, 7},
                {-1, -1, 4, -1, -1},
                {-1, -1, -1, 8, 2},
                {-1, -1, 8, -1, 6},
                {-1, 3, -1, -1, -1},
        };*/
        this.map = newMap;
    }

    public int[][] getMap() {
        return this.map;
    }
}
