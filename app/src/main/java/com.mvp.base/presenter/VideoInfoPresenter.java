package com.mvp.base.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.Collection;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.Record;
import com.mvp.base.model.db.RealmHelper;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.VideoInfoContract;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.RxUtil;

import org.simple.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Description: VideoInfoPresenter
 * Creator: yxc
 * date: 2016/9/21 15:35
 */
public class VideoInfoPresenter extends RxPresenter implements VideoInfoContract.Presenter {

    public final static String Refresh_Video_Info = "Refresh_Video_Info";
    public final static String Refresh_Video_Info_RECT = "Refresh_Video_Info_REACT";
    public final static String Put_DataId = "Put_DataId";
    public static final String Refresh_Collection_List = "Refresh_Collection_List";
    public static final String Refresh_History_List = "Refresh_History_List";
    final int WAIT_TIME = 200;
    DoodleBean intentbean;
    String dataId = "";
    String pic = "";

    @NonNull
    final VideoInfoContract.View mView;

    public VideoInfoPresenter(@NonNull VideoInfoContract.View addTaskView, DoodleBean videoInfo) {
        mView = Preconditions.checkNotNull(addTaskView);
        mView.setPresenter(this);
        mView.showContent(videoInfo);
        this.intentbean = videoInfo ;
        this.dataId = videoInfo.getDoodleid();
        this.pic = videoInfo.getImg_small();
        getDetailData(videoInfo);
        setCollectState();
        putMediaId();
    }

    @Override
    public void getDetailData(DoodleBean doodlebean) {




        Subscription rxSubscription = RetrofitHelper.getBmobApis().getDoodleDetail(doodlebean.objectId)
                .compose(RxUtil.<DoodleBean>rxSchedulerHelper())
                .subscribe(new Action1<DoodleBean>() {
                    @Override
                    public void call(DoodleBean doodleBean) {
                        intentbean = doodleBean ;
                        if(mView
                                != null && mView.isActive()){
                            postData();
                            insertRecord();
                        }
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (mView.isActive())
                            mView.hidLoading();
                        mView.showError("数据加载失败");
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        if (mView.isActive())
                            mView.hidLoading();
                    }
                });
        addSubscribe(rxSubscription);


        Map<String, Object> params = new HashMap<>();
        params.put("month", doodlebean.getMonth());
        params.put("day", doodlebean.getDay());
        String json = new Gson().toJson(params) ;

        Subscription rxSubscription_react = RetrofitHelper.getBmobApis().getDoodleReact(json)
                .compose(RxUtil.<BmobHttpResponse<List<DoodleBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<DoodleBean>>handleBmobResult())
                .subscribe(new Action1<List<DoodleBean>>() {
                    @Override
                    public void call(final List<DoodleBean> beans) {
                        if (beans != null) {
                            if (mView.isActive()) {
                                postDataReact(beans);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (mView.isActive())
                            mView.hidLoading();
                        mView.showError("数据加载失败");
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        if (mView.isActive())
                            mView.hidLoading();
                    }
                });
        addSubscribe(rxSubscription_react);
    }

    private void postData() {
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        EventBus.getDefault().post(intentbean, Refresh_Video_Info);
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void postDataReact(final List<DoodleBean> beans){
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        EventBus.getDefault().post(beans, Refresh_Video_Info_RECT);
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void putMediaId() {
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        EventBus.getDefault().post(dataId, Put_DataId);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void collect() {
        if (RealmHelper.getInstance().queryCollectionId(dataId)) {
            RealmHelper.getInstance().deleteCollection(dataId);
            mView.disCollect();
        } else {
            if (intentbean != null) {
                Collection bean = new Collection();
                bean.setId(String.valueOf(dataId));
                bean.setPic(pic);
                bean.setTitle(intentbean.getTitle());
                bean.setTime(System.currentTimeMillis());
                bean.setType(intentbean.getType());
                RealmHelper.getInstance().insertCollection(bean);
                mView.collected();
            }
        }
        //刷新收藏列表
        Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        EventBus.getDefault().post("", Refresh_Collection_List);
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void insertRecord() {
        if (!RealmHelper.getInstance().queryRecordId(dataId)) {
            if (intentbean != null) {
                Record bean = new Record();
                bean.setId(String.valueOf(dataId));
                bean.setPic(pic);
                bean.setTitle(intentbean.getTitle());
                bean.setTime(System.currentTimeMillis());
                RealmHelper.getInstance().insertRecord(bean, MinePresenter.maxSize);
                //刷新收藏列表
                Subscription rxSubscription = Observable.timer(WAIT_TIME, TimeUnit.MILLISECONDS)
                        .compose(RxUtil.<Long>rxSchedulerHelper())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                EventBus.getDefault().post("", Refresh_History_List);
                            }
                        });
                addSubscribe(rxSubscription);
            }
        }
    }

    private void setCollectState() {
        if (RealmHelper.getInstance().queryCollectionId(dataId)) {
            mView.collected();
        } else {
            mView.disCollect();
        }
    }
}
