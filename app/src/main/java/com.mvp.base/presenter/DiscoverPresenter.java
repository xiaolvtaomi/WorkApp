package com.mvp.base.presenter;

import android.support.annotation.NonNull;

import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.DiscoverContract;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;
import com.mvp.base.utils.SystemUtils;

import java.util.List;
import java.util.Random;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Description: DiscoverPresenter
 * Creator: yxc
 * date: 2016/9/21 17:55
 */
public class DiscoverPresenter extends RxPresenter implements DiscoverContract.Presenter {
    DiscoverContract.View mView;

    int max = 5;
    int min = 1;


    public DiscoverPresenter(@NonNull DiscoverContract.View threeView) {
        mView = Preconditions.checkNotNull(threeView);
        mView.setPresenter(this);
    }

    @Override
    public void getData() {
        getNextVideos();
    }

    private void getNextVideos() {
        Random random = new Random();

        Subscription rxSubscription = RetrofitHelper.getBmobApis().getDoodleRandom(random.nextInt(120)*25)
                .compose(RxUtil.<BmobHttpResponse<List<DoodleBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<DoodleBean>>handleBmobResult())
                .subscribe(new Action1<List<DoodleBean>>() {
                               @Override
                               public void call(final List<DoodleBean> beans) {
                                   if (beans != null) {
                                       if (mView.isActive()) {
                                           mView.showContent(beans);
                                       }
                                   }
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {

                                   mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                               }
                           }, new Action0() {
                               @Override
                               public void call() {
                                   if (mView.isActive())
                                       mView.hidLoading();
                               }
                           }
                );

        addSubscribe(rxSubscription);
    }


    private int getNextPage() {
        int page = mView.getLastPage();
        if (SystemUtils.isNetworkConnected()) {
            page = StringUtils.getRandomNumber(min, max);
            mView.setLastPage(page);
        }
        return page;
    }
}
