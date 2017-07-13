package com.mvp.base.ui.activitys;

import android.os.Bundle;

import com.mvp.base.R;
import com.mvp.base.base.SwipeBackActivity;
import com.mvp.base.presenter.CollectionPresenter;
import com.mvp.base.ui.view.CollectionView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 历史界面
 */
public class HistoryActivity extends SwipeBackActivity {

    @BindView(R.id.collect_view)
    CollectionView collectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        unbinder = ButterKnife.bind(this);
        mPresenter = new CollectionPresenter(collectView,1);
    }

}
