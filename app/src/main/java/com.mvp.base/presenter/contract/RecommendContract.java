package com.mvp.base.presenter.contract;


import android.content.Context;

import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.VideoRes;

import java.util.List;

/**
 * Description: RecommendContract
 * Creator: yxc
 * date: 2016/9/21 15:53
 */
public interface RecommendContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showContent(List<DoodleBean> doodles);

        void showMoreContent(List<DoodleBean> doodles);

        void refreshFaild(String msg);

        void stopBanner(boolean stop);
    }

    interface Presenter extends BasePresenter {
        void onRefresh();

        void loadMore();

        void refreshNick(Context context);
    }
}
