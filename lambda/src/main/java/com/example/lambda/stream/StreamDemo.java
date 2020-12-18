package com.example.lambda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Barret
 * @description
 * @date 11/30/2020
 */

/**
 * 中间方法和终点方法：
 * 它具有过滤、映射以及减少遍历数等方法，这些方法分两种：中间方法和终端方法，
 * “流”抽象天生就该是持续的，中间方法永远返回的是Stream，因此如果我们要获取最终结果的话，
 * 必须使用终点操作才能收集流产生的最终结果。区分这两个方法是看他的返回值，
 * 如果是Stream则是中间方法，否则是终点方法
 * 中间方法：map，limit，filter，distinct
 * 终点方法：count ，collect（收集流的结果）
 */
public class StreamDemo {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setAge(1);
        p1.setName("n1");
        Person p2 = new Person();
        p2.setAge(3);
        p2.setName("n2");
        Person p3 = new Person();
        p3.setAge(5);
        p3.setName("n3");
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        /***********************************************************/


        Stream<Adult> adultStream = list.stream().filter(p4 -> p4.getAge() > 2).map(per -> new Adult());
        List<Adult> collect = adultStream.collect(Collectors.toList());
        collect.forEach(ppp -> System.out.println(ppp.getAge()));

        List<Person> collect1 = list.stream().limit(2).collect(Collectors.toList());
        collect1.forEach(ddd -> System.out.println(ddd.getName()));
    }
}
