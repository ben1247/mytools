package org.zy.mytools.domain;

/**
 * Created by yuezhang on 2019/5/12.
 */
public class BaseStat {

    private String key;
    private String name;
    private int count;

    public BaseStat(String key,String name){
        this(key,name,0);
    }

    public BaseStat(String key , String name,int count){
        this.key = key;
        this.name = name;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
