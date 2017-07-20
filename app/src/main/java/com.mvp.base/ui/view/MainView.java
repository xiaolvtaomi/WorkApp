package com.mvp.base.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library
        .MaterialDesignIconic;
import com.mvp.base.R;
import com.mvp.base.base.RootView;
import com.mvp.base.presenter.contract.MainContract;
import com.mvp.base.ui.activitys.CollectionActivity;
import com.mvp.base.ui.activitys.MainActivity;
import com.mvp.base.ui.activitys.SettingActivity;
import com.mvp.base.ui.adapter.ContentPagerAdapter;
import com.mvp.base.ui.fragments.ClassificationFragment;
import com.mvp.base.ui.fragments.DiscoverFragment;
import com.mvp.base.ui.fragments.MineFragment;
import com.mvp.base.ui.fragments.RecommendFragment;
import com.mvp.base.utils.EventUtil;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.Preconditions;
import com.mvp.base.utils.StringUtils;
import com.mvp.base.utils.ThemeUtils;
import com.mvp.base.widget.ResideLayout;
import com.mvp.base.widget.UnScrollViewPager;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mvp.base.ui.activitys.MainActivity.Banner_Stop;


/**
 * Description: MainView
 * Creator: yxc
 * date: 2016/10/20 10:14
 */
public class MainView extends RootView<MainContract.Presenter> implements MainContract.View, RadioGroup.OnCheckedChangeListener {

    final int WAIT_TIME = 200;

    @BindView(R.id.tab_rg_menu)
    RadioGroup tabRgMenu;
    @BindView(R.id.vp_content)
    UnScrollViewPager vpContent;
    ContentPagerAdapter mPagerAdapter;
    MainActivity mActivity;


    public MainView(Context context) {
        super(context);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.activity_main_view2, this);
    }

    @Override
    protected void initView() {
        mActivity = (MainActivity) mContext;
        List<Fragment> fragments = initFragments();
        vpContent.setScrollable(false);
        mPagerAdapter = new ContentPagerAdapter(mActivity.getSupportFragmentManager(), fragments);
        vpContent.setAdapter(mPagerAdapter);
        vpContent.setOffscreenPageLimit(fragments.size());
//        StringUtils.setIconDrawable(mContext, tvCollect, MaterialDesignIconic.Icon.gmi_collection_bookmark, 16, 10);
//        StringUtils.setIconDrawable(mContext, tvShare, MaterialDesignIconic.Icon.gmi_share, 16, 10);
//        StringUtils.setIconDrawable(mContext, tvFeedback, MaterialDesignIconic.Icon.gmi_android, 16, 10);
//        StringUtils.setIconDrawable(mContext, tvSetting, MaterialDesignIconic.Icon.gmi_settings, 16, 10);
//        StringUtils.setIconDrawable(mContext, about, MaterialDesignIconic.Icon.gmi_account, 16, 10);
//        StringUtils.setIconDrawable(mContext, theme, MaterialDesignIconic.Icon.gmi_palette, 16, 10);
    }

    @Override
    protected void initEvent() {
        tabRgMenu.setOnCheckedChangeListener(this);
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) tabRgMenu.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
//        mResideLayout.setPanelSlideListener(new ResideLayout.PanelSlideListener() {
//            @Override
//            public void onPanelSlide(View panel, float slideOffset) {
//                postBannerState(true);
//            }
//
//            @Override
//            public void onPanelOpened(View panel) {
//                postBannerState(true);
//            }
//
//            @Override
//            public void onPanelClosed(View panel) {
//                postBannerState(false);
//            }
//        });
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);


    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    private void postBannerState(final boolean stop) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(stop, Banner_Stop);
            }
        }, WAIT_TIME);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.tab_rb_1:
                vpContent.setCurrentItem(0, false);
                break;
            case R.id.tab_rb_2:
                vpContent.setCurrentItem(1, false);
                break;
            case R.id.tab_rb_4:
                vpContent.setCurrentItem(2, false);
                break;
        }
    }

    private List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment1 = new RecommendFragment();
        Fragment fragment2 = new ClassificationFragment();
        Fragment mineFragment = new MineFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(mineFragment);
        return fragments;
    }

    @Subscriber(tag = MineFragment.SET_THEME)
    public void setTheme(String content) {
        new ColorChooserDialog.Builder(mActivity, R.string.theme)
                .customColors(R.array.colors, null) 
                .doneButton(R.string.done)
                .cancelButton(R.string.cancel)
                .allowUserColorInput(false)
                .allowUserColorInputAlpha(false)
                .show();
    }



//    public ResideLayout getResideLayout() {
//        return mResideLayout;
//    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }


}
