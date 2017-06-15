package com.alinge.market.brand.entity;
import com.alinge.market.common.entity.ResultEntity;
import java.util.List;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-01 11:02
 * Describe:
 */
public class BrandEntity {

   private List<BrandItemEntity> List;
    private ResultEntity Result;

    public BrandEntity() {
    }

    public BrandEntity(java.util.List<BrandItemEntity> list, ResultEntity result) {
        List = list;
        Result = result;
    }

    public List<BrandItemEntity> getList() {
        return List;
    }

    public void setList(List<BrandItemEntity> list) {
        List = list;
    }

    public ResultEntity getResult() {
        return Result;
    }

    public void setResult(ResultEntity result) {
        Result = result;
    }

    @Override
    public String toString() {

        return super.toString();
    }
}
