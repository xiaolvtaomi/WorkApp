package com.mvp.base.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.cook.DishManageContract;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by lml on 17/7/11.
 */

public class DishManagePresenter extends RxPresenter implements DishManageContract.Presenter{
    @NonNull
    DishManageContract.View mView ;

    public DishManagePresenter(@NonNull DishManageContract.View addTaskView){
        this.mView = addTaskView;
        this.mView.setPresenter(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        getAllDishes();
    }

    @Override
    public void getAllDishes() {
        Map<String, Object> params = new HashMap<>();

        String json = new Gson().toJson(params) ;
        Subscription rxSubscription = RetrofitHelper.getBmobApis().getAllDishes()
                .compose(RxUtil.<BmobHttpResponse<List<DishBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<DishBean>>handleBmobResult())
                .subscribe(new Action1<List<DishBean>>() {
                    @Override
                    public void call(List<DishBean> beans) {
                        if (beans != null) {
                            if (mView.isActive()) {
                                mView.showContent(beans);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFailed(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void postDishes(ArrayList<DishBean> selectedDishes) {
//        Subscription rxSubscription = RetrofitHelper.getBmobApis().postDill(mDillitembean)
//                .compose(RxUtil.<BmobHttpResponse>rxSchedulerHelper())
//                .subscribe(new Action1<BmobHttpResponse>() {
//                    @Override
//                    public void call(BmobHttpResponse response) {
//                        if (response != null && !TextUtils.isEmpty(response.getObjectId())) {
//                            if (mView.isActive()) {
//                                mView.postSuc(mDillitembean);
//                            }else{
//                                mView.postFailed("提交失败");
//                            }
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        mView.postFailed("提交异常");
//                    }
//                });
//        addSubscribe(rxSubscription);
    }


}
