package com.mvp.base.ui.fragments;

import android.view.LayoutInflater;

import com.mvp.base.R;
import com.mvp.base.base.BaseFragment;
import com.mvp.base.presenter.CommentPresenter;
import com.mvp.base.ui.view.CommentView;

import butterknife.BindView;

/**
 * Description: 详情--评论
 * Creator: yxc
 * date: 2016/9/9 9:54
 */
public class VideoCommentFragment extends BaseFragment {

    @BindView(R.id.comment_view)
    CommentView commentView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        mPresenter = new CommentPresenter(commentView);
    }

    @Override
    protected void lazyFetchData() {
        ((CommentPresenter) mPresenter).onRefresh();
    }

}
