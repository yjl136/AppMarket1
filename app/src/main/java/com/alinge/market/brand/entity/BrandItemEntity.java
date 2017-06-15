package com.alinge.market.brand.entity;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-01 11:10
 * Describe:
 */
public class BrandItemEntity {

    private String BrandImg;
    private String BrandImgForList;
    private String BrandName;
    private int Id;
    private int IsDisplay;
    private int Sort;


    public BrandItemEntity() {
    }

    public BrandItemEntity(String brandImg, String brandImgForList, String brandName, int id, int isDisplay, int sort) {
        BrandImg = brandImg;
        BrandImgForList = brandImgForList;
        BrandName = brandName;
        Id = id;
        IsDisplay = isDisplay;
        Sort = sort;
    }

    public String getBrandImg() {
        return BrandImg;
    }

    public void setBrandImg(String brandImg) {
        BrandImg = brandImg;
    }

    public String getBrandImgForList() {
        return BrandImgForList;
    }

    public void setBrandImgForList(String brandImgForList) {
        BrandImgForList = brandImgForList;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIsDisplay() {
        return IsDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        IsDisplay = isDisplay;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }

    @Override
    public String toString() {

        return "BrandImg :"+BrandImg
                +"   BrandImgForList :"+BrandImgForList
                +"   IsDisplay :"+IsDisplay
                +"   Sort :"+Sort;

    }
}
