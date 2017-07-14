package com.mvp.base.ui.activitys;


import com.mvp.base.R;
import com.mvp.base.base.NewSwipeBackActivity;
import com.mvp.base.presenter.VideoListPresenter;
import com.mvp.base.ui.view.WorkmateListView;

import butterknife.BindView;

public class WorkmateListActivity extends NewSwipeBackActivity {

    String mTitle = "";
    int mCatalogId = 0;
    @BindView(R.id.video_list_view)
    WorkmateListView workmateListView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_workmate_list);
//        unbinder = ButterKnife.bind(this);
//
//    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_workmate_list;
    }

    @Override
    protected void onBaseCreate() {
        getIntentData();
        mPresenter = new VideoListPresenter(workmateListView, mCatalogId);
    }

    private void getIntentData() {
        mCatalogId = getIntent().getIntExtra("catalogId", 0);
        mTitle = getIntent().getStringExtra("title");
        workmateListView.setTitleName(mTitle);
    }
}
