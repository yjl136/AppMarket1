package com.alinge.http;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-19 10:02
 * Describe:
 */

public class HttpDownManager {
    //完成开始下载、暂停、继续、取消，完成
    //下载监听、暂停监听、继续监听、取消监听、完成监听
    //断点续传
    private static HttpDownManager mHttpDownManager;

    private HttpDownManager() {
    }

    private static HttpDownManager getInstance(){
        if(mHttpDownManager==null){
            synchronized (mHttpDownManager){
                if(mHttpDownManager==null){
                    mHttpDownManager = new HttpDownManager();
                }
            }
        }
        return mHttpDownManager;
    }

}
