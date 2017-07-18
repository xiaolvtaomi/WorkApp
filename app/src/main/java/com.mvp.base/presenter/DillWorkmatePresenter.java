package com.mvp.base.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.TongjiWorkmateBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.cook.DillTotalContract;
import com.mvp.base.presenter.contract.cook.DillWorkmateContract;
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

public class DillWorkmatePresenter extends RxPresenter implements DillWorkmateContract.Presenter {
    @NonNull
    DillWorkmateContract.View mView ;
    @Override
    public void onRefresh() {
        getDillItems();
    }

    public DillWorkmatePresenter(@NonNull DillWorkmateContract.View addTaskView){
        this.mView = addTaskView;
        this.mView.setPresenter(this);
        onRefresh();
    }

    @Override
    public void getDillItems() {
        Map<String, Object> params = new HashMap<>();
        int year , month ,day ;
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH)+1;
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        params.put("year", year);
        params.put("month", month);
        params.put("day", day);
        String json = new Gson().toJson(params) ;
        Subscription rxSubscription = RetrofitHelper.getBmobClouds().getTongjiWorkmatesByYMD()
                .compose(RxUtil.<BmobHttpResponse<List<TongjiWorkmateBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<TongjiWorkmateBean>>handleBmobResult())
                .subscribe(new Action1<List<TongjiWorkmateBean>>() {
                    @Override
                    public void call(List<TongjiWorkmateBean> beans) {
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
}
