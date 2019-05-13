package org.zy.mytools.util;

import org.zy.mytools.domain.BaseStat;

import java.util.List;

/**
 * Created by yuezhang on 2019/5/12.
 */
public class BaseStatUtil {

    public static int get(String key , List<BaseStat> list){
        if (key == null || "".equals(key) || list == null || list.size() == 0) return 0;

        for (BaseStat stat: list){
            if (stat.getKey().equals(key)){
                return stat.getCount();
            }
        }
        return 0;
    }

    public static void put(String key , int count , List<BaseStat> list){
        if (key == null || "".equals(key) || list == null || list.size() == 0) return ;

        for (BaseStat stat: list){
            if (stat.getKey().equals(key)){
                stat.setCount(count);
                return;
            }
        }
    }

    public static String getName(String key , List<BaseStat> list){
        if (key == null || "".equals(key) || list == null || list.size() == 0) return null;

        for (BaseStat stat: list){
            if (stat.getKey().equals(key)){
                return stat.getName();
            }
        }
        return null;
    }

}
