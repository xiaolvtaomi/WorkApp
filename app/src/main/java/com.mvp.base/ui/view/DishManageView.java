package com.mvp.base.ui.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mvp.base.R;
import com.mvp.base.base.RootView;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.presenter.contract.cook.DishManageContract;
import com.mvp.base.ui.activitys.CollectionActivity;
import com.mvp.base.ui.activitys.DishManageActivity;
import com.mvp.base.ui.activitys.HistoryActivity;
import com.mvp.base.ui.adapter.DishAdapter;
import com.mvp.base.ui.adapter.DishManageAdapter;
import com.mvp.base.utils.JumpUtil;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.widget.theme.ColorTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lml on 17/7/11.
 */

public class DishManageView extends RootView<DishManageContract.Presenter> implements DishManageContract.View , SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.title_name)
    ColorTextView titleName;
    @BindView(R.id.rl_collect_clear)
    RelativeLayout rl_add;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    DishManageAdapter adapter ;

    public DishManageView(Context context) {
        super(context);
    }

    public DishManageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.fragment_dishmanage_view, this);
    }

    @Override
    protected void initView() {
        titleName.setText("配菜");
        adapter = new DishManageAdapter(getContext());
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setErrorView(R.layout.view_error);
        rl_add.setVisibility(View.VISIBLE);
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

        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                ArrayList<String> data = new ArrayList<String>();
                for (DishBean bean : adapter.getSelectedDishes().values()){
                    data.add(bean.getObjectId());
                }
                mPresenter.postDishes(data);

            }
        });
    }


    @Override
    public boolean isActive() {
        return mActive;
    }

    List<DishBean> dishes ;
    @Override
    public void showContent(List<DishBean> cells) {
        dishes = cells ;
        adapter.clear();
        adapter.setData(dishes);
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
        if (mContext instanceof DishManageActivity) {
            ((DishManageActivity) mContext).finish();
        }
    }

    @Override
    public void refreshFailed(String reason) {
        if (!TextUtils.isEmpty(reason))
            showError(reason);
        recyclerView.showError();
//        adapter.pauseMore();
    }

    @Override
    public void setPresenter(DishManageContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {

    }


    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }


    @OnClick({R.id.rl_back, R.id.rl_collect_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                if (mContext instanceof DishManageActivity) {
                    ((DishManageActivity) mContext).finish();
                }
                break;
            case R.id.rl_collect_clear:
                JumpUtil.go2DishAddActivity(mContext);
                break;

        }
    }
}
