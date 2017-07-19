package com.mvp.base.ui.activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.mvp.base.R;
import com.mvp.base.base.SwipeBackActivity;
import com.mvp.base.presenter.DishManagePresenter;
import com.mvp.base.ui.view.DishManageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 配菜界面
 */
public class DishManageActivity extends SwipeBackActivity {

    @BindView(R.id.dishmanage_view)
    DishManageView dishmanage_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishmanage);
        unbinder = ButterKnife.bind(this);
        mPresenter = new DishManagePresenter(dishmanage_view);
        SharedPreferences sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        //取得欢迎界面背景
        DishManageActivity.rolle = sp.getInt("role",0);


    }
    public  static int  rolle = 0;
}
