package com.mvp.base.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.CommentBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.CommentContract;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action1;

import static com.mvp.base.utils.RxUtil.handleBmobResult;

public class CommentPresenter extends RxPresenter implements CommentContract.Presenter {
    @NonNull
    final CommentContract.View mView;
    int page = 1;
    String doodleId = "";


    public CommentPresenter(@NonNull CommentContract.View addTaskView) {
        mView = Preconditions.checkNotNull(addTaskView);
        mView.setPresenter(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page = 1;
        if (doodleId != null && !doodleId.equals("")) {
            getComment(doodleId);
        }
    }

    private void getComment(String doodleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("doodleId", doodleId);
        String json = new Gson().toJson(params) ;
        Subscription rxSubscription = RetrofitHelper.getBmobApis().getCommentsByDoodleid(json, page*100, (page-1)*100)
                .compose(RxUtil.<BmobHttpResponse<List<CommentBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<CommentBean>>handleBmobResult())
                .subscribe(new Action1<List<CommentBean>>() {
                    @Override
                    public void call(List<CommentBean> comments) {
                        if (comments != null) {
                            if (mView.isActive()) {
                                if (page == 1) {
                                    mView.showContent(comments);
                                } else {
                                    mView.showMoreContent(comments);
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

    @Override
    public void loadMore() {
        page++;
        if (doodleId != null && doodleId.equals("")) {
            getComment(doodleId);
        }
    }

    @Override
    public void setMediaId(String doodleId) {
        this.doodleId = doodleId;
    }

    @Override
    public void postComment(CommentBean commentBean) {
        Subscription rxSubscription = RetrofitHelper.getBmobApis().postComment(commentBean)
                .compose(RxUtil.<BmobHttpResponse>rxSchedulerHelper())
                .subscribe(new Action1<BmobHttpResponse>() {
                    @Override
                    public void call(BmobHttpResponse response) {
                        if (response != null && !TextUtils.isEmpty(response.getObjectId())) {
                            if (mView.isActive()) {
                                mView.postCommentResult(true);
                            }else{
                                mView.postCommentResult(false);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
        addSubscribe(rxSubscription);
    }

}
