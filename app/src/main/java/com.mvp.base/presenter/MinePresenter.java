package com.mvp.base.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.Record;
import com.mvp.base.model.bean.VideoType;
import com.mvp.base.model.db.RealmHelper;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.MineContract;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: CollectionPresenter
 * Creator: cp
 * date: 2016/9/29 12:19
 */
public class MinePresenter extends RxPresenter implements MineContract.Presenter {
    MineContract.View mView;
    public static final int maxSize = 30;

    public MinePresenter(@NonNull MineContract.View oneView) {
        mView = Preconditions.checkNotNull(oneView);
        mView.setPresenter(this);
        getHistoryData();
    }

    @Override
    public void getHistoryData() {
        List<Record> records = RealmHelper.getInstance().getRecordList();
        List<VideoType> list = new ArrayList<>();
        VideoType videoType;
        int maxlinth = records.size() <= 3 ? records.size() : 3;
        for (int i = 0; i < maxlinth; i++) {
            Record record = records.get(i);
            videoType = new VideoType();
            videoType.title = record.title;
            videoType.pic = record.pic;
            videoType.dataId = record.getId();
            list.add(videoType);
        }
        mView.showContent(list);
    }

    @Override
    public void delAllHistory() {
        RealmHelper.getInstance().deleteAllRecord();
    }

    @Override
    public void updateMyInfo(Context context, String myjsoninfo) {
        String objectId = PreUtils.getString(context, "objectId", "") ;

        Subscription rxSubscription =
                RetrofitHelper.getBmobApis().updateMyInfo(
                        objectId, myjsoninfo)
                        .compose(RxUtil.<BmobHttpResponse>rxSchedulerHelper())
                        .subscribe(new Action1<BmobHttpResponse>() {
                            @Override
                            public void call(final BmobHttpResponse res) {
                                mView.postSuccess();
                                System.out.println("提交成功");

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mView.postFailed();
                                System.out.println("提交失败");
                            }
                        });
        addSubscribe(rxSubscription);
    }
}
