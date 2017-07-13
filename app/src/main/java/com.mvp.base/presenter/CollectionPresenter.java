package com.mvp.base.presenter;

import android.support.annotation.NonNull;

import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.Collection;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.Record;
import com.mvp.base.model.db.RealmHelper;
import com.mvp.base.presenter.contract.CollectionContract;
import com.mvp.base.utils.Preconditions;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: CollectionPresenter
 * Creator: yxc
 * date: 2016/9/29 12:15
 */
public class CollectionPresenter extends RxPresenter implements CollectionContract.Presenter {
    CollectionContract.View mView;
    int type = 0;//收藏:0; 历史:1:

    public CollectionPresenter(@NonNull CollectionContract.View oneView, int type) {
        this.type = type;
        mView = Preconditions.checkNotNull(oneView);
        mView.setPresenter(this);
        if (type == 0) {
            getCollectData();
        } else {
            getRecordData();
        }
    }

    @Override
    public void getCollectData() {
        List<Collection> collections = RealmHelper.getInstance().getCollectionList();
        List<DoodleBean> list = new ArrayList<>();
        DoodleBean videoType;
        for (Collection collection : collections) {
            videoType = new DoodleBean();
            videoType.setTitle(collection.title) ;
            videoType.setImg_small(collection.pic) ;
            videoType.setDoodleid(collection.getId()) ;
            videoType.setType(collection.type);
            list.add(videoType);
        }
        mView.showContent(list);
    }

    @Override
    public void delAllDatas() {
        if (type == 0) {
            RealmHelper.getInstance().deleteAllCollection();
        } else {
            RealmHelper.getInstance().deleteAllRecord();
            EventBus.getDefault().post("", VideoInfoPresenter.Refresh_History_List);
        }
    }

    @Override
    public void getRecordData() {
        List<Record> records = RealmHelper.getInstance().getRecordList();
        List<DoodleBean> list = new ArrayList<>();
        DoodleBean videoType;
        for (Record record : records) {
            videoType = new DoodleBean();
            videoType.setTitle(record.title) ;
            videoType.setImg_small(record.pic) ;
            videoType.setDoodleid(record.getId()) ;
            list.add(videoType);
        }
        mView.showContent(list);
    }

    @Override
    public int getType() {
        return type;
    }
}
