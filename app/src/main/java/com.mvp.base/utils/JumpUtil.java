package com.mvp.base.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;

import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.VideoInfo;
import com.mvp.base.ui.activitys.DishAddActivity;
import com.mvp.base.ui.activitys.DishManageActivity;
import com.mvp.base.ui.activitys.MainActivity;
import com.mvp.base.ui.activitys.WorkmateListActivity;
import com.mvp.base.ui.activitys.WelcomeActivity;

/**
 * Description: JumpUtil
 * Creator: yxc
 * date: 2016/9/21 14:46
 */
public class JumpUtil {



    public static void go2WorkmateListActivity(Context context, String title) {
        Intent intent = new Intent(context, WorkmateListActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }
    public static void go2VideoListSearchActivity(Context context, String searchStr,String title) {
        Intent intent = new Intent(context, WorkmateListActivity.class);
        intent.putExtra("searchStr", searchStr);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    public static void go2MainActivity(Context context) {
        jump(context, MainActivity.class);
        ((WelcomeActivity) context).finish();
    }

    private static void jump(Context a, Class<?> clazz) {
        Intent intent = new Intent(a, clazz);
        a.startActivity(intent);
    }


    public static void go2DishManageActivity(Context context) {
        Intent intent = new Intent(context, DishManageActivity.class);
        context.startActivity(intent);
    }

    public static void go2DishAddActivity(Context context) {
        Intent intent = new Intent(context, DishAddActivity.class);
        context.startActivity(intent);
    }

    public static void goMainActivity(Context context, ActivityOptionsCompat oc2) {
        Intent intent = new Intent(context, MainActivity.class);
        if(oc2 == null) {
            context.startActivity(intent);

        }else{
            context.startActivity(intent, oc2.toBundle());

        }
    }

}
