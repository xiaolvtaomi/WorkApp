package com.mvp.base.presenter.contract;


import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.Cell;
import com.mvp.base.model.bean.CollectionBean;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishTongjiBean;
import com.mvp.base.model.bean.VideoRes;
import com.mvp.base.model.bean.VideoType;

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
