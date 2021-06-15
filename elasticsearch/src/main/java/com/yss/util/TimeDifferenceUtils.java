package com.yss.util;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class TimeDifferenceUtils {
    public static List<Map<String,Object>> listAdd(List<Map<String,Object>> object,String key) throws ParseException {
        for (Map<String, Object> map : object) {
            String dateDifference = TimeTransferUtils.format((String) map.get(key));
            map.put("dateDifference",dateDifference);
        }
        return object;
    }
    public static Map<String,Object> mapAdd(Map<String,Object>map,String key) throws ParseException {
        String dateDifference = TimeTransferUtils.format((String) map.get(key));
        map.put("dateDifference",dateDifference);
        return map;
    }
}
