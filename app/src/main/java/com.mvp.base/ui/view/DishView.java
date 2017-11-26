package com.mvp.base.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mvp.base.R;
import com.mvp.base.base.RootView;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.WorkmateBean;
import com.mvp.base.presenter.contract.cook.DishContract;
import com.mvp.base.ui.activitys.DishSelectActivity;
import com.mvp.base.ui.activitys.MainActivity;
import com.mvp.base.ui.adapter.DishAdapter;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.widget.theme.ColorTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lml on 17/7/11.
 */

public class DishView extends RootView<DishContract.Presenter> implements DishContract.View , SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.title_name)
    ColorTextView titleName;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    DishAdapter adapter ;

    WorkmateBean[] workmates = null;
    public DishView(Context context) {
        super(context);
    }

    public DishView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setWorkmates(WorkmateBean[] workmates){
        this.workmates = workmates ;
    }


    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.fragment_dish_view, this);
    }

    @Override
    protected void initView() {
        titleName.setText("点菜");
        adapter = new DishAdapter(getContext(), !(mContext instanceof DishSelectActivity));
        recyclerView.setAdapterWithProgress(adapter);
//        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setErrorView(R.layout.view_error);
//        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
//        itemDecoration.setPaddingEdgeSide(false);
//        itemDecoration.setPaddingStart(false);
//        itemDecoration.setPaddingHeaderFooter(false);
//        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initEvent() {
        recyclerView.setRefreshListener(this);

        recyclerView.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                onRefresh();
            }
        });
        statusToNormal();

    }


    @Override
    public boolean isActive() {
        return mActive;
    }

    List<DishBean> dishes = new ArrayList<>();
    @Override
    public void showContent(List<DishBean> cells) {
        dishes = cells ;
        adapter.clear();
        adapter.setData(dishes);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMyDishesContent(List<DillItemBean> beans) {

    }

    @Override
    public void statusToNormal() {
        fab.setImageResource(android.R.drawable.ic_dialog_email);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelectedDishes() != null && adapter.getSelectedDishes().size() > 0 ){
                    if(mContext instanceof DishSelectActivity){
                        if(workmates != null && workmates.length > 0){
                            mPresenter.postDishes(workmates, adapter.getSelectedDishes());
                        }else{
                            Snackbar.make(view, "没有选人,请退回上个界面选择同事", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();
                        }
                    }else{
                        if(workmates == null || workmates.length == 0){
                            workmates = new WorkmateBean[1];
                            WorkmateBean temp = new WorkmateBean();
                            temp.setAvatar(PreUtils.getString(mContext, "avatar", ""));
                            temp.setName(PreUtils.getString(mContext, "name", "未知"));
                            temp.setObjectId(PreUtils.getString(mContext, "objectId", ""));
                            workmates[0] = temp ;
                            mPresenter.postDishes(workmates, adapter.getSelectedDishes());
                        }
                    }
                }else{
                    Snackbar.make(view, "想吃至少选个菜吧", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public void statusToDished(List<DishBean> dishes) {
        if(mContext instanceof DishSelectActivity){
            ((DishSelectActivity)mContext).setResult(Activity.RESULT_OK);
            ((DishSelectActivity)mContext).finish();
        }else {
            // 更新 adapter
            fab.setImageResource(R.drawable.ic_done_white);
            fab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "你已经定过了", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public void statusToClosed() {
        fab.setImageResource(R.drawable.ic_player_close_white);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "点餐时间已经过了", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void postFailed(String reason) {
        Snackbar.make(recyclerView, "提交失败", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    public void postSuc(String result) {
        Snackbar.make(recyclerView, result, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        statusToDished(null);
    }

    @Override
    public void refreshFailed(String reason) {
        if (!TextUtils.isEmpty(reason))
            showError(reason);
        recyclerView.showError();
    }

    @Override
    public void setPresenter(DishContract.Presenter presenter) {
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
