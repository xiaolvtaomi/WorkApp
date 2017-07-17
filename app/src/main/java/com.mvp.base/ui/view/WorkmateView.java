package com.mvp.base.ui.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mvp.base.R;
import com.mvp.base.base.RootView;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.WorkmateBean;
import com.mvp.base.presenter.contract.cook.DishContract;
import com.mvp.base.presenter.contract.cook.WorkmateContract;
import com.mvp.base.ui.adapter.DishAdapter;
import com.mvp.base.ui.adapter.VideoListAdapter;
import com.mvp.base.ui.adapter.WorkmateAdapter;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.widget.theme.ColorTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lml on 17/7/11.
 */

public class WorkmateView extends RootView<WorkmateContract.Presenter> implements WorkmateContract.View , SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.title_name)
    ColorTextView titleName;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    WorkmateAdapter adapter ;

    public WorkmateView(Context context) {
        super(context);
    }

    public WorkmateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.activity_workmate_list_view, this);
    }

    @Override
    protected void initView() {
        titleName.setText("选同事");
        adapter = new WorkmateAdapter(getContext());
        recyclerView.setAdapterWithProgress(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(3));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setErrorView(R.layout.view_error);

//        adapter.setMore(R.layout.view_more, this);
//        adapter.setNoMore(R.layout.view_nomore);

//        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
//        itemDecoration.setPaddingEdgeSide(false);
//        itemDecoration.setPaddingStart(false);
//        itemDecoration.setPaddingHeaderFooter(false);
//        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initEvent() {
        recyclerView.setRefreshListener(this);

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

    List<WorkmateBean> workmates = new ArrayList<>();
    @Override
    public void showContent(List<WorkmateBean> cells) {
        workmates = cells ;
        adapter.clear();
        adapter.setData(workmates);
//        adapter.notifyDataSetChanged();
    }


    @Override
    public void postFailed(String reason) {
        Snackbar.make(recyclerView, "提交失败", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    public void postSuc() {
        Snackbar.make(recyclerView, "提交成功", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();

    }

    @Override
    public void refreshFailed(String reason) {
        if (!TextUtils.isEmpty(reason))
            showError(reason);
        recyclerView.showError();
//        adapter.pauseMore();
    }

    @Override
    public void setPresenter(WorkmateContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {

    }


    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }


}
