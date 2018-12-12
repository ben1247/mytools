package org.zy.mytools.domain;

import org.zy.mytools.util.StringUtil;

import java.io.Serializable;

/**
 * 订单导出对象
 */
public class OrderExportInfo implements Serializable {

    private String orderNo;
    private String transactionNo;
    private String userName;
    private String payWay;
    private String payWayName;
    private String amount;
    private String payAmount;
    private String status;
    private String appplt;
    private String appid;
    private String createTime;
    private String updateTime;
    private String payTime;
    private String canal;
    private String outTradeNo;
    private String outTransactionNo;
    private String channelTradeNo;
    private String aid;
    private String cid;
    private String actid;
    private String actDetailId;
    private String actOriginId;
    private String goodsNo;
    private String goodsName;
    private String price;
    private String goodsNum;
    private String rightsNo;
    private String rightsCategory;
    private String brandName;


    public OrderExportInfo(){

    }

    public OrderExportInfo(String csvLine){
        String [] array = csvLine.split(",");
        try {
            this.orderNo = array[0]; // 订单号
            this.transactionNo = array[1]; // 支付流水号
            this.userName = array[2]; // 下单用户名
            this.payWay = array[3]; // 支付方式
            this.payWayName = array[4]; // 支付方式解释
            this.amount = array[5]; // 支付金额
            this.payAmount = array[6]; // 应付金额
            this.status = array[7]; // 支付状态
            this.appplt = array[8]; // 平台类型
            this.appid = array[9]; // 产品线
            this.createTime = array[10]; // 下单时间
            this.updateTime = array[11]; // 更新时间
            this.payTime = array[12]; // 支付时间
            this.canal = array[13]; // 渠道来源
            this.outTradeNo = array[14]; // 外部订单号
            this.outTransactionNo = array[15]; // 外部交易流水号
            this.channelTradeNo = array[16]; // 渠道订单号
            this.aid = array[17]; // 位置入口id
            this.cid = array[18]; // 来源内容ID
            this.actid = array[19]; // 活动ID
            this.actDetailId = array[20]; // 活动明细ID
            this.actOriginId = array[21]; // 活动原始ID
            this.goodsNo = array[22]; // 商品编号
            this.goodsName = array[23]; // 商品名称
            this.price = array[24]; // 商品价格
            this.goodsNum = array[25]; // 购买数量
            this.rightsNo = array[26]; // 权益编号
            this.rightsCategory = array[27]; // 权益类型
            this.brandName = array[28]; // 品牌名称
        }catch (Exception e){
            System.out.println("出错了!!!："  + csvLine);
        }

    }

    public static String getWriteCsv(OrderExportInfo order){
        StringBuilder sb = new StringBuilder();
        sb.append(order.getOrderNo()).append("\t").append(",") // 订单号
                .append(order.getTransactionNo()).append("\t").append(",") // 支付流水号
                .append(order.getUserName()).append(",") // 下单用户名
                .append(order.getPayWay()).append(",") // 支付方式
                .append(order.getPayWayName()).append(",") // 支付方式解释
                .append(order.getAmount()).append(",") // 支付金额
                .append(order.getPayAmount()).append(",") // 应付金额
                .append(order.getStatus()).append(",") // 支付状态
                .append(order.getAppplt()).append(",") // 平台类型
                .append(order.getAppid()).append(",") // 产品线
                .append(order.getCreateTime()).append(",") // 下单时间
                .append(order.getUpdateTime()).append(",") // 更新时间
                .append(order.getPayTime()).append(",") // 支付时间
                .append(order.getCanal()).append(",") // 渠道来源
                .append(order.getOutTradeNo()).append(",") // 外部订单号
                .append(order.getOutTransactionNo()).append(",") // 外部交易流水号
                .append(order.getChannelTradeNo()).append(",") // 渠道订单号
                .append(order.getAid()).append(",") // 位置入口id
                .append(order.getCid()).append(",") // 来源内容ID
                .append(order.getActid()).append(",") // 活动ID
                .append(order.getActDetailId()).append(",") // 活动明细ID
                .append(order.getActOriginId()).append(",") // 活动原始ID
                .append(order.getGoodsNo()).append(",") // 商品编号
                .append(order.getGoodsName()).append(",") // 商品名称
                .append(order.getPrice()).append(",") // 商品价格
                .append(order.getGoodsNum()).append(",") // 购买数量
                .append(order.getRightsNo()).append(",") // 权益编号
                .append(order.getRightsCategory()).append(",") // 权益类型
                .append(order.getBrandName()); // 品牌名称
        return sb.toString();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppplt() {
        return appplt;
    }

    public void setAppplt(String appplt) {
        this.appplt = appplt;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTransactionNo() {
        return outTransactionNo;
    }

    public void setOutTransactionNo(String outTransactionNo) {
        this.outTransactionNo = outTransactionNo;
    }

    public String getChannelTradeNo() {
        return channelTradeNo;
    }

    public void setChannelTradeNo(String channelTradeNo) {
        this.channelTradeNo = channelTradeNo;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getActid() {
        return actid;
    }

    public void setActid(String actid) {
        this.actid = actid;
    }

    public String getActDetailId() {
        return actDetailId;
    }

    public void setActDetailId(String actDetailId) {
        this.actDetailId = actDetailId;
    }

    public String getActOriginId() {
        return actOriginId;
    }

    public void setActOriginId(String actOriginId) {
        this.actOriginId = actOriginId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getRightsNo() {
        return rightsNo;
    }

    public void setRightsNo(String rightsNo) {
        this.rightsNo = rightsNo;
    }

    public String getRightsCategory() {
        return rightsCategory;
    }

    public void setRightsCategory(String rightsCategory) {
        this.rightsCategory = rightsCategory;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
