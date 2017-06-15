package com.alinge.market.common.entity;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-05-24 15:43
 * Describe:
 */

public class ResultEntity {
    /**
     * ReturnCode : 返回值
     * ReturnMessage : 返回消息
     */

    private double ReturnCode;
    private String ReturnMessage;

    public double getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(double ReturnCode) {
        this.ReturnCode = ReturnCode;
    }

    public String getReturnMessage() {
        return ReturnMessage;
    }

    public void setReturnMessage(String ReturnMessage) {
        this.ReturnMessage = ReturnMessage;
    }
    @Override
    public String toString() {
        return "ReturnCode:"+ReturnCode+"  ReturnMessage:"+ReturnMessage;
    }
}
