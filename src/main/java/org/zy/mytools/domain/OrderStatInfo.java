package org.zy.mytools.domain;

import java.io.Serializable;

/**
 * 订单统计
 * Created by yuezhang on 2019/5/12.
 */
public class OrderStatInfo implements Serializable {

    private String appplt;

    private String goodsGroup;

    private String goods;

    private String payChannel;

    // 是否创建订单 0：否 1：是
    private int isCreate;

    // 是否支付 0：否 1：是
    private int isPay;


    public static String getWriteCsv(OrderStatInfo order){
        StringBuilder sb = new StringBuilder();
        sb.append(order.getAppplt()).append(",")
                .append(order.getGoodsGroup()).append(",")
                .append(order.getGoods()).append(",")
                .append(order.getPayChannel()).append(",")
                .append(order.getIsCreate()).append(",")
                .append(order.getIsPay());
        return sb.toString();
    }

    public String getAppplt() {
        return appplt;
    }

    public void setAppplt(String appplt) {
        this.appplt = appplt;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public int getIsCreate() {
        return isCreate;
    }

    public void setIsCreate(int isCreate) {
        this.isCreate = isCreate;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public String getGoodsGroup() {
        return goodsGroup;
    }

    public void setGoodsGroup(String goodsGroup) {
        this.goodsGroup = goodsGroup;
    }
}
