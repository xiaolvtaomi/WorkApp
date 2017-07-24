package com.mvp.base.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.mvp.base.R;
import com.mvp.base.base.SwipeBackActivity;
import com.mvp.base.model.bean.WorkmateBean;
import com.mvp.base.presenter.DishManagePresenter;
import com.mvp.base.presenter.DishPresenter;
import com.mvp.base.ui.view.DishManageView;
import com.mvp.base.ui.view.DishView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 配菜界面
 */
public class DishSelectActivity extends SwipeBackActivity {

    @BindView(R.id.dishselect_view)
    DishView dishselect_view;


    WorkmateBean[] selectedPersons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishselect);
        unbinder = ButterKnife.bind(this);
        try {
            mPresenter = new DishPresenter(dishselect_view);

            selectedPersons = (WorkmateBean[]) getIntent().getSerializableExtra("selectedPersons");


            dishselect_view.setWorkmates(selectedPersons);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            finish();
        }
    }
}
