package com.mvp.base.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mvp.base.R;
import com.mvp.base.base.RootView;
import com.mvp.base.component.ImageLoader;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.presenter.contract.VideoInfoContract;
import com.mvp.base.ui.activitys.VideoInfoActivity;
import com.mvp.base.ui.fragments.VideoCommentFragment;
import com.mvp.base.ui.fragments.VideoIntroFragment;
import com.mvp.base.utils.EventUtil;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.ScreenUtil;
import com.mvp.base.widget.LVGhost;
import com.mvp.base.widget.SwipeViewPager;
import com.mvp.base.widget.theme.ColorTextView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Description: VideoInfoView
 * Creator: yxc
 * date: 2016/9/21 15:54
 */
public class VideoInfoView extends RootView<VideoInfoContract.Presenter> implements VideoInfoContract.View {

    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.iv_logo)
    ImageView iv_logo;
    @BindView(R.id.title_name)
    ColorTextView mTitleName;
    @BindView(R.id.viewpagertab)
    SmartTabLayout mViewpagertab;
    @BindView(R.id.viewpager)
    SwipeViewPager mViewpager;
    @BindView(R.id.circle_loading)
    LVGhost mLoading;
    @BindView(R.id.rl_collect)
    RelativeLayout rlCollect;

    DoodleBean mDoodleBean;
    private Animation animation;

    public VideoInfoView(Context context) {
        super(context);
    }

    public VideoInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.activity_video_info_view, this);
    }

    @Override
    protected void initView() {
        animation = AnimationUtils.loadAnimation(mContext, R.anim.view_hand);
        rlCollect.setVisibility(View.VISIBLE);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                ((VideoInfoActivity) mContext).getSupportFragmentManager(), FragmentPagerItems.with(mContext)
                .add(R.string.video_intro, VideoIntroFragment.class)
                .add(R.string.video_comment, VideoCommentFragment.class)
                .create());
        mViewpager.setAdapter(adapter);
        mViewpagertab.setViewPager(mViewpager);
//        iv_logo.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.rl_back)
    public void back() {
        if (mContext instanceof VideoInfoActivity) {
            ((VideoInfoActivity) mContext).finish();
        }
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void hidLoading() {
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void collected() {
        ivCollect.setBackgroundResource(R.mipmap.collection_select);
    }

    @Override
    public void disCollect() {
        ivCollect.setBackgroundResource(R.mipmap.collection);
    }


    @Override
    public void setPresenter(VideoInfoContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(DoodleBean mDoodleBean) {
        this.mDoodleBean = mDoodleBean;
        mTitleName.setText(mDoodleBean.getTitle());
        if (!TextUtils.isEmpty(mDoodleBean.getImg_small())) {

            int screenWidth = ScreenUtil.getScreenWidth(getContext());
            iv_logo.setMaxWidth(screenWidth);
            iv_logo.setMaxHeight(screenWidth);

            ImageLoader.load(mContext, mDoodleBean.getImg_small(), iv_logo);
        }

    }

    @OnClick(R.id.rl_collect)
    public void onClick() {
        if (mDoodleBean != null) {
            ivCollect.startAnimation(animation);
            mPresenter.collect();
        }
    }
}
