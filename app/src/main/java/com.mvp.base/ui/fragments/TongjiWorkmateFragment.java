package com.mvp.base.ui.fragments;

import android.view.LayoutInflater;

import com.mvp.base.R;
import com.mvp.base.base.BaseFragment;
import com.mvp.base.presenter.DillTotalPresenter;
import com.mvp.base.ui.view.TongjiInfoDishView;

import butterknife.BindView;

/**
 * Description: 详情--评论
 * Creator: yxc
 * date: 2016/9/9 9:54
 */
public class TongjiWorkmateFragment extends BaseFragment {

    @BindView(R.id.one_view)
    TongjiInfoDishView oneView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_tongji_dish;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new DillTotalPresenter(oneView);
    }

    @Override
    protected void lazyFetchData() {
        ((DillTotalPresenter) mPresenter).onRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
