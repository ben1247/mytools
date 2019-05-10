package org.zy.mytools.domain;

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
    private String appver;
    private String shopId;
    private String channelCode;
    private String createTime;
    private String updateTime;
    private String payTime;
    private String canal;
    private String marketingChannel;
    private String ppOpenId;
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
    private String rightsStatus;
    private String saleType;

    public OrderExportInfo(){

    }

    public OrderExportInfo(String csvLine){
        String [] array = csvLine.split(",");
        try {
            this.orderNo = array[0].trim(); // 订单号
            this.transactionNo = array[1].trim(); // 支付流水号
            this.userName = array[2].trim(); // 下单用户名
            this.payWay = array[3].trim(); // 支付方式
            this.payWayName = array[4].trim(); // 支付方式解释
            this.amount = array[5].trim(); // 支付金额
            this.payAmount = array[6].trim(); // 应付金额
            this.status = array[7].trim(); // 支付状态
            this.appplt = array[8].trim(); // 平台类型
            this.appid = array[9].trim(); // 产品线
            this.appver = array[10].trim(); // 版本号
            this.shopId = array[11].trim(); // 店铺ID
            this.channelCode = array[12].trim(); // 渠道编号
            this.createTime = array[13].trim(); // 下单时间
            this.updateTime = array[14].trim(); // 更新时间
            this.payTime = array[15].trim(); // 支付时间
            this.canal = array[16].trim(); // 渠道来源
            this.marketingChannel = array[17].trim(); // 营销推广渠道
            this.ppOpenId = array[18].trim(); // ppOpenId
            this.outTradeNo = array[19].trim(); // 外部订单号
            this.outTransactionNo = array[20].trim(); // 外部交易流水号
            this.channelTradeNo = array[21].trim(); // 渠道订单号
            this.aid = array[22].trim(); // 位置入口id
            this.cid = array[23].trim(); // 来源内容ID
            this.actid = array[24].trim(); // 活动ID
            this.actDetailId = array[25].trim(); // 活动明细ID
            this.actOriginId = array[26].trim(); // 活动原始ID
            this.goodsNo = array[27].trim(); // 商品编号
            this.goodsName = array[28].trim(); // 商品名称
            this.price = array[29].trim(); // 商品价格
            this.goodsNum = array[30].trim(); // 购买数量
            this.rightsNo = array[31].trim(); // 权益编号
            this.rightsCategory = array[32].trim(); // 权益类型
            this.brandName = array[33].trim(); // 品牌名称
            this.rightsStatus = array[34].trim(); //权益状态
            this.saleType = array[35].trim(); //销售类型
        }catch (Exception e){
            System.out.println("出错了!!!："  + csvLine);
        }

    }

    @Override
    public String toString() {
        return "OrderExportInfo{" +
                "orderNo='" + orderNo + '\'' +
                ", transactionNo='" + transactionNo + '\'' +
                ", userName='" + userName + '\'' +
                ", payWay='" + payWay + '\'' +
                ", payWayName='" + payWayName + '\'' +
                ", amount='" + amount + '\'' +
                ", payAmount='" + payAmount + '\'' +
                ", status='" + status + '\'' +
                ", appplt='" + appplt + '\'' +
                ", appid='" + appid + '\'' +
                ", appver='" + appver + '\'' +
                ", shopId='" + shopId + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", canal='" + canal + '\'' +
                ", marketingChannel='" + marketingChannel + '\'' +
                ", ppOpenId='" + ppOpenId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outTransactionNo='" + outTransactionNo + '\'' +
                ", channelTradeNo='" + channelTradeNo + '\'' +
                ", aid='" + aid + '\'' +
                ", cid='" + cid + '\'' +
                ", actid='" + actid + '\'' +
                ", actDetailId='" + actDetailId + '\'' +
                ", actOriginId='" + actOriginId + '\'' +
                ", goodsNo='" + goodsNo + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", price='" + price + '\'' +
                ", goodsNum='" + goodsNum + '\'' +
                ", rightsNo='" + rightsNo + '\'' +
                ", rightsCategory='" + rightsCategory + '\'' +
                ", brandName='" + brandName + '\'' +
                ", rightsStatus='" + rightsStatus + '\'' +
                ", saleType='" + saleType + '\'' +
                '}';
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

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getMarketingChannel() {
        return marketingChannel;
    }

    public void setMarketingChannel(String marketingChannel) {
        this.marketingChannel = marketingChannel;
    }

    public String getPpOpenId() {
        return ppOpenId;
    }

    public void setPpOpenId(String ppOpenId) {
        this.ppOpenId = ppOpenId;
    }

    public String getRightsStatus() {
        return rightsStatus;
    }

    public void setRightsStatus(String rightsStatus) {
        this.rightsStatus = rightsStatus;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }
}
