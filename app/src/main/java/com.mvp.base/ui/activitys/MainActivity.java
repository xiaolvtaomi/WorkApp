package com.mvp.base.ui.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.Log;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.mvp.base.R;
import com.mvp.base.app.App;
import com.mvp.base.app.Constants;
import com.mvp.base.utils.EventUtil;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.ThemeUtils;
import com.mvp.base.base.BaseActivity;
import com.mvp.base.ui.fragments.ClassificationFragment;
import com.mvp.base.ui.view.MainView;
import com.mvp.base.widget.theme.Theme;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity implements ColorChooserDialog.ColorCallback, TakePhoto.TakeResultListener,InvokeListener {

    public static final String Set_Theme_Color = "Set_Theme_Color";
    public static final String UPDATE_AVATAR = "update_avatar";
    public final static String Banner_Stop = "Banner_Stop";
    private Long firstTime = 0L;
    @BindView(R.id.main_view)
    MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        PgyUpdateManager.register(MainActivity.this, "");


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == ThemeUtils.getThemeColor(this, R.attr.colorPrimary))
            return;

        if (selectedColor == getResources().getColor(R.color.colorBluePrimary)) {
            this.setTheme(R.style.BlueTheme);
            PreUtils.setCurrentTheme(this, Theme.Blue);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#2196F3");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorRedPrimary)) {
            this.setTheme(R.style.RedTheme);
            PreUtils.setCurrentTheme(this, Theme.Red);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#F44336");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorBrownPrimary)) {
            this.setTheme(R.style.BrownTheme);
            PreUtils.setCurrentTheme(this, Theme.Brown);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#795548");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorGreenPrimary)) {
            this.setTheme(R.style.GreenTheme);
            PreUtils.setCurrentTheme(this, Theme.Green);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#4CAF50");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorPurplePrimary)) {
            this.setTheme(R.style.PurpleTheme);
            PreUtils.setCurrentTheme(this, Theme.Purple);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#9c27b0");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorTealPrimary)) {
            this.setTheme(R.style.TealTheme);
            PreUtils.setCurrentTheme(this, Theme.Teal);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#009688");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorPinkPrimary)) {
            this.setTheme(R.style.PinkTheme);
            PreUtils.setCurrentTheme(this, Theme.Pink);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#E91E63");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorDeepPurplePrimary)) {
            this.setTheme(R.style.DeepPurpleTheme);
            PreUtils.setCurrentTheme(this, Theme.DeepPurple);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#673AB7");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorOrangePrimary)) {
            this.setTheme(R.style.OrangeTheme);
            PreUtils.setCurrentTheme(this, Theme.Orange);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#FF9800");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorIndigoPrimary)) {
            this.setTheme(R.style.IndigoTheme);
            PreUtils.setCurrentTheme(this, Theme.Indigo);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#3F51B5");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorLightGreenPrimary)) {
            this.setTheme(R.style.LightGreenTheme);
            PreUtils.setCurrentTheme(this, Theme.LightGreen);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#8BC34A");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorDeepOrangePrimary)) {
            this.setTheme(R.style.DeepOrangeTheme);
            PreUtils.setCurrentTheme(this, Theme.DeepOrange);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "##FF5722");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorLimePrimary)) {
            this.setTheme(R.style.LimeTheme);
            PreUtils.setCurrentTheme(this, Theme.Lime);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#CDDC39");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorBlueGreyPrimary)) {
            this.setTheme(R.style.BlueGreyTheme);
            PreUtils.setCurrentTheme(this, Theme.BlueGrey);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#CDDC39");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(R.color.colorCyanPrimary)) {
            this.setTheme(R.style.CyanTheme);
            PreUtils.setCurrentTheme(this, Theme.Cyan);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#00BCD4");
            PreUtils.putString(this, Constants.TITLECOLOR, "#ffffff");
        } else if (selectedColor == getResources().getColor(android.R.color
                .black)) {
            this.setTheme(R.style.BlackTheme);
            PreUtils.setCurrentTheme(this, Theme.Black);
            PreUtils.putString(this, Constants.PRIMARYCOLOR, "#000000");
            PreUtils.putString(this, Constants.TITLECOLOR, "#0aa485");
        }
        EventBus.getDefault().post("", Set_Theme_Color);
    }

    @Override
    public void onBackPressed() {
//        if (mainView.getResideLayout().isOpen()) {
//            mainView.getResideLayout().closePane();
//        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1500) {
                EventUtil.showToast(this, "再按一次退出");
                firstTime = secondTime;
            } else {
                App.getInstance().exitApp();
            }
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);

        /**
         * 处理二维码扫描结果

        if (requestCode == Constants.REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    insertCell
                    if(result.startsWith("http://")){

                    }else{
                        for (int i = 0 ;i < 3 ;i++){
                            result = new String(Base64.decode(result, Base64.DEFAULT));
                            if(result.startsWith("http://")){
                                break;
                            }
                        }
                    }

                    if(result.startsWith("http://")){
                        com.mvp.base.model.bean.Cell cell = new com.mvp.base.model.bean.Cell();
                        cell.setTitle("日期:"+sdf.format(new Date()));
                        cell.setPath(result);
                        cell.setNpath(result);
                        com.mvp.base.model.db.RealmHelper.getInstance().insertCell(cell);

                        Subscription rxSubscription = Observable.timer(200, TimeUnit.MILLISECONDS)
                                .compose(RxUtil.<Long>rxSchedulerHelper())
                                .subscribe(new Action1<Long>() {
                                    @Override
                                    public void call(Long aLong) {
                                        EventBus.getDefault().post("",
                                                ClassificationFragment.REFRESH_CELL);
                                    }
                                });

                        CompositeSubscription mCompositeSubscription = new CompositeSubscription();
                        mCompositeSubscription.add(rxSubscription);
                    } else {
                        Toast.makeText(this, "无法识别结果" + result, Toast.LENGTH_LONG).show();

                    }


                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
         */

    }


    @Override
    public void takeSuccess(TResult result) {
        Log.e(TAG, "000000===="+result.getImage().getCompressPath());
        EventBus.getDefault().post(result, UPDATE_AVATAR);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.e(TAG, "11111===="+msg);

    }

    @Override
    public void takeCancel() {
        Log.e(TAG, "22222====");

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }


    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private String TAG= "MainActivity";

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    /**
     *  获取TakePhoto实例
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }
}