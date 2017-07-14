package com.mvp.base.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.mvp.base.R;
import com.mvp.base.base.RootView;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.VideoInfo;
import com.mvp.base.presenter.contract.VideoListContract;
import com.mvp.base.ui.activitys.WorkmateListActivity;
import com.mvp.base.ui.adapter.VideoListAdapter;
import com.mvp.base.utils.EventUtil;
import com.mvp.base.utils.JumpUtil;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.ScreenUtil;
import com.mvp.base.widget.theme.ColorTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Description: 影片列表
 * Creator: yxc
 * date: 2016/9/21 14:57
 */
public class WorkmateListView extends RootView<VideoListContract.Presenter> implements VideoListContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title_name)
    ColorTextView mTitleName;

    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    VideoListAdapter mAdapter;

    VideoInfo videoInfo;
    int pageSize = 30;

    public WorkmateListView(Context context) {
        super(context);
    }

    public WorkmateListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.activity_workmate_list_view, this);
    }

    @Override
    protected void initView() {
        mRecyclerView.setAdapterWithProgress(mAdapter = new VideoListAdapter(mContext));
        mRecyclerView.setErrorView(R.layout.view_error);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(2));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(mContext, 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initEvent() {
        mTitleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EventUtil.isFastDoubleClick()) {
                    mRecyclerView.scrollToPosition(0);
                }
            }
        });
        mRecyclerView.setRefreshListener(this);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                JumpUtil.go2DoodleInfoActivity(getContext(), mAdapter.getItem(position));
            }
        });
        mAdapter.setError(R.layout.view_error_footer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.resumeMore();
            }
        });
        mRecyclerView.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.showProgress();
                onRefresh();
            }
        });
    }

    @OnClick(R.id.rl_back)
    public void back() {
        if (mContext instanceof WorkmateListActivity) {
            ((WorkmateListActivity) mContext).finish();
        }
    }

    @Override
    public void showTitle(String title) {
        mTitleName.setText(title);
    }

    @Override
    public void refreshFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        mRecyclerView.showError();
    }

    @Override
    public void loadMoreFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        mAdapter.pauseMore();
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    public void clearFooter() {
        mAdapter.setMore(new View(mContext), this);
        mAdapter.setError(new View(mContext));
        mAdapter.setNoMore(new View(mContext));
    }

    @Override
    public void showContent(List<DoodleBean> list) {
        mAdapter.clear();
        if (list != null && list.size() < pageSize) {
            clearFooter();
        }
        mAdapter.addAll(list);
    }

    @Override
    public void showMoreContent(List<DoodleBean> list) {
        mAdapter.addAll(list);
    }

    @Override
    public void setPresenter(VideoListContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    public void setTitleName(String title) {
        mTitleName.setText(title);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }
}
