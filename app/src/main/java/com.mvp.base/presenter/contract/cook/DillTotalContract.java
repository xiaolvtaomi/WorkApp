package com.mvp.base.presenter.contract.cook;

import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.WorkmateBean;

import java.util.List;

/**
 * Created by lml on 17/7/7.
 */

public interface DillTotalContract {


    interface View extends BaseView<DillTotalContract.Presenter> {

        boolean isActive();

        void refreshFailed(String reason);

        void showDishesContent(List<DillItemBean> mDillItemBeans);

    }

    interface Presenter extends BasePresenter {

        void onRefresh();

        void getDillItems();

    }

}
