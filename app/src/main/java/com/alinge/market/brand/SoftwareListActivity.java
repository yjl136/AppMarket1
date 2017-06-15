package com.alinge.market.brand;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alinge.market.R;
import com.alinge.market.brand.view.DividerItemDecorator;
import com.alinge.market.brand.view.SoftwareAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-01 14:05
 * Describe:
 */

public class SoftwareListActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    public RecyclerView mRecyclerView;
    @BindView(R.id.srf)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private int brandID;
    private String brandImg;
    private SoftwareAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.software_list_layout);
        ButterKnife.bind(this);
        brandID = getIntent().getIntExtra("brandID",1);
        brandImg = getIntent().getStringExtra("brandImg");
        initView();

    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecorator());
        adapter = new SoftwareAdapter(this,brandID);
        mSwipeRefreshLayout.setOnRefreshListener(adapter);
        mRecyclerView.setAdapter(adapter);

    }
}
