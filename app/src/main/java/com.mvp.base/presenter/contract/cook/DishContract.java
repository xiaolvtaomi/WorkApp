package com.mvp.base.presenter.contract.cook;

import android.content.Context;

import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.WorkmateBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lml on 17/7/7.
 */

public interface DishContract {

    interface View extends BaseView<DishContract.Presenter> {

        boolean isActive();

        Context getContext();

        /**
         * 显示今天所有菜品
         * @param cells
         */
        void showContent(List<DishBean> cells);

        /**
         * 显示我已经点的菜品,跳转新页面显示
         * @param beans
         */
        void showMyDishesContent(List<DillItemBean> beans);

        /**
         * 可以点
         */
        void statusToNormal();

        /**
         * 已经点过了
         * @param dishes
         */
        void statusToDished(List<DishBean> dishes);

        /**
         * 没有点过同时也不能点
         */
        void statusToClosed();

        void onRefresh();


        void postFailed(String reason);

        void postSuc(String result);

        void refreshFailed(String reason);

    }

    interface Presenter extends BasePresenter {

        void onRefresh();

        /**
         * 今天菜单
         */
        void getTodayDishes();

        /**
         * 我点的菜
         */
        void getMyDish(String uid);

        /**
         * 提交我的菜
         */
        void postDishes(WorkmateBean[] workmateBeens,  Collection<DishBean> dishes);

        /**
         * 点选菜
         * @param dish
         */
        void clickDish(DishBean dish);
    }


}
