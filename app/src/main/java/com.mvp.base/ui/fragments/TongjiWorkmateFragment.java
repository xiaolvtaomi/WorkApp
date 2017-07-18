package com.mvp.base.ui.fragments;

import android.view.LayoutInflater;

import com.mvp.base.R;
import com.mvp.base.base.BaseFragment;
import com.mvp.base.presenter.DillTotalPresenter;
import com.mvp.base.presenter.DillWorkmatePresenter;
import com.mvp.base.ui.view.TongjiInfoDishView;
import com.mvp.base.ui.view.TongjiInfoWorkmateView;

import butterknife.BindView;

/**
 * Description: 统计  安人
 */
public class TongjiWorkmateFragment extends BaseFragment {

    @BindView(R.id.one_view)
    TongjiInfoWorkmateView oneView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_tongji_workmate;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new DillWorkmatePresenter(oneView);
    }

    @Override
    protected void lazyFetchData() {
        ((DillWorkmatePresenter) mPresenter).onRefresh();
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
