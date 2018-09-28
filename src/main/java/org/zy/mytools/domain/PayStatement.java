package org.zy.mytools.domain;

import java.io.Serializable;

/**
 * Created by yuezhang on 18/9/23.
 */
public class PayStatement implements Serializable{

    private String statementId;

    private String transactionId;

    private String amount;

    private String subject;

    private String userName;

    private String outTradeNo;

    public PayStatement(){

    }

    public PayStatement(String csvLine){
        String [] array = csvLine.split(",");
        statementId = array[0];
        transactionId = array[1];
        amount = array[2];
        subject = array[3];
    }

    public static String getWriteCsv(PayStatement payStatement){
        StringBuilder sb = new StringBuilder();
        sb.append(payStatement.getStatementId()).append("\t").append(",")
                .append(payStatement.getTransactionId()).append("\t").append(",")
                .append(payStatement.getAmount()).append(",")
                .append(payStatement.getSubject());
        return sb.toString();
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString() {
        return "PayStatement{" +
                "statementId='" + statementId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", amount='" + amount + '\'' +
                ", subject='" + subject + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
