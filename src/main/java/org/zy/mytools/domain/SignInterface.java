package org.zy.mytools.domain;

/**
 * Created by yuezhang on 18/10/16.
 */
public interface SignInterface {

    /**
     * 获取 sign
     *
     * @return
     */
    public String getSign();

    /**
     * 获取 merchantId
     *
     * @return
     */
    public String getMerchantId();

    public void setSign(String sign);

    public void setMerchantId(String merchantId);

}
