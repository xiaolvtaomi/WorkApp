package com.mvp.base.ui.fragments;

import android.view.LayoutInflater;

import com.mvp.base.R;
import com.mvp.base.base.BaseFragment;
import com.mvp.base.presenter.ClassificationPresenter;
import com.mvp.base.ui.view.ClassificationView;

import butterknife.BindView;

/**
 * Description: ClassificationFragment
 * Creator: yxc
 * date: 2016/9/21 17:45
 */
public class ClassificationFragment extends BaseFragment {
    public static final String REFRESH_CELL = "REFRESH_CELL";

    @BindView(R.id.two_view)
    ClassificationView twoView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new ClassificationPresenter(twoView);
    }

    @Override
    protected void lazyFetchData() {
        ((ClassificationPresenter) mPresenter).onRefresh();
    }
}
