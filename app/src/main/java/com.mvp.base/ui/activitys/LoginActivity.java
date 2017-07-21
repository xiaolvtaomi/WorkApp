package com.mvp.base.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mvp.base.R;
import com.mvp.base.base.BaseActivity;
import com.mvp.base.model.bean.LoginResponse;
import com.mvp.base.model.bean.WorkmateBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.utils.GsonUtil;
import com.mvp.base.utils.Httpurl;
import com.mvp.base.utils.JumpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    ActivityOptionsCompat oc2;
    String str1;
    String str2;
    WorkmateBean bean;
    String name;
    String mobile;
    int  role;
    String avatar;
    String objectId;
    int userid;
    String objectid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        SharedPreferences sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        String moible = sp.getString("mobile", "");
        objectid=sp.getString("objectId","");
        Httpurl.objectID=objectid;
       if (!"".equals(name)&&!"".equals(moible)&&name!=null&&moible!=null){
           JumpUtil.goMainActivity(LoginActivity.this);
       }
    }
    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    getWindow().setExitTransition(null);
//                    getWindow().setEnterTransition(null);
//                    ActivityOptions options =
//                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
//                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
//                } else {
//                    startActivity(new Intent(this, RegisterActivity.class));
//                }
                break;
            case R.id.bt_go:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    EditText editText1 =(EditText)findViewById(R.id.et_username);
                    str1=editText1.getText().toString();
                    EditText editText2 =(EditText)findViewById(R.id.et_password);
                    str2=editText2.getText().toString();
                    Explode explode = new Explode();
                    explode.setDuration(500);
                    getWindow().setExitTransition(explode);
                    getWindow().setEnterTransition(explode);
                    oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);

                    if (!"".equals(str1)&&str1!=null&&!"".equals(str2)&&str2!=null) {
                        Map<String, Object> params = new HashMap<>();
                        params.put("name", str1);
                        params.put("mobile", str2);
                        String json = new Gson().toJson(params);
                        Call<ResponseBody> call = RetrofitHelper.getBmobApis().login(json);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String respon_str = response.body().string();
                                    System.out.println(respon_str);
                                    LoginResponse responsebean = (LoginResponse) GsonUtil.getObject(respon_str, LoginResponse.class);
                                    List<WorkmateBean> results = responsebean==null?null:responsebean.getResults();
                                    if (results!=null && results.size() > 0) {
                                        name=results.get(0).getName();
                                        mobile=results.get(0).getMobile();
                                        role=results.get(0).getRole();
                                        avatar=results.get(0).getAvatar();
                                        objectId=results.get(0).getObjectId();
                                        userid=results.get(0).getUserid();
                                        SharedPreferences mSharedPreferences = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                                        editor.putString("name",name);
                                        editor.putString("mobile",mobile);
                                        editor.putInt("role",role);
                                        editor.putString("avatar",avatar);
                                        editor.putString("objectId",objectId);
                                        editor.putInt("userid",userid);
                                        editor.commit();
                                        Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(i2, oc2.toBundle());
                                        SharedPreferences sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                                        String name = sp.getString("name", "");
                                        String moible = sp.getString("mobile", "");
                                        objectid=sp.getString("objectId","");
                                        Httpurl.objectID=objectid;
                                    } else {
                                        Toast.makeText(getApplicationContext(), "请输入正确的姓名与手机号", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(), "请输入姓名与手机号", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    EditText editText1 =(EditText)findViewById(R.id.et_username);
                    str1=editText1.getText().toString();
                    EditText editText2 =(EditText)findViewById(R.id.et_password);
                    str2=editText2.getText().toString();
                    if (!"".equals(str1)&&!"".equals(str2)&&str1!=null&&str2!=null) {
                        Map<String, Object> params = new HashMap<>();
                        params.put("name", str1);
                        params.put("mobile", str2);
                        String json = new Gson().toJson(params);
                        Call<ResponseBody> call = RetrofitHelper.getBmobApis().login(json);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String respon_str = response.body().string();
                                    System.out.println(respon_str);
                                    LoginResponse responsebean = (LoginResponse) GsonUtil.getObject(respon_str, LoginResponse.class);
                                    List<WorkmateBean> results = responsebean==null?null:responsebean.getResults();
                                    if (results!=null && results.size() > 0) {
                                        name=results.get(0).getName();
                                        mobile=results.get(0).getMobile();
                                        role=results.get(0).getRole();
                                        avatar=results.get(0).getAvatar();
                                        objectId=results.get(0).getObjectId();
                                        userid=results.get(0).getUserid();
                                        SharedPreferences mSharedPreferences = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                                        editor.putString("name",name);
                                        editor.putString("mobile",mobile);
                                        editor.putInt("role",role);
                                        editor.putString("avatar",avatar);
                                        editor.putString("objectId",objectId);
                                        editor.putInt("userid",userid);
                                        editor.commit();
                                        SharedPreferences sp = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                                        String name = sp.getString("name", "");
                                        String moible = sp.getString("mobile", "");
                                        objectid=sp.getString("objectId","");
                                        Httpurl.objectID=objectid;
                                        JumpUtil.goMainActivity(LoginActivity.this);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "请输入正确的姓名与手机号", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(), "请输入姓名与手机号", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
