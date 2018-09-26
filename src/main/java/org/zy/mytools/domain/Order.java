package org.zy.mytools.domain;

import java.io.Serializable;

/**
 * Created by yuezhang on 18/9/26.
 */
public class Order implements Serializable {

    private String statementId;

    private String userName;

    private String goodsName;

    private String system;

    private String amount;


    public Order(){

    }

    public Order(String csvLine){
        String [] array = csvLine.split(",");
        statementId = array[0];
        userName = array[1];
        goodsName = array[2];
        system = array[3];
        amount = array[4];
    }

    public static String getWriteCsv(Order order){
        StringBuilder sb = new StringBuilder();
        sb.append(order.getStatementId()).append("\t").append(",")
                .append(order.getUserName()).append(",")
                .append(order.getGoodsName()).append(",")
                .append(order.getAmount()).append(",")
                .append(order.getSystem());
        return sb.toString();
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "Order{" +
                "statementId='" + statementId + '\'' +
                ", userName='" + userName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", amount='" + amount + '\'' +
                ", system='" + system + '\'' +
                '}';
    }
}
