package com.mvp.base.model.net;


import com.mvp.base.model.bean.BatchRequestBean;
import com.mvp.base.model.bean.CollectionBean;
import com.mvp.base.model.bean.CommentBean;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.model.bean.GankHttpResponse;
import com.mvp.base.model.bean.GankItemBean;
import com.mvp.base.model.bean.NickBean;
import com.mvp.base.model.bean.VideoRes;
import com.mvp.base.model.bean.WorkmateBean;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface BmobApis {

    String HOST = "https://api.bmob.cn/1/";

    String APPID = "03274654d9ef74a7989712c500f155aa";
    String RESTKEY = "502d2271cf2efa791972b85135ef8222";
    String CONTENTTYPE = "application/json";


    /**
     * 首页list
     * where={"level":{"$gte":1}}
     * where%3d%7b%22level%22%3a%7b%22%24gte%22%3a1%7d%7d
     *
     * @return
     */
    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:03274654d9ef74a7989712c500f155aa","X-Bmob-REST-API-Key:502d2271cf2efa791972b85135ef8222"})
    @GET("classes/doodle?where%3d%7b%22level%22%3a%7b%22%24gte%22%3a1%7d%7d&order=-createdAt")
    Observable<BmobHttpResponse<List<DoodleBean>>> getHomePage(@Query("limit") int limit , @Query("skip") int skip);

    /**
     * 根据objectid获得doodle详情
     * where={"level":{"$gte":1}}
     * where%3d%7b%22level%22%3a%7b%22%24gte%22%3a1%7d%7d
     *
     * @return
     */
    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:03274654d9ef74a7989712c500f155aa","X-Bmob-REST-API-Key:502d2271cf2efa791972b85135ef8222"})
    @GET("classes/doodle/{objectid}")
    Observable<DoodleBean> getDoodleDetail(@Path("objectid") String objectid);



    /**
     * 根据doodleid获得doodle相关的集合
     *
     * @return
     */
    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:03274654d9ef74a7989712c500f155aa","X-Bmob-REST-API-Key:502d2271cf2efa791972b85135ef8222"})
    @GET("classes/doodle")
    Observable<BmobHttpResponse<List<DoodleBean>>> getDoodleReact(
            @Query("where") String encoded_monthday);


    /**
     * 根据doodleid获得doodle的评价最新100个的集合
     * where={"doodleId":"Lily"}
     * where%3d%7b%22doodleId%22%3a%22Lily%22%7d
     * @return
     */
    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:03274654d9ef74a7989712c500f155aa","X-Bmob-REST-API-Key:502d2271cf2efa791972b85135ef8222"})
    @GET("classes/comment")
    Observable<BmobHttpResponse<List<CommentBean>>> getCommentsByDoodleid(@Query("where") String doodleidmap, @Query("limit") int limit, @Query("skip") int skip);

    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:03274654d9ef74a7989712c500f155aa","X-Bmob-REST-API-Key:502d2271cf2efa791972b85135ef8222"})
    @GET("classes/doodle")
    Observable<BmobHttpResponse<List<DoodleBean>>> getDoodleByCollectid(@Query("where") String doodleidmap, @Query("limit") int limit, @Query("skip") int skip);

    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:9511f045589e984315543d0a6e44e857","X-Bmob-REST-API-Key:34b46edfd764465a1d3925321403a7ed"})
    @GET("classes/dillitem?order=title")
    Observable<BmobHttpResponse<List<DillItemBean>>> getDillItemsByYMD(@Query("where") String map);


    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:9511f045589e984315543d0a6e44e857","X-Bmob-REST-API-Key:34b46edfd764465a1d3925321403a7ed"})
    @GET("classes/dillitem")
    Observable<BmobHttpResponse<List<DillItemBean>>> getDillItemsByYMD_UID(@Query("where") String map);



    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:9511f045589e984315543d0a6e44e857","X-Bmob-REST-API-Key:34b46edfd764465a1d3925321403a7ed"})
    @GET("classes/dish")
    Observable<BmobHttpResponse<List<DishBean>>> getDishesByYMD(@Query("where") String map);

    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:9511f045589e984315543d0a6e44e857","X-Bmob-REST-API-Key:34b46edfd764465a1d3925321403a7ed"})
    @GET("classes/dish?order=title")
    Observable<BmobHttpResponse<List<DishBean>>> getAllDishes();

    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:9511f045589e984315543d0a6e44e857","X-Bmob-REST-API-Key:34b46edfd764465a1d3925321403a7ed"})
    @POST("classes/dish")
    Call<ResponseBody> addDishbean(@Body DishBean mDishBean);

    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:9511f045589e984315543d0a6e44e857","X-Bmob-REST-API-Key:34b46edfd764465a1d3925321403a7ed"})
    @POST("batch")
    Call<ResponseBody> postDillBatch(@Body BatchRequestBean batchRequestBean);

    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:03274654d9ef74a7989712c500f155aa","X-Bmob-REST-API-Key:502d2271cf2efa791972b85135ef8222"})
    @POST("classes/comment")
    Observable<BmobHttpResponse> postComment(@Body CommentBean mCommentBean);



    /**
     * 随机获取50个Doodle ,用doodleid来排序分页
     *
     * @return
     */
    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:03274654d9ef74a7989712c500f155aa","X-Bmob-REST-API-Key:502d2271cf2efa791972b85135ef8222"})
    @GET("classes/doodle?order=doodleid&limit=25")
    Observable<BmobHttpResponse<List<DoodleBean>>> getDoodleRandom(@Query("skip") int skip);


    /**
     * 随机获取一个nick
     *
     * @return
     */
    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:03274654d9ef74a7989712c500f155aa","X-Bmob-REST-API-Key:502d2271cf2efa791972b85135ef8222"})
    @GET("classes/nickname?limit=1")
    Observable<BmobHttpResponse<List<NickBean>>> getNickRandom(@Query("skip") int skip);

    /**
     * 专辑list
     *
     *
     * @return
     */
    @Headers({"CONTENTTYPE:application/json","X-Bmob-Application-Id:03274654d9ef74a7989712c500f155aa","X-Bmob-REST-API-Key:502d2271cf2efa791972b85135ef8222"})
    @GET("classes/collection")
    Observable<BmobHttpResponse<List<CollectionBean>>> getCollection();

}
