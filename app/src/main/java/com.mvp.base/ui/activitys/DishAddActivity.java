package com.mvp.base.ui.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.TResult;
import com.mvp.base.R;
import com.mvp.base.base.BaseActivity;
import com.mvp.base.base.SwipeBackActivity;

import com.mvp.base.base.SwipeBackWithPicActivity;
import com.mvp.base.component.ImageLoader;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.utils.GsonUtil;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;
import com.mvp.base.widget.circleprogress.CircleProgress;
import com.mvp.base.widget.theme.ColorTextView;
import com.sina.cloudstorage.auth.AWSCredentials;
import com.sina.cloudstorage.auth.BasicAWSCredentials;
import com.sina.cloudstorage.services.scs.SCS;
import com.sina.cloudstorage.services.scs.SCSClient;
import com.sina.cloudstorage.services.scs.model.PutObjectResult;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
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
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by lml on 17/7/14.
 */

public class DishAddActivity extends SwipeBackWithPicActivity implements View.OnClickListener{

    String accessKey = "18fj8yd7wTMClAV82gph";
    String secretKey = "d6e28b0c3631d16901a2a6cbeb29ecf6bff40193";
    AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    SCS conn = new SCSClient(credentials);


    @BindView(R.id.btn_post)
    Button btn_post ;
    @BindView(R.id.iv_logo)
    ImageView iv_logo ;
    @BindView(R.id.loading)
    CircleProgress loading;

    @BindView(R.id.title_name)
    ColorTextView titleName;
    @BindView(R.id.et_title)
    EditText et_title;
    @BindView(R.id.et_dishname)
    EditText et_dishname;

    DishBean bean = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishadd);
        unbinder = ButterKnife.bind(this);

        bean = (DishBean)getIntent().getSerializableExtra("dishbean");

        titleName = (ColorTextView) findViewById(R.id.title_name);
        titleName.setText("新增");
        btn_post = (Button) findViewById(R.id.btn_post);
        btn_post.setOnClickListener(this);
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        iv_logo .setOnClickListener(this);

    }


    String compressPath = "";
    String originalPath = "";
    @OnClick({R.id.btn_post, R.id.iv_logo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_post:

                if(TextUtils.isEmpty(compressPath)){
                    Toast.makeText(this,"选图", Toast.LENGTH_SHORT).show();


                }else {
                    if(TextUtils.isEmpty(et_title.getText().toString())){
                        Toast.makeText(this,"填分类", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if(TextUtils.isEmpty(et_dishname.getText().toString())){
                        Toast.makeText(this,"填菜名", Toast.LENGTH_SHORT).show();
                        break;
                    }


                    btn_post.setClickable(false);
                    loading.setVisibility(View.VISIBLE);
                    // 测试
                    if(bean == null) {
                        bean = new DishBean();
                    }
                    if(!TextUtils.isEmpty(compressPath)) {
                        bean.setPicurl("http://cdn.sinacloud.net/diancai/dish/" + compressPath.substring(compressPath.lastIndexOf("/") + 1));
                    }
                    bean.setSpicylevel(0);
                    bean.setTitle(et_title.getText().toString());
                    bean.setDishname(et_dishname.getText().toString());
                    bean.setIsopen(false);

                    putObjectWithCustomRequestHeader(compressPath, bean);

                }
                break;
            case R.id.iv_logo:
                CompressConfig compressConfig = new CompressConfig.Builder().setMaxPixel(320).create();
                getTakePhoto().onEnableCompress(compressConfig,true);
                getTakePhoto().onPickFromGallery();
                break;

        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 123: // 隐藏loading
                    btn_post.setClickable(true);
                    loading.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };


    /**
     * 上传文件 自定义请求头
     */
    public void putObjectWithCustomRequestHeader(final String filepath, final DishBean bean){
        new Thread(){
            public void run(){
                //自定义请求头k-v
//                Map<String, String> requestHeader = new HashMap<String, String>();
//                requestHeader.put("x-sina-additional-indexed-key", filepath.substring(filepath.lastIndexOf("/"))+1);
//                PutObjectResult putObjectResult = conn.putObject("diancai", "dish/",
//                        new File(filepath), requestHeader);

                Map<String, String> headers = new HashMap<String, String>();
                headers.put("x-amz-acl", "public-read");
                PutObjectResult putObjectResult = conn.putObject("diancai",
                        "dish/"+filepath.substring(filepath.lastIndexOf("/")+1),
                        new File(filepath), headers);
                System.out.println(putObjectResult);//服务器响应结果

                if(putObjectResult != null && !TextUtils.isEmpty(putObjectResult.getContentMd5())){
                    postDish(bean);
                }else{
                    handler.sendEmptyMessage(123);
                }


            }
        }.start();

    }


    @Override
    public void takeSuccess(TResult result) {
//        super.takeSuccess(result);
        compressPath = result.getImage().getCompressPath() ;
        originalPath = result.getImage().getOriginalPath() ;
        ImageLoader.load(this, compressPath, iv_logo);
    }


    public void  postDish(DishBean bean){
//        Map<String, Object> params = new HashMap<>();
//        int year , month ,day ;
//        year = Calendar.getInstance().get(Calendar.YEAR);
//        month = Calendar.getInstance().get(Calendar.MONTH);
//        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//        params.put("year", year);
//        params.put("month", month);
//        params.put("day", day);
//        String json = new Gson().toJson(params) ;
        Call<ResponseBody> call = RetrofitHelper.getBmobApis().addDishbean(bean);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(123);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                handler.sendEmptyMessage(123);
            }
        });
    }

}
