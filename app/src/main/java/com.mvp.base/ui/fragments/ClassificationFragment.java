package com.mvp.base.ui.fragments;

import android.view.LayoutInflater;

import com.mvp.base.R;
import com.mvp.base.base.BaseFragment;
import com.mvp.base.ui.view.TongjiInfoView;

import butterknife.BindView;


public class ClassificationFragment extends BaseFragment {
    public static final String REFRESH_CELL = "REFRESH_CELL";

    @BindView(R.id.two_view)
    TongjiInfoView twoView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initView(LayoutInflater inflater) {

    }

    @Override
    protected void lazyFetchData() {

    }
}
