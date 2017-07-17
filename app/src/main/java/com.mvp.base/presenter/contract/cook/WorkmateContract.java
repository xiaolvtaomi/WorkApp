package com.mvp.base.presenter.contract.cook;

import android.content.Context;

import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.WorkmateBean;

import java.util.Collection;
import java.util.List;

/**
 * Created by lml on 17/7/7.
 */

public interface WorkmateContract {

    interface View extends BaseView<WorkmateContract.Presenter> {

        boolean isActive();

        Context getContext();

        /**
         * 显示所有同事
         */
        void showContent(List<WorkmateBean> cells);

        void onRefresh();

        void postFailed(String reason);

        void postSuc();

        void refreshFailed(String reason);

    }

    interface Presenter extends BasePresenter {

        void onRefresh();

        void getUnDishedWorkmates();

        void postWorkmates(Collection<WorkmateBean> workmates);

        void clickWorkmate(WorkmateBean workmate);
    }


}
