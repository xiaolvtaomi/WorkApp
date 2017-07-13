package com.mvp.base.ui.activitys;

import android.os.Bundle;

import com.mvp.base.R;
import com.mvp.base.base.BaseActivity;
import com.mvp.base.presenter.WelcomePresenter;
import com.mvp.base.ui.view.WelcomeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome_view)
    WelcomeView welcomeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        unbinder = ButterKnife.bind(this);
        mPresenter = new WelcomePresenter(welcomeView);

    }
}