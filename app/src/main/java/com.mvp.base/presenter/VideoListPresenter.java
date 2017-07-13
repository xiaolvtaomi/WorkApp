package com.mvp.base.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.mvp.base.app.Constants;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.VideoListContract;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action1;

public class VideoListPresenter extends RxPresenter implements VideoListContract.Presenter {
    @NonNull
    final VideoListContract.View mView;
    int page = 1;
    int catalogId = 0;


    public VideoListPresenter(@NonNull VideoListContract.View addTaskView, int catalogId) {
        mView = Preconditions.checkNotNull(addTaskView);
        mView.setPresenter(this);
        this.catalogId = catalogId;
        params.put("collectid", catalogId);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page = 1;
        getVideoList(catalogId);
    }
    Map<String, Object> params = new HashMap<>();

    private void getVideoList(int catalogID) {


        int limit = Constants.PAGESIZE;
        int skip = (page - 1)*limit;

        String json = new Gson().toJson(params) ;

        Subscription rxSubscription = RetrofitHelper.getBmobApis().getDoodleByCollectid(json, limit, skip)
                .compose(RxUtil.<BmobHttpResponse<List<DoodleBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<DoodleBean>>handleBmobResult())
                .subscribe(new Action1<List<DoodleBean>>() {

                    @Override
                    public void call(List<DoodleBean> beans) {
                        if (beans != null) {
                            if (mView.isActive()) {
                                if (page == 1) {
                                    mView.showContent(beans);
                                } else {
                                    mView.showMoreContent(beans);
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

    /**
     * 搜索电影
     *
     * @param searchStr
     */
    private void getSearchVideoList(String searchStr) {
//        Subscription rxSubscription = RetrofitHelper.getVideoApi().getVideoListByKeyWord(searchStr, page + "")
//                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
//                .compose(RxUtil.<VideoRes>handleResult())
//                .subscribe(new Action1<VideoRes>() {
//                    @Override
//                    public void call(VideoRes res) {
//                        if (res != null) {
//                            if (mView.isActive()) {
//                                if (page == 1) {
//                                    mView.showContent(res.list);
//                                } else {
//                                    mView.showMoreContent(res.list);
//                                }
//                            }
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        if (page > 1) {
//                            page--;
//                        }
//                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
//                    }
//                });
//        addSubscribe(rxSubscription);
    }

    @Override
    public void loadMore() {
        page++;
        getVideoList(catalogId);
    }

}
