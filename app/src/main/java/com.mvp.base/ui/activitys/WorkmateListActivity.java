package com.mvp.base.ui.activitys;


import android.os.Bundle;

import com.mvp.base.R;
import com.mvp.base.base.NewSwipeBackActivity;
import com.mvp.base.presenter.VideoListPresenter;
import com.mvp.base.presenter.WorkmatePresenter;
import com.mvp.base.ui.view.WorkmateListView;
import com.mvp.base.ui.view.WorkmateView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkmateListActivity extends NewSwipeBackActivity {

    String mTitle = "";
    int mCatalogId = 0;
    @BindView(R.id.video_list_view)
    WorkmateView workmateListView;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_workmate_list;
    }


    @Override
    protected void onBaseCreate() {
        getIntentData();
        mPresenter = new WorkmatePresenter(workmateListView);
    }

    private void getIntentData() {
//        mCatalogId = getIntent().getIntExtra("catalogId", 0);
//        mTitle = getIntent().getStringExtra("title");
//        workmateListView.setTitleName("sdf");
    }


}
