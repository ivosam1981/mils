package com.ivosam.mils.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParam(String s){
        try {
            URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
           return "";
        }
        return s;
    }


    public static List<Long> decodeLogList(String s){
        return Arrays.asList(s.split(",")).stream().
                map(x -> Long.parseLong(x)).collect(Collectors.toList());
    }
}
