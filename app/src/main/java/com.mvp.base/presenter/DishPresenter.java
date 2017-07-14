package com.mvp.base.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.mvp.base.base.RxPresenter;
import com.mvp.base.model.bean.BatchBean;
import com.mvp.base.model.bean.BatchRequestBean;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.cook.DishContract;
import com.mvp.base.utils.GsonUtil;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.functions.Action1;

import static com.mvp.base.model.bean.BatchRequestBean.*;

/**
 * Created by lml on 17/7/11.
 */

public class DishPresenter extends RxPresenter implements DishContract.Presenter{
    @NonNull
    DishContract.View mView ;

    public DishPresenter(@NonNull DishContract.View addTaskView){
        this.mView = addTaskView;
        this.mView.setPresenter(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        getTodayDishes();
        getMyDish(PreUtils.getString(mView.getContext(), "uid", ""));
    }

    @Override
    public void getTodayDishes() {
        Map<String, Object> params = new HashMap<>();
        int year , month ,day ;
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//        params.put("year", year);
//        params.put("month", month);
//        params.put("day", day);
        params.put("isopen", true);
        String json = new Gson().toJson(params) ;
        Subscription rxSubscription = RetrofitHelper.getBmobApis().getDishesByYMD(json)
                .compose(RxUtil.<BmobHttpResponse<List<DishBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<DishBean>>handleBmobResult())
                .subscribe(new Action1<List<DishBean>>() {
                    @Override
                    public void call(List<DishBean> beans) {
                        if (beans != null) {
                            if (mView.isActive()) {
                                mView.showContent(beans);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFailed(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void getMyDish(String uid) {
        if(!TextUtils.isEmpty(uid)) {
            Map<String, Object> params = new HashMap<>();
            int year, month, day;
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            params.put("year", year);
            params.put("month", month);
            params.put("day", day);
            params.put("userid", uid);
            String json = new Gson().toJson(params);
            Subscription rxSubscription = RetrofitHelper.getBmobApis().getDillItemsByYMD_UID(json)

                    .compose(RxUtil.<BmobHttpResponse<List<DillItemBean>>>rxSchedulerHelper())
                    .compose(RxUtil.<List<DillItemBean>>handleBmobResult())
                    .subscribe(new Action1<List<DillItemBean>>() {
                        @Override
                        public void call(List<DillItemBean> beans) {
                            if (beans != null) {
                                if (mView.isActive()) {
                                    mView.showMyDishesContent(beans);
                                }
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            mView.refreshFailed(StringUtils.getErrorMsg(throwable.getMessage()));
                        }
                    });
            addSubscribe(rxSubscription);
        }
    }

    @Override
    public void postDishes(Collection<DishBean> dishes) {

        BatchRequestBean batchrequest = new BatchRequestBean();
        for (DishBean dish : dishes){
            DillItemBean temp = new DillItemBean();
            temp.setTitle(dish.getTitle());
            temp.setDishname(dish.getDishname());
            temp.setAvatar("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3001304778,4021565056&fm=96");
            temp.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            temp.setDishpic(dish.getPicurl());
            temp.setMonth(Calendar.getInstance().get(Calendar.MONTH)+1);
            temp.setWorkmate_creator("李梦龙");
            temp.setWorkmate_name("李梦龙");
            temp.setYear(Calendar.getInstance().get(Calendar.YEAR));

            BatchBean batchbean = new BatchBean();
            batchbean.setMethod("POST");
            batchbean.setPath("/1/classes/dillitem");
            batchbean.setBody(temp);
            batchrequest.getRequests().add(batchbean);
        }

//        Subscription rxSubscription = RetrofitHelper.getBmobApis().postDillBatch(batchrequest)
//                .compose(RxUtil.<List<Map<String,BmobHttpResponse>>>rxSchedulerHelper())
//                .compose(RxUtil.<List<DillItemBean>>handleBmobResult())
//                .subscribe(new Action1<List<Map<String,BmobHttpResponse>>>() {
//                    @Override
//                    public void call(List<Map<String,BmobHttpResponse>> response) {
//                        if (response != null && !TextUtils.isEmpty(response.getObjectId())) {
//                            if (mView.isActive()) {
//                                mView.postSuc(mDillitembean);
//                            }else{
//                                mView.postFailed("提交失败");
//                            }
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        mView.postFailed("提交异常");
//                    }
//                });
//        addSubscribe(rxSubscription);

        Log.e("DishPresenter", GsonUtil.getJson(batchrequest));

        Call<ResponseBody> call = RetrofitHelper.getBmobApis().postDillBatch(batchrequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                    mView.postSuc();
                } catch (IOException e) {
                    e.printStackTrace();
                    mView.postFailed("提交失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void clickDish(DishBean dish) {

    }
}
