package com.cobodeal.authserver.utils;

import io.swagger.v3.oas.annotations.servers.Server;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Server
public class StringUtil {

    private StringUtil() {
    }

    public static String convertIntegerToString(List<Integer> listOfIntegers) {
        return listOfIntegers.stream().map(String::valueOf).collect(Collectors.joining(",","(",")"));
    }
}
