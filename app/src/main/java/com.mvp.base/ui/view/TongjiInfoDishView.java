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
import com.mvp.base.presenter.contract.cook.DillTotalContract;
import com.mvp.base.ui.adapter.TongjiDishAdapter;
import com.mvp.base.ui.fragments.ClassificationFragment;
import com.mvp.base.utils.EventUtil;
import com.mvp.base.utils.Preconditions;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 统计,按菜统计
 */
public class TongjiInfoDishView extends RootView<DillTotalContract.Presenter> implements DillTotalContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    TongjiDishAdapter adapter;

    public TongjiInfoDishView(Context context) {
        super(context);
    }

    public TongjiInfoDishView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.fragment_classification_view, this);
    }

    @Override
    protected void initView() {
        recyclerView.setAdapterWithProgress(adapter = new TongjiDishAdapter(getContext()));
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

        recyclerView.getErrorView().setOnClickListener(new View.OnClickListener() {
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
    public void setPresenter(DillTotalContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    List<DillItemBean> videoInfos = new ArrayList<>();
    @Override
    public void showDishesContent(final List<DillItemBean> beans) {
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
