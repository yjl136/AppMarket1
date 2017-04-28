package com.alinge.market.home.entity;

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

    public static class ResultEntity {
        /**
         * ReturnCode : 返回值
         * ReturnMessage : 返回消息
         */

        private String ReturnCode;
        private String ReturnMessage;

        public String getReturnCode() {
            return ReturnCode;
        }

        public void setReturnCode(String ReturnCode) {
            this.ReturnCode = ReturnCode;
        }

        public String getReturnMessage() {
            return ReturnMessage;
        }

        public void setReturnMessage(String ReturnMessage) {
            this.ReturnMessage = ReturnMessage;
        }
    }

    public static class AppEntity {
        /**
         * AppIcon : 应用图标地址
         * AvgScore : 平均评分(double)
         * Developer : 开发商
         * DownUrl : 应用下载地址
         * DownloadCount : 下载次数(int)
         * PackageName : 软件包名
         * Id : 应用文件ID
         * InstallType : 安装方式(0普通安装，1定制安装)
         * Md5Code : MD5值
         * Toll : 是否包含收费项目(0-否，1-是 int型)
         * SoftwareName : 应用名称
         * Version : 版本号
         * FileSize : 文件大小
         */

        private String AppIcon;
        private double AvgScore;
        private String Developer;
        private String DownUrl;
        private int DownloadCount;
        private String PackageName;
        private int Id;
        private int InstallType;
        private String Md5Code;
        private int Toll;
        private String SoftwareName;
        private String Version;
        private String FileSize;

        public String getAppIcon() {
            return AppIcon;
        }

        public void setAppIcon(String AppIcon) {
            this.AppIcon = AppIcon;
        }

        public double getAvgScore() {
            return AvgScore;
        }

        public void setAvgScore(double AvgScore) {
            this.AvgScore = AvgScore;
        }

        public String getDeveloper() {
            return Developer;
        }

        public void setDeveloper(String Developer) {
            this.Developer = Developer;
        }

        public String getDownUrl() {
            return DownUrl;
        }

        public void setDownUrl(String DownUrl) {
            this.DownUrl = DownUrl;
        }

        public int getDownloadCount() {
            return DownloadCount;
        }

        public void setDownloadCount(int DownloadCount) {
            this.DownloadCount = DownloadCount;
        }

        public String getPackageName() {
            return PackageName;
        }

        public void setPackageName(String PackageName) {
            this.PackageName = PackageName;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getInstallType() {
            return InstallType;
        }

        public void setInstallType(int InstallType) {
            this.InstallType = InstallType;
        }

        public String getMd5Code() {
            return Md5Code;
        }

        public void setMd5Code(String Md5Code) {
            this.Md5Code = Md5Code;
        }

        public int getToll() {
            return Toll;
        }

        public void setToll(int Toll) {
            this.Toll = Toll;
        }

        public String getSoftwareName() {
            return SoftwareName;
        }

        public void setSoftwareName(String SoftwareName) {
            this.SoftwareName = SoftwareName;
        }

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }

        public String getFileSize() {
            return FileSize;
        }

        public void setFileSize(String FileSize) {
            this.FileSize = FileSize;
        }
    }


}