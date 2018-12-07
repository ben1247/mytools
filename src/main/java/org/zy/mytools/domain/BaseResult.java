package org.zy.mytools.domain;

/**
 * Created by yuezhang on 18/10/16.
 */
public class BaseResult {

    private boolean returnState = true;

    private String errMsg;

    private int errCode = 0;


    public boolean isReturnState() {
        return returnState;
    }

    public void setReturnState(boolean returnState) {
        this.returnState = returnState;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }


    @Override
    public String toString() {
        return "BaseResult{" +
                "returnState=" + returnState +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }

}
