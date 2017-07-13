package com.mvp.base.presenter.contract;


import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.VideoInfo;
import com.mvp.base.model.bean.VideoType;

import java.util.List;

/**
 * Description: VideoListContract
 * Creator: zjg
 * date: 2016/10/11 14:59
 */
public interface SearchVideoListContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showContent(List<DoodleBean> list);

        void showMoreContent(List<DoodleBean> list);

        void showRecommend(List<DoodleBean> list);
    }

    interface Presenter extends BasePresenter {

        void onRefresh();

        void loadMore();

        void setSearchKey(String strSearchKey);

    }
}
