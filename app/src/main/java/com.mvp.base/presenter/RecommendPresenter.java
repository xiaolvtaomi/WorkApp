package com.mvp.base.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.mvp.base.app.Constants;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.NickBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.RecommendContract;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;

import java.util.List;
import java.util.Random;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: RecommendPresenter
 * Creator: yxc
 * date: 2016/9/21 16:26
 */
public class RecommendPresenter extends RxPresenter implements RecommendContract.Presenter {
    RecommendContract.View mView;
    int page = 1;

    public RecommendPresenter(@NonNull RecommendContract.View oneView) {
        mView = Preconditions.checkNotNull(oneView);
        mView.setPresenter(this);


    }

    @Override
    public void onRefresh() {
        page = 1;
        getPageHomeInfo();
    }

    @Override
    public void loadMore() {
        page++;
        getPageHomeInfo();
    }

    @Override
    public void refreshNick(final Context context) {
        if(TextUtils.isEmpty(PreUtils.getString(context, "nickname", ""))){
            Random random = new Random();

            Subscription rxSubscription = RetrofitHelper.getBmobApis()
                    .getNickRandom(random.nextInt(9999))
                    .compose(RxUtil.<BmobHttpResponse<List<NickBean>>>rxSchedulerHelper())
                    .compose(RxUtil.<List<NickBean>>handleBmobResult())
                    .subscribe(new Action1<List<NickBean>>() {
                        @Override
                        public void call(final List<NickBean> beans) {
                            if (beans != null && beans.size() > 0) {
                                PreUtils.putString(context, "nickname", beans.get(0).getNamechinese());
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

    private void getPageHomeInfo() {



        int limit = Constants.PAGESIZE;
        int skip = (page - 1)*limit;
        Subscription rxSubscription = RetrofitHelper.getBmobApis().getHomePage(limit, skip)
                .compose(RxUtil.<BmobHttpResponse<List<DoodleBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<DoodleBean>>handleBmobResult())
                .subscribe(new Action1<List<DoodleBean>>() {
                    @Override
                    public void call(final List<DoodleBean> res) {

                        if (res != null) {
                            if (mView.isActive()) {
                                if (page == 1) {
                                    mView.showContent(res);
                                } else {
                                    mView.showMoreContent(res);
                                }
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (page > 1) {
                            page--;
                        }
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }

}
