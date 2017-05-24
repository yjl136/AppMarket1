package com.alinge.market.update;

import com.alinge.market.common.entity.AppEntity;
import com.alinge.market.common.entity.ResultEntity;

import java.util.List;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-05-24 15:50
 * Describe:
 */

public class UpdateListEntity {
    private List<AppEntity> List;
    private ResultEntity Result;

    public UpdateListEntity() {
    }

    public UpdateListEntity(java.util.List<AppEntity> list, ResultEntity result) {
        List = list;
        Result = result;
    }

    public java.util.List<AppEntity> getList() {
        return List;
    }

    public ResultEntity getResult() {
        return Result;
    }

    public void setList(java.util.List<AppEntity> list) {
        List = list;
    }

    public void setResult(ResultEntity result) {
        Result = result;
    }
}
