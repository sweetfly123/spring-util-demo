package com.example.lambda.arrowsfunction;

import java.util.Arrays;
import java.util.List;

/**
 * @author Barret
 * (params) -> expression
 * (params) -> statement
 * (params) -> { statements }
 * @description
 * @date 11/30/2020
 */
public class Arrows {
    public static final String AA = "SDFSDF";

    public static void main(String[] args) {
        new Thread(() -> System.out.println("arrows function")).start();

        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> {
            System.out.println(n);
        });
        features.forEach(s -> System.out.println(s));

    }
}
