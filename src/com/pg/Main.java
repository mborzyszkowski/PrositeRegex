package com.pg;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int number = 4;
        System.out.println(number);
        ArrayList<Long> longList = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L));
        longList.forEach(System.out::println);
    }
}
