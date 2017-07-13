package com.mvp.base.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
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

    @Override
    public void getDillItems() {
        Map<String, Object> params = new HashMap<>();
        int year , month ,day ;
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        params.put("year", year);
        params.put("month", month);
        params.put("day", day);
        String json = new Gson().toJson(params) ;
        Subscription rxSubscription = RetrofitHelper.getBmobApis().getDillItemsByYMD(json)
                .compose(RxUtil.<BmobHttpResponse<List<DillItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<DillItemBean>>handleBmobResult())
                .subscribe(new Action1<List<DillItemBean>>() {
                    @Override
                    public void call(List<DillItemBean> beans) {
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
