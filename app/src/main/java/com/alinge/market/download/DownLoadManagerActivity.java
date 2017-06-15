package com.alinge.market.download;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.alinge.http.downlaod.DownInfo;
import com.alinge.http.downlaod.HttpDownManager;
import com.alinge.market.R;
import com.alinge.market.download.adapter.DownAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-15 16:36
 * Describe:
 */

public class DownLoadManagerActivity extends AppCompatActivity {
    List<DownInfo> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_laod);
        initResource();
        initWidget();
    }

    /*数据*/
    private void initResource(){
        listData=new ArrayList<>();
        String[] downUrl=new String[]{"http://www.izaodao.com/app/izaodao_app.apk",
                "http://huajiao.dl.keniub.com/app/android/apk/qumeng1_release.apk"};
        for (int i = 0; i < downUrl.length; i++) {
            File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "test"+i + ".apk");
            DownInfo apkApi=new DownInfo(downUrl[i]);
            apkApi.setSavePath(outputFile.getAbsolutePath());
            listData.add(apkApi);
        }
    }

    /*加载控件*/
    private void initWidget(){
        EasyRecyclerView recyclerView=(EasyRecyclerView)findViewById(R.id.rv);
        DownAdapter adapter=new DownAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.addAll(listData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*停止全部下载*/
        HttpDownManager.getInstance().stopAllDown();
    }
}
