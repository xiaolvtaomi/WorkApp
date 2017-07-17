package com.mvp.base.model.net;


import com.mvp.base.model.bean.BatchRequestBean;
import com.mvp.base.model.bean.CollectionBean;
import com.mvp.base.model.bean.CommentBean;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.NickBean;
import com.mvp.base.model.bean.WorkmateBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface BmobClouds {

    String HOST_CLOUD = "https://cloud.bmob.cn/e07debd368cf7106/";


    @Headers({"CONTENTTYPE:application/json; charset=utf-8","X-Bmob-Application-Id:9511f045589e984315543d0a6e44e857","X-Bmob-REST-API-Key:34b46edfd764465a1d3925321403a7ed"})
    @GET("getUnDishedWorkmates")
    Observable<BmobHttpResponse<List<WorkmateBean>>> getUnDishedWorkmates();


}
