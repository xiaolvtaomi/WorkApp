package com.mvp.base.presenter.contract;


import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DillItemBean;

import java.util.List;


public interface ClassificationContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showContent(List<DillItemBean> beans);

        void refreshFaild(String msg);

    }

    interface Presenter extends BasePresenter {
        void onRefresh();
    }
}
