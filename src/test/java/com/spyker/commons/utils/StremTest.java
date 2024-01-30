package com.spyker.commons.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StremTest {

    @Test
    public void test1() {

        String[] args = new String[2];
        args[0] = "22";
        args[1] = "23";

        List<String> ss = Arrays.stream(args).collect(Collectors.toList());

        System.out.println(ss);

        List<Integer> il = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        int max = il.stream().sorted().max(Comparator.comparing(Integer::valueOf)).get();

        System.out.println(max);
    }
}