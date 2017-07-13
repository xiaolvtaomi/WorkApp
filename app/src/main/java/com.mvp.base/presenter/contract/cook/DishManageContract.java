package com.mvp.base.presenter.contract.cook;

import android.content.Context;

import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lml on 17/7/7.
 */

public interface DishManageContract {

    interface View extends BaseView<DishManageContract.Presenter> {

        boolean isActive();

        Context getContext();

        /**
         * 显示今天所有菜品
         * @param cells
         */
        void showContent(List<DishBean> cells);

        void onRefresh();

        void postFailed(String reason);

        void postSuc(DillItemBean postedBean);

        void refreshFailed(String reason);

    }

    interface Presenter extends BasePresenter {

        void onRefresh();

        /**
         * 全部菜单
         */
        void getAllDishes();

        /**
         * 提交我的菜
         */
        void postDishes(ArrayList<DishBean> selectedDishes);

    }


}
