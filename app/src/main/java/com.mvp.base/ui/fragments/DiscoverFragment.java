package com.mvp.base.ui.fragments;

import android.view.LayoutInflater;

import com.mvp.base.R;
import com.mvp.base.base.BaseFragment;
import com.mvp.base.presenter.DiscoverPresenter;
import com.mvp.base.ui.view.DiscoverView;

import butterknife.BindView;

/**
 * Description:
 * Creator: yxc
 * date: $date $time
 */
public class DiscoverFragment extends BaseFragment {

    @BindView(R.id.three_view)
    DiscoverView threeView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new DiscoverPresenter(threeView);
    }

    @Override
    protected void lazyFetchData() {
        ((DiscoverPresenter) mPresenter).getData();
    }
}
