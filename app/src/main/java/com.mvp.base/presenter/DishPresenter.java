package com.mvp.base.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.cook.DishContract;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by lml on 17/7/11.
 */

public class DishPresenter extends RxPresenter implements DishContract.Presenter{
    @NonNull
    DishContract.View mView ;

    public DishPresenter(@NonNull DishContract.View addTaskView){
        this.mView = addTaskView;
        this.mView.setPresenter(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        getTodayDishes();
        getMyDish(PreUtils.getString(mView.getContext(), "uid", ""));
    }

    @Override
    public void getTodayDishes() {
        Map<String, Object> params = new HashMap<>();
        int year , month ,day ;
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//        params.put("year", year);
//        params.put("month", month);
//        params.put("day", day);
        params.put("isopen", true);
        String json = new Gson().toJson(params) ;
        Subscription rxSubscription = RetrofitHelper.getBmobApis().getDishesByYMD(json)
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
    public void getMyDish(String uid) {
        if(!TextUtils.isEmpty(uid)) {
            Map<String, Object> params = new HashMap<>();
            int year, month, day;
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            params.put("year", year);
            params.put("month", month);
            params.put("day", day);
            params.put("userid", uid);
            String json = new Gson().toJson(params);
            Subscription rxSubscription = RetrofitHelper.getBmobApis().getDillItemsByYMD_UID(json)

                    .compose(RxUtil.<BmobHttpResponse<List<DillItemBean>>>rxSchedulerHelper())
                    .compose(RxUtil.<List<DillItemBean>>handleBmobResult())
                    .subscribe(new Action1<List<DillItemBean>>() {
                        @Override
                        public void call(List<DillItemBean> beans) {
                            if (beans != null) {
                                if (mView.isActive()) {
                                    mView.showMyDishesContent(beans);
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
    }

    @Override
    public void postDishes(final DillItemBean mDillitembean) {
        Subscription rxSubscription = RetrofitHelper.getBmobApis().postDill(mDillitembean)
                .compose(RxUtil.<BmobHttpResponse>rxSchedulerHelper())
                .subscribe(new Action1<BmobHttpResponse>() {
                    @Override
                    public void call(BmobHttpResponse response) {
                        if (response != null && !TextUtils.isEmpty(response.getObjectId())) {
                            if (mView.isActive()) {
                                mView.postSuc(mDillitembean);
                            }else{
                                mView.postFailed("提交失败");
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.postFailed("提交异常");
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void clickDish(DishBean dish) {

    }
}
