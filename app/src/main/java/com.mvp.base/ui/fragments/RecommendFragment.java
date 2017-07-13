package com.mvp.base.ui.fragments;

import android.view.LayoutInflater;

import com.mvp.base.R;
import com.mvp.base.base.BaseFragment;
import com.mvp.base.presenter.DishPresenter;
import com.mvp.base.presenter.RecommendPresenter;
import com.mvp.base.ui.view.DishView;
import com.mvp.base.ui.view.RecommendView;

import butterknife.BindView;

/**
 * Description: 点菜首页
 */
public class RecommendFragment extends BaseFragment {

    @BindView(R.id.one_view)
    DishView oneView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new DishPresenter(oneView);
    }

    @Override
    protected void lazyFetchData() {
        ((DishPresenter) mPresenter).onRefresh();
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
