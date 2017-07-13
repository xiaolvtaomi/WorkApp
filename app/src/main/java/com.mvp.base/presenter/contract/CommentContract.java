package com.mvp.base.presenter.contract;


import com.mvp.base.base.BasePresenter;
import com.mvp.base.base.BaseView;
import com.mvp.base.model.bean.CommentBean;
import com.mvp.base.model.bean.VideoType;

import java.util.List;

/**
 * Description: CommentContract
 * Creator: yxc
 * date: 2016/10/18 13:21
 */
public interface CommentContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void refreshFaild(String msg);

        void showContent(List<CommentBean> list);

        void showMoreContent(List<CommentBean> list);

        void postCommentResult(boolean result);

    }

    interface Presenter extends BasePresenter {

        void onRefresh();

        void loadMore();

        void setMediaId(String id);

        void postComment(CommentBean commentBean);


    }
}
