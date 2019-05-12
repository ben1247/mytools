package org.zy.mytools.domain;

/**
 * Created by yuezhang on 2019/5/12.
 */
public class BaseStat {

    private String key;
    private int count;

    public BaseStat(String key){
        this(key,0);
    }

    public BaseStat(String key , int count){
        this.key = key;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
