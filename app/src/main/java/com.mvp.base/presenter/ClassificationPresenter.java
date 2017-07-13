package com.mvp.base.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.CollectionBean;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.ClassificationContract;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: ClassificationPresenter
 * Creator: yxc
 * date: 2016/9/21 17:55
 */
public class ClassificationPresenter extends RxPresenter implements ClassificationContract.Presenter {
    ClassificationContract.View mView;
    int page = 0;

    public ClassificationPresenter(@NonNull ClassificationContract.View twoView) {
        mView = Preconditions.checkNotNull(twoView);
        mView.setPresenter(this);

    }

    @Override
    public void onRefresh() {
        page = 0;
        getPageHomeInfo();
    }

    private void getPageHomeInfo() {
//        HttpMethods.getInstance().queryClassification()
//                .subscribe(new MyObserver<VideoRes>() {
//                    @Override
//                    protected void onError(ApiException ex) {
//                        mView.refreshFaild(ex.getDisplayMessage());
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onNext(VideoRes res) {
//                        if (res != null) {
//                            if (mView.isActive()) {
//                                mView.showContent(res);
//                            }
//                        }
//                    }
//                });


        Map<String, Object> params = new HashMap<>();
        int year , month ,day ;
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH)+1;
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        params.put("year", year);
        params.put("month", month);
        params.put("day", 12);
        String json = new Gson().toJson(params) ;
        Subscription rxSubscription = RetrofitHelper.getBmobApis().getDillItemsByYMD(json)
                .compose(RxUtil.<BmobHttpResponse<List<DillItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<DillItemBean>>handleBmobResult())
                .subscribe(new Action1<List<DillItemBean>>() {
                    @Override
                    public void call(final List<DillItemBean> res) {
                        if (res != null) {
                            if (mView.isActive()) {
                                mView.showContent(res);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }
}
