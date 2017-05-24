package com.alinge.market.home.entity;

import com.alinge.market.common.entity.AppEntity;
import com.alinge.market.common.entity.ResultEntity;

import java.util.List;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-03 10:52
 * Describe:
 */
public class HomeEntity {

    /**
     * GiftsList : [{"AppIcon":"应用图标地址","AvgScore":"平均评分(double)","Developer":"开发商","DownUrl":"应用下载地址","DownloadCount":"下载次数(int)","PackageName":"软件包名","Id":"应用文件ID","InstallType":"安装方式(0普通安装，1定制安装)","Md5Code":"MD5值","Toll":"是否包含收费项目(0-否，1-是 int型)","SoftwareName":"应用名称","Version":"版本号","FileSize":"文件大小"}]
     * NewestList : [{"AppIcon":"应用图标地址","AvgScore":"平均评分(double)","Developer":"开发商","DownUrl":"应用下载地址","DownloadCount":"下载次数(int)","PackageName":"软件包名","Id":"应用文件ID","InstallType":"安装方式(0普通安装，1定制安装)","Md5Code":"MD5值","Toll":"是否包含收费项目(0-否，1-是 int型)","SoftwareName":"应用名称","Version":"版本号","FileSize":"文件大小"}]
     * RecommendList : [{"AppIcon":"应用图标地址","AvgScore":"平均评分(double)","Developer":"开发商","DownUrl":"应用下载地址","DownloadCount":"下载次数(int)","PackageName":"软件包名","Id":"应用文件ID","InstallType":"安装方式(0普通安装，1定制安装)","Md5Code":"MD5值","Toll":"是否包含收费项目(0-否，1-是 int型)","SoftwareName":"应用名称","Version":"版本号","FileSize":"文件大小"}]
     * BannerList : [{"AppIcon":"应用图标地址","AvgScore":"平均评分(double)","Developer":"开发商","DownUrl":"应用下载地址","DownloadCount":"下载次数(int)","PackageName":"软件包名","Id":"应用文件ID","InstallType":"安装方式(0普通安装，1定制安装)","Md5Code":"MD5值","Toll":"是否包含收费项目(0-否，1-是 int型)","SoftwareName":"应用名称","Version":"版本号","FileSize":"文件大小"}]
     * Result : {"ReturnCode":"返回值","ReturnMessage":"返回消息"}
     */

    private ResultEntity Result;
    private List<AppEntity> GiftsList;
    private List<AppEntity> NewestList;
    private List<AppEntity> RecommendList;
    private List<AppEntity> BannerList;

    public ResultEntity getResult() {
        return Result;
    }

    public void setResult(ResultEntity Result) {
        this.Result = Result;
    }

    public List<AppEntity> getGiftsList() {
        return GiftsList;
    }

    public void setGiftsList(List<AppEntity> GiftsList) {
        this.GiftsList = GiftsList;
    }

    public List<AppEntity> getNewestList() {
        return NewestList;
    }

    public void setNewestList(List<AppEntity> NewestList) {
        this.NewestList = NewestList;
    }

    public List<AppEntity> getRecommendList() {
        return RecommendList;
    }

    public void setRecommendList(List<AppEntity> RecommendList) {
        this.RecommendList = RecommendList;
    }

    public List<AppEntity> getBannerList() {
        return BannerList;
    }

    public void setBannerList(List<AppEntity> BannerList) {
        this.BannerList = BannerList;
    }

}