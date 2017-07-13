package com.mvp.base.ui.activitys;


import com.mvp.base.R;
import com.mvp.base.base.NewSwipeBackActivity;
import com.mvp.base.presenter.VideoListPresenter;
import com.mvp.base.ui.view.VideoListView;

import butterknife.BindView;

public class VideoListActivity extends NewSwipeBackActivity {

    String mTitle = "";
    int mCatalogId = 0;
    @BindView(R.id.video_list_view)
    VideoListView videlListView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_list);
//        unbinder = ButterKnife.bind(this);
//
//    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_video_list;
    }

    @Override
    protected void onBaseCreate() {
        getIntentData();
        mPresenter = new VideoListPresenter(videlListView, mCatalogId);
    }

    private void getIntentData() {
        mCatalogId = getIntent().getIntExtra("catalogId", 0);
        mTitle = getIntent().getStringExtra("title");
        videlListView.setTitleName(mTitle);
    }
}
