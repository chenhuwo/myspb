package com.chw.spb.system.service;

import java.util.HashMap;
import java.util.Map;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
public class ResultMapService {

    private static final Map<String,String> resultMap = new HashMap<String,String>();

    public static void clear(){
        if (resultMap != null) {
            resultMap.clear();
        }
    }

    public static  void addResultMap(String key,String value) {
        resultMap.put(key, value);
    }

    public static String get(String key) {
        return resultMap.get(key);
    }

    public static Map<String,String> getResultMap() {
        return resultMap;
    }
}
