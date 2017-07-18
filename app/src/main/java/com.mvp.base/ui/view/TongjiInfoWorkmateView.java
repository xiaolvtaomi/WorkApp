package com.mvp.base.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mvp.base.R;
import com.mvp.base.base.RootView;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.TongjiWorkmateBean;
import com.mvp.base.presenter.contract.cook.DillWorkmateContract;
import com.mvp.base.ui.adapter.TongjiWorkmateAdapter;
import com.mvp.base.ui.fragments.ClassificationFragment;
import com.mvp.base.utils.EventUtil;
import com.mvp.base.utils.Preconditions;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 统计,按人统计
 */
public class TongjiInfoWorkmateView extends RootView<DillWorkmateContract.Presenter> implements DillWorkmateContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    TongjiWorkmateAdapter adapter;

    public TongjiInfoWorkmateView(Context context) {
        super(context);
    }

    public TongjiInfoWorkmateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.fragment_tongji_workmate_view, this);
    }

    @Override
    protected void initView() {
        recyclerView.setAdapterWithProgress(adapter = new TongjiWorkmateAdapter(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setErrorView(R.layout.view_error);
//        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
//        itemDecoration.setPaddingEdgeSide(true);
//        itemDecoration.setPaddingStart(true);
//        itemDecoration.setPaddingHeaderFooter(false);
//        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initEvent() {
        recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


            }
        });

        recyclerView.getErrorView().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                onRefresh();
            }
        });
    }


    @Override
    public boolean isActive() {
        return mActive;
    }




    @Override
    public void setPresenter(DillWorkmateContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    List<TongjiWorkmateBean> videoInfos = new ArrayList<>();

    @Override
    public void showContent(final List<TongjiWorkmateBean> beans) {
        if (beans != null) {
            adapter.clear();
            videoInfos.clear();
            videoInfos = beans ;

            adapter.setData(videoInfos);
        }

    }



    @Override
    public void refreshFailed(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        recyclerView.showError();
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }


    @Subscriber(tag = ClassificationFragment.REFRESH_CELL)
    public void refreshCell(String tag){
        mPresenter.onRefresh();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }



}
