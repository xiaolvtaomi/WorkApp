package com.mvp.base.presenter.contract;


import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.VideoType;

import java.util.List;

/**
 * Description: CollectionContract
 * Creator: yxc
 * date: 2016/9/29 12:06
 */
public interface CollectionContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showContent(List<DoodleBean> list);

    }

    interface Presenter extends BasePresenter {
        void getCollectData();

        void delAllDatas();

        void getRecordData();

        int getType();

    }
}
