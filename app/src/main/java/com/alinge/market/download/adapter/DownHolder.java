package com.alinge.market.download.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alinge.http.downlaod.DownInfo;
import com.alinge.http.downlaod.HttpDownManager;
import com.alinge.http.listener.HttpProgressOnNextListener;
import com.alinge.market.R;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by WZG on 2016/10/21.
 */

public class DownHolder extends BaseViewHolder<DownInfo> implements View.OnClickListener{
    private TextView tvMsg;
    private NumberProgressBar progressBar;
    private DownInfo apkApi;
    private HttpDownManager manager;

    public DownHolder(ViewGroup parent) {
        super(parent, R.layout.view_item_holder);
        manager= HttpDownManager.getInstance();
        $(R.id.btn_rx_down).setOnClickListener(this);
        $(R.id.btn_rx_pause).setOnClickListener(this);
        progressBar=$(R.id.number_progress_bar);
        tvMsg=$(R.id.tv_msg);
    }

    @Override
    public void setData(DownInfo data) {
        super.setData(data);
        data.setListener(httpProgressOnNextListener);
        apkApi=data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rx_down:
                manager.startDown(apkApi);
                break;
            case R.id.btn_rx_pause:
                manager.pause(apkApi);
                break;
        }
    }

    /*下载回调*/
    HttpProgressOnNextListener<DownInfo> httpProgressOnNextListener=new HttpProgressOnNextListener<DownInfo>() {
        @Override
        public void onNext(DownInfo baseDownEntity) {
            Toast.makeText(getContext(),baseDownEntity.getSavePath(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStart() {
            tvMsg.setText("提示:开始下载");
        }

        @Override
        public void onComplete() {
            tvMsg.setText("提示：下载完成");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            tvMsg.setText("失败:"+e.toString());
        }


        @Override
        public void onPuase() {
            super.onPuase();
            tvMsg.setText("提示:暂停");
        }

        @Override
        public void onStop() {
            super.onStop();
        }

        @Override
        public void updateProgress(long readLength, long countLength) {
            tvMsg.setText("提示:下载中");
            progressBar.setMax((int) countLength);
            progressBar.setProgress((int) readLength);
        }
    };
}