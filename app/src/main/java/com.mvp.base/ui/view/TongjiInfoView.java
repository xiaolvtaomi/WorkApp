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
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.presenter.contract.VideoInfoContract;
import com.mvp.base.ui.activitys.MainActivity;
import com.mvp.base.ui.fragments.TongjiWorkmateFragment;
import com.mvp.base.ui.fragments.TongjiDishFragment;
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



public class TongjiInfoView extends RootView<VideoInfoContract.Presenter> implements VideoInfoContract.View {

    @BindView(R.id.title_name)
    ColorTextView mTitleName;
    @BindView(R.id.viewpagertab)
    SmartTabLayout mViewpagertab;
    @BindView(R.id.viewpager)
    SwipeViewPager mViewpager;

    private Animation animation;

    public TongjiInfoView(Context context) {
        super(context);
    }

    public TongjiInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.activity_video_info_view, this);
    }

    @Override
    protected void initView() {
        mTitleName.setText("统计");
        animation = AnimationUtils.loadAnimation(mContext, R.anim.view_hand);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                ((MainActivity) mContext).getSupportFragmentManager(), FragmentPagerItems.with(mContext)
                .add("按菜统计", TongjiDishFragment.class)
                .add("按人统计", TongjiWorkmateFragment.class)
                .create());
        mViewpager.setAdapter(adapter);
        mViewpagertab.setViewPager(mViewpager);
//        iv_logo.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    protected void initEvent() {

    }



    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void hidLoading() {
//        mLoading.setVisibility(View.GONE);
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

    }

    public void onClick() {
    }
}
