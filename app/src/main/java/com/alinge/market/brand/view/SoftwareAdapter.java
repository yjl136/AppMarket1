package com.alinge.market.brand.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alinge.market.R;
import com.alinge.market.common.entity.AppEntity;
import com.alinge.market.common.entity.SoftwareListEntity;
import com.alinge.market.common.log.Log;
import com.alinge.market.http.Api;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-01 16:54
 * Describe:
 */

public class SoftwareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SwipeRefreshLayout.OnRefreshListener {

    private final int STATE_LOADING = 1;
    private final int STATE_SUCESS = 2;
    private final int STATE_ERROR = 3;
    private List<AppEntity> apps;
    private Context context;
    private LayoutInflater inflater;
    private final int HEADER_COUNT = 0;
    private final int FOOTER_COUNT = 1;
    private final int ITEM_TYPE_MESSAGE = 0;
    private final int ITEM_TYPE_HEADER = 1;
    private final int ITEM_TYPE_CONTENT = 2;
    private final int ITEM_TYPE_FOOTER = 3;
    private int brandId;
    private int pageIndex;
    private int pageSize = 10;
    private String brandImg;
    private Resources res;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    //共有多少条
    private double totalCount;
    private boolean isComplete = true;
    private int state;

    public SoftwareAdapter(Context context, int brandId) {
        this(context, 10, brandId);
    }

    private SoftwareAdapter(Context context, int pageSize, int brandId) {
        this.context = context;
        this.apps = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.brandId = brandId;
        this.pageIndex = 1;
        this.res = context.getResources();
        this.pageSize = pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setApps(List<AppEntity> apps) {
        this.apps = apps;
        notifyDataSetChanged();
    }

    /**
     * 向集合里添加新加载的数据
     *
     * @param news
     */
    public void appendApps(List<AppEntity> news) {
        if (news != null && news.size() > 0) {
            if (pageIndex == 1) {
                apps.clear();
            }
            apps.addAll(news);
        }
        notifyDataSetChanged();
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (apps.size() <= 0) {
            return 1;
        }
        if (apps.size() < 8) {
            return apps.size() + HEADER_COUNT;
        }
        return apps.size() + HEADER_COUNT + FOOTER_COUNT;
    }

    public boolean isHeaderView(int position) {
        if (getItemViewType(position) == ITEM_TYPE_HEADER) {
            return true;
        }
        return false;
    }

    public boolean isFooterView(int position) {
        if (getItemViewType(position) == ITEM_TYPE_FOOTER) {
            return true;
        }
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == 1) {
            return ITEM_TYPE_MESSAGE;
        } else if (HEADER_COUNT != 0 && position < HEADER_COUNT) {
            return ITEM_TYPE_HEADER;
        } else if (FOOTER_COUNT != 0 && position >= (apps.size() + HEADER_COUNT)) {
            return ITEM_TYPE_FOOTER;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        View view = null;
        if (viewType == ITEM_TYPE_HEADER) {
            view = inflater.inflate(R.layout.item_header, parent, false);
            vh = new HeaderViewHolder(view);
        } else if (viewType == ITEM_TYPE_FOOTER) {
            view = inflater.inflate(R.layout.item_footer, parent, false);
            vh = new FooterViewHolder(view);
        } else if (viewType == ITEM_TYPE_MESSAGE) {
            view = inflater.inflate(R.layout.item_message, parent, false);
            vh = new MessageViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.item_software, parent, false);
            vh = new ContentViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == ITEM_TYPE_CONTENT) {
            position = position - HEADER_COUNT;
            AppEntity app = apps.get(position);
            String url = app.getAppIcon();
            ContentViewHolder vh = (ContentViewHolder) holder;
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.icon_empty)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(vh.iv);
            vh.appnameTv.setText(text(R.string.appname, app.getSoftwareName()));
            vh.versionTv.setText(text(R.string.version, app.getVersion()));
            vh.filesizeTv.setText(text(R.string.filesize, app.getFileSize()));
        } else if (viewType == ITEM_TYPE_HEADER) {
            HeaderViewHolder vh = (HeaderViewHolder) holder;
            Glide.with(context)
                    .load(brandImg)
                    .placeholder(R.drawable.icon_empty)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(vh.brandIv);
        } else if (viewType == ITEM_TYPE_MESSAGE) {
            MessageViewHolder vh = (MessageViewHolder) holder;
            if (state==STATE_LOADING) {
                vh.messageTv.setText("努力加载数据中...");
            } else if(state==STATE_SUCESS){
                vh.messageTv.setText("暂时不包含数据，下拉刷新试一试吧");
            }else if(state==STATE_ERROR){
                vh.messageTv.setText("访问异常,请下拉刷新试一试吧...");
            }
        } else {
            FooterViewHolder vh = (FooterViewHolder) holder;
            if (apps.size() >= totalCount) {
                vh.loadTv.setText("已经没有数据啦!");
            } else {
                pageIndex++;
                vh.loadTv.setText("努力加载数据中...");
                loadData(pageIndex, brandId);
            }
        }
    }

    public CharSequence text(int id, String content) {
        String src = String.format(res.getString(id), content);
        int index = src.indexOf("：");
        SpannableString spannable = new SpannableString(src);
        spannable.setSpan(new AbsoluteSizeSpan(10, true), 0, index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) mRecyclerView.getParent();

      loadFirstPage();
    }

    public void loadData(final int pageIndex, int brandId) {
        Observable<SoftwareListEntity> observable = Api.getSoftListByBrand(context, pageIndex, pageSize, brandId);
        observable.delay(pageIndex==1?1000:500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SoftwareListEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.info("onCompleted");
                        state = STATE_SUCESS;
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.info("onError");
                        if (SoftwareAdapter.this.pageIndex > 1) {
                            SoftwareAdapter.this.pageIndex--;
                        }
                        if (pageIndex == 1) {
                            isComplete = false;
                            appendApps(null);
                        }
                        state = STATE_ERROR;
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(SoftwareListEntity entity) {
                        if (entity != null) {
                            if (entity.getResult().getReturnCode() >= 0) {
                                totalCount = entity.getResult().getReturnCode();
                                appendApps(entity.getList());
                            } else {
                                onError(null);
                            }
                        } else {
                            onError(null);
                        }
                    }
                });
    }


    static class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        public ImageView iv;
        @BindView(R.id.appnameTv)
        public TextView appnameTv;
        @BindView(R.id.versionTv)
        public TextView versionTv;
        @BindView(R.id.filesizeTv)
        public TextView filesizeTv;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.brandIv)
        public ImageView brandIv;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.loadTv)
        public TextView loadTv;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.messageTv)
        public TextView messageTv;

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        loadFirstPage();
    }

    private void loadFirstPage() {
        pageIndex = 1;
        state = STATE_LOADING;
        loadData(pageIndex, brandId);
    }
}
