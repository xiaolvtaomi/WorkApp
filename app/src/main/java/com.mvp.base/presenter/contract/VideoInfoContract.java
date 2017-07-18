package com.mvp.base.presenter.contract;


import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.VideoRes;

public interface VideoInfoContract {

    interface View extends BaseView<Presenter> {

        void showContent(DoodleBean doodlebean);

        boolean isActive();

        void hidLoading();

    }

    interface Presenter extends BasePresenter {
        void getDetailData(DoodleBean doodlebean);

        void collect();

        void insertRecord();

    }
}
