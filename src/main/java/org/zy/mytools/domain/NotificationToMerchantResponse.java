package org.zy.mytools.domain;

/**
 * Created by yuezhang on 18/10/16.
 */
public class NotificationToMerchantResponse extends BaseResult implements SignInterface {

    private String outTradNo;

    private String totalFee;

    private String subject;

    private String transactionId;

    private String endTime;

    private int payWay;

    private String merchantId;

    private String sign;

    private String extraParameters;//一个map的json串,如果这个Json为空的时候,这个Json为null



    public String getOutTradNo() {
        return outTradNo;
    }

    public void setOutTradNo(String outTradNo) {
        this.outTradNo = outTradNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    @Override
    public String getSign() {
        return this.sign;
    }

    @Override
    public String getMerchantId() {
        return merchantId;
    }

    @Override
    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getExtraParameters() {
        return extraParameters;
    }

    public void setExtraParameters(String extraParameters) {
        this.extraParameters = extraParameters;
    }


    @Override
    public String toString() {
        return "NotificationToMerchantResponse{" +
                "outTradNo='" + outTradNo + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", subject='" + subject + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", endTime='" + endTime + '\'' +
                ", payWay=" + payWay +
                ", merchantId='" + merchantId + '\'' +
                ", sign='" + sign + '\'' +
                ", extraParameters='" + extraParameters + '\'' +
                '}';
    }

}
