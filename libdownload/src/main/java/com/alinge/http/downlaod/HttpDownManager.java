package com.alinge.http.downlaod;


import android.os.Environment;
import android.text.TextUtils;

import com.alinge.http.downlaod.DownLoadListener.DownloadInterceptor;
import com.alinge.http.exception.HttpTimeException;
import com.alinge.http.exception.RetryWhenNetworkException;
import com.alinge.http.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * http下载处理类
 * Created by WZG on 2016/7/16.
 */
public class HttpDownManager {
    /*记录下载数据*/
    private HashMap<String,DownInfo>  downInfos;
    private HashMap<String,ProgressDownSubscriber> subMap;

    /*单利对象*/
    private volatile static HttpDownManager INSTANCE;

    private HttpDownManager(){
        downInfos=new HashMap<>();
        subMap = new HashMap<>();
    }

    /**
     * 获取单例
     * @return
     */
    public static HttpDownManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpDownManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDownManager();
                }
            }
        }
        return INSTANCE;
    }
    public void startDown(String url){
        startDown(url,Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    /**
     * 开始下载
     */
    public void startDown(String url,String path){
        DownInfo info = null;
        HttpService httpService;
        ProgressDownSubscriber subscriber;
        if(TextUtils.isEmpty(url)){
            throw new IllegalArgumentException("url==null");
        }
        if(downInfos.get(url)!= null){
             info = downInfos.get(url);
             subscriber=new ProgressDownSubscriber(info);
             httpService = info.getService();
        }else{
            info = new DownInfo(url);
            info.setSavePath(path);
            subscriber=new ProgressDownSubscriber(info);
            DownloadInterceptor interceptor = new DownloadInterceptor(subscriber);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //手动创建一个OkHttpClient并设置超时时间
            builder.connectTimeout(info.getConnectionTime(), TimeUnit.SECONDS);
            builder.addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(info.getBaseUrl())
                    .build();
            httpService= retrofit.create(HttpService.class);
            info.setService(httpService);
        }
        downInfos.put(info.getUrl(),info);
        subMap.put(info.getUrl(),subscriber);
        /*得到rx对象-上一次下載的位置開始下載*/
        httpService.download("bytes=" + info.getReadLength() + "-",info.getUrl())
                /*指定线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                   /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException())
                /*读取下载写入文件*/
                .map(new Func(info))
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                /*数据回调*/
                .subscribe(subscriber);

    }


    /**
     * 停止下载
     */
    public void stopDown(String url){
        if(url==null || downInfos.get(url)==null)return;
        DownInfo info = downInfos.get(url);
        info.setState(DownState.STOP);
        info.getListener().onStop();
        if(subMap.containsKey(info.getUrl())) {
            ProgressDownSubscriber subscriber=subMap.get(info.getUrl());
            subscriber.unsubscribe();
            subMap.remove(info.getUrl());
        }
        /*同步数据库*/
    }


    /**
     * 删除
     * @param url
     */
    public void deleteDown(String url){
        stopDown(url);
         /*删除数据库信息和本地文件*/
    }


    /**
     * 暂停下载
     * @param url
     */
    public void pause(String url){
        if(url==null || downInfos.get(url)==null)return;
        DownInfo info = downInfos.get(url);
        info.setState(DownState.PAUSE);
        info.getListener().onPuase();
        if(subMap.containsKey(info.getUrl())){
            ProgressDownSubscriber subscriber=subMap.get(info.getUrl());
            subscriber.unsubscribe();
            subMap.remove(info.getUrl());
        }
        /*这里需要讲info信息写入到数据中，可自由扩展，用自己项目的数据库*/
    }

    /**
     * 停止全部下载
     */
    public void stopAllDown(){
        for (DownInfo downInfo : downInfos.values()) {
            stopDown(downInfo.getUrl());
        }
        subMap.clear();
        downInfos.clear();
    }

    /**
     * 暂停全部下载
     */
    public void pauseAll(){
        for (DownInfo downInfo : downInfos.values()) {
            pause(downInfo.getUrl());
        }
        subMap.clear();
        downInfos.clear();
    }


    /**
     * 返回全部正在下载的数据
     * @return
     */
    public Map<String, DownInfo> getDownInfos() {
        return downInfos;
    }


    /**
     * 写入文件
     * @param file
     * @param info
     * @throws IOException
     */
    public void writeCache(ResponseBody responseBody,File file,DownInfo info) throws IOException{
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        long allLength;
        if (info.getCountLength()==0){
            allLength=responseBody.contentLength();
        }else{
            allLength=info.getCountLength();
        }
            FileChannel channelOut = null;
            RandomAccessFile randomAccessFile = null;
            randomAccessFile = new RandomAccessFile(file, "rwd");
            channelOut = randomAccessFile.getChannel();
            MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                    info.getReadLength(),allLength-info.getReadLength());
            byte[] buffer = new byte[1024*8];
            int len;
            int record = 0;
            while ((len = responseBody.byteStream().read(buffer)) != -1) {
                mappedBuffer.put(buffer, 0, len);
                record += len;
            }
            responseBody.byteStream().close();
                if (channelOut != null) {
                    channelOut.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
    }
private class Func implements Func1<ResponseBody, DownInfo>{
    private DownInfo info;
    public Func(DownInfo info){
        this.info = info;
    }

    @Override
    public DownInfo call(ResponseBody responseBody) {
        try {
            writeCache(responseBody,new File(info.getSavePath()),info);
        } catch (IOException e) {
                            /*失败抛出异常*/
            throw new HttpTimeException(e.getMessage());
        }
        return info;
    }
}
}
