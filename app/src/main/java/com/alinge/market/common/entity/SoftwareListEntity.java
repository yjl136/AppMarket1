package com.alinge.market.common.entity;

import java.util.List;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-01 14:01
 * Describe:
 */

public class SoftwareListEntity {
    private List<AppEntity> List;
    private ResultEntity Result;

    public SoftwareListEntity() {
    }

    public SoftwareListEntity(java.util.List<AppEntity> list, ResultEntity result) {
        List = list;
        Result = result;
    }

    public void setList(java.util.List<AppEntity> list) {
        List = list;
    }

    public void setResult(ResultEntity result) {
        Result = result;
    }

    public java.util.List<AppEntity> getList() {
        return List;
    }

    public ResultEntity getResult() {
        return Result;
    }
}
