package com.mvp.base.ui.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.TResult;
import com.mvp.base.R;
import com.mvp.base.base.BaseActivity;
import com.mvp.base.base.SwipeBackActivity;

import com.mvp.base.base.SwipeBackWithPicActivity;
import com.mvp.base.component.ImageLoader;
import com.mvp.base.widget.theme.ColorTextView;
import com.sina.cloudstorage.auth.AWSCredentials;
import com.sina.cloudstorage.auth.BasicAWSCredentials;
import com.sina.cloudstorage.services.scs.SCS;
import com.sina.cloudstorage.services.scs.SCSClient;
import com.sina.cloudstorage.services.scs.model.PutObjectResult;

import org.w3c.dom.Text;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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

//    @BindView(R.id.title_name)
    ColorTextView titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishadd);

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
                    putObjectWithCustomRequestHeader(compressPath);
                }
                break;
            case R.id.iv_logo:
                CompressConfig compressConfig = new CompressConfig.Builder().setMaxPixel(200).create();
                getTakePhoto().onEnableCompress(compressConfig,true);
                getTakePhoto().onPickFromGallery();
                break;

        }
    }


    /**
     * 上传文件 自定义请求头
     */
    public void putObjectWithCustomRequestHeader(final String filepath){
        new Thread(){
            public void run(){
                //自定义请求头k-v
                Map<String, String> requestHeader = new HashMap<String, String>();
                requestHeader.put("x-sina-additional-indexed-key", filepath.substring(filepath.lastIndexOf("/"))+1);
                PutObjectResult putObjectResult = conn.putObject("diancai", "dish/",
                        new File(filepath), requestHeader);
                System.out.println(putObjectResult);//服务器响应结果
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

}
