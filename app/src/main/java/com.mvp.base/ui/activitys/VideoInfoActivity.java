package com.mvp.base.ui.activitys;

import android.os.Bundle;


import com.mvp.base.R;
import com.mvp.base.base.SwipeBackActivity;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.presenter.VideoInfoPresenter;
import com.mvp.base.ui.view.VideoInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoInfoActivity extends SwipeBackActivity {

    DoodleBean videoInfo;
    @BindView(R.id.video_info_view)
    VideoInfoView videoInfoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_info);
        unbinder = ButterKnife.bind(this);
        getIntentData();
        mPresenter = new VideoInfoPresenter(videoInfoView, videoInfo);
    }

    private void getIntentData() {
        videoInfo = (DoodleBean) getIntent().getSerializableExtra("doodleinfo");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
//        if (JCVideoPlayer.backPress()) {
//            return;
//        }
        super.onBackPressed();
    }
}