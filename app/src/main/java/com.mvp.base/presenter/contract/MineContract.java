package com.mvp.base.presenter.contract;


import android.content.Context;

import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.VideoType;

import java.util.List;

/**
 * Description: CollectionContract
 * Creator: cp
 * date: 2016/9/29 12:19
 */
public interface MineContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showContent(List<VideoType> list);

        void postSuccess();
        void postFailed();
    }

    interface Presenter extends BasePresenter {
        void getHistoryData();

        void delAllHistory();

        void updateMyInfo(Context context, String myjsoninfo);
    }
}
