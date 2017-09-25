package com.chw.spb.common;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
public class PageUtil {

    public static Map<String, Object> createDatagrid(long total, List rows) {
        Map<String,Object> data = Maps.newHashMap();
        data.put("total", total);
        data.put("rows", rows);
        return data;
    }

}
