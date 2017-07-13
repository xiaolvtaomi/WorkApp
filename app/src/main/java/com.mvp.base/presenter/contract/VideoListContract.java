package com.mvp.base.presenter.contract;


import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.VideoType;

import java.util.List;

/**
 * Description: VideoListContract
 * Creator: yxc
 * date: 2016/9/21 14:59
 */
public interface VideoListContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showTitle(String title);

        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showContent(List<DoodleBean> list);

        void showMoreContent(List<DoodleBean> list);
    }

    interface Presenter extends BasePresenter {

        void onRefresh();

        void loadMore();

    }
}
