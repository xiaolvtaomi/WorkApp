package com.mvp.base.ui.activitys;

import android.os.Bundle;

import com.mvp.base.R;
import com.mvp.base.base.SwipeBackActivity;
import com.mvp.base.presenter.WelfarePresenter;
import com.mvp.base.ui.view.WelfareView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareActivity extends SwipeBackActivity {

    @BindView(R.id.welfare_view)
    WelfareView welfareView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);
        unbinder = ButterKnife.bind(this);
        mPresenter = new WelfarePresenter(welfareView);
    }

}
