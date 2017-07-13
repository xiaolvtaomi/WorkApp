package com.mvp.base.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.mvp.base.R;
import com.mvp.base.base.RootView;
import com.mvp.base.model.bean.CommentBean;
import com.mvp.base.presenter.VideoInfoPresenter;
import com.mvp.base.presenter.contract.CommentContract;
import com.mvp.base.ui.adapter.CommentAdapter;
import com.mvp.base.utils.EventUtil;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.ScreenUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Description: 评论列表
 * Creator: yxc
 * date: 2016/9/21 14:57
 */
public class CommentView extends RootView<CommentContract.Presenter> implements CommentContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    TextView tv_empty;


    @BindView(R.id.edt_search)
    EditText edtSearch;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.tv_operate)
    TextView tvOperate;

    CommentAdapter adapter;
    String doodleId ;

    public CommentView(Context context) {
        super(context);
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.fragment_comment_view, this);
    }

    @Override
    protected void initView() {
        recyclerView.setAdapterWithProgress(adapter = new CommentAdapter(mContext));
        recyclerView.setErrorView(R.layout.view_error);
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);
        tv_empty = (TextView) recyclerView.getEmptyView();
        tv_empty.setText("暂无评论");
    }

    @Override
    protected void initEvent() {
        recyclerView.setRefreshListener(this);
        adapter.setError(R.layout.view_error_footer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.resumeMore();
            }
        });
        recyclerView.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.showProgress();
                onRefresh();
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    imgClear.setVisibility(View.VISIBLE);
                    tvOperate.setText("发送");
                } else {
                    imgClear.setVisibility(View.INVISIBLE);
                    tvOperate.setText("取消");
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void refreshFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        recyclerView.showError();
    }


    @Override
    public boolean isActive() {
        return mActive;
    }

    public void clearFooter() {
        adapter.setMore(new View(mContext), this);
        adapter.setError(new View(mContext));
        adapter.setNoMore(new View(mContext));
    }

    @Override
    public void showContent(List<CommentBean> list) {
        adapter.clear();
        if (list != null && list.size() < 30) {
            clearFooter();
        }
        adapter.addAll(list);
    }

    @Override
    public void showMoreContent(List<CommentBean> list) {
        adapter.addAll(list);
    }

    @Override
    public void postCommentResult(boolean result) {
        if(result){
            if(edtSearch != null){
                edtSearch.setText("");
                onRefresh();
            }
        }else{
            showError("提交失败,请重试");
        }
    }

    @Override
    public void setPresenter(CommentContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Subscriber(tag = VideoInfoPresenter.Put_DataId)
    public void setData(String doodleId) {
        this.doodleId = doodleId ;
        mPresenter.setMediaId(doodleId);
        mPresenter.onRefresh();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }



    @OnClick({R.id.tv_operate, R.id.img_clear})
    public void back(View view) {
        switch (view.getId()) {
            case R.id.tv_operate:
                String commentStr = edtSearch.getText().toString();
                if (!TextUtils.isEmpty(commentStr)) {
                    postComment(commentStr);
                } else {
                    showError("请输入评价内容");
                }
                break;
            case R.id.img_clear:
                edtSearch.setText("");
                break;
        }

    }


    public void postComment(String comment){
        CommentBean commentBean = new CommentBean();
        commentBean.setCreatorContent(comment);
        commentBean.setCreatorName(PreUtils.getString(getContext(), "nickname", "匿名"));
        commentBean.setCreatorPic("");
        commentBean.setDoodleId(doodleId);
        commentBean.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        mPresenter.postComment(commentBean);
    }
}
