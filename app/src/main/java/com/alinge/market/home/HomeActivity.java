package com.alinge.market.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.alinge.market.R;
import com.alinge.market.brand.SoftwareListActivity;
import com.alinge.market.brand.entity.BrandEntity;
import com.alinge.market.common.log.Log;
import com.alinge.market.home.view.HomeAdapter;
import com.alinge.market.home.view.HomeItemDecoration;
import com.alinge.market.http.Api;
import com.alinge.market.view.ItemListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-02-28 10:14
 * Describe:
 */
public class HomeActivity extends AppCompatActivity implements ItemListener.ItemClickListener {
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    private HomeAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        ButterKnife.bind(this);
        initView();
        Call<BrandEntity> call = Api.getBrand(this);
        call.enqueue(new Callback<BrandEntity>() {
            @Override
            public void onResponse(Call<BrandEntity> call, Response<BrandEntity> response) {
                Log.info("response", response.body().getResult().getReturnMessage());
                adapter.setLists(response.body().getList());
            }
            @Override
            public void onFailure(Call<BrandEntity> call, Throwable throwable) {
                Log.error("message:" + throwable.getMessage() + "  cause:" + throwable.getCause());
            }
        });
    }

    /**
     * 初始化view，并设置item监听
     */
    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeAdapter(this);
        mRecyclerView.addItemDecoration(new HomeItemDecoration(adapter));

        mRecyclerView.setAdapter(adapter);
        ItemListener listener = new ItemListener(mRecyclerView);
        listener.setItemClickListener(this);
        mRecyclerView.addOnItemTouchListener(listener);
    }

    @Override
    public void onItemClick(ViewGroup parent, View child, int postion) {
        Log.info("positon:"+postion);
        int id=adapter.getBrandID(postion);
        String brandImg=adapter.getBrandImg(postion);
        Intent intent=new Intent(this, SoftwareListActivity.class);
        if(id != adapter.NOVALID_POSITION){
            intent.putExtra("brandID",id);
        }
        intent.putExtra("brandImg",brandImg);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_anim,R.anim.out_anim);
    }

}
