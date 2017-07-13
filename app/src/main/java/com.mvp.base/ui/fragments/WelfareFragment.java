package com.mvp.base.ui.fragments;

import android.view.LayoutInflater;

import com.mvp.base.R;
import com.mvp.base.base.BaseFragment;
import com.mvp.base.presenter.WelfarePresenter;
import com.mvp.base.ui.view.WelfareView;

import butterknife.BindView;

/**
 * Description:
 * Creator: yxc
 * date: $date $time
 */
public class WelfareFragment extends BaseFragment {

    @BindView(R.id.three_view)
    WelfareView threeView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_welfare;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new WelfarePresenter(threeView);
    }


}
