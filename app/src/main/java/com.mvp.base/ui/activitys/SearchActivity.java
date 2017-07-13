package com.mvp.base.ui.activitys;

import com.mvp.base.R;
import com.mvp.base.base.NewSwipeBackActivity;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.presenter.SearchVideoListPresenter;
import com.mvp.base.ui.view.SearchVideoListView;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends NewSwipeBackActivity {

    @BindView(R.id.search_video_info_view)
    SearchVideoListView searchVideoListView;
    List<DoodleBean> list;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_search;
    }

    @Override
    protected void onBaseCreate() {
        getIntentData();
        mPresenter = new SearchVideoListPresenter(searchVideoListView,list);
    }

    private void getIntentData() {
        list = (List<DoodleBean>) getIntent().getSerializableExtra("recommend");
    }
}