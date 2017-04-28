package com.alinge.market.brand.entity;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-01 11:14
 * Describe:
 */
public class ResultEntity {

    private int ReturnCode;
    private String ReturnMessage;

    public ResultEntity() {
    }

    public ResultEntity(int returnCode, String returnMessage) {
        ReturnCode = returnCode;
        ReturnMessage = returnMessage;
    }

    public int getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(int returnCode) {
        ReturnCode = returnCode;
    }

    public String getReturnMessage() {
        return ReturnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        ReturnMessage = returnMessage;
    }

    @Override
    public String toString() {
        return "ReturnCode:"+ReturnCode+"  ReturnMessage:"+ReturnMessage;
    }
}
