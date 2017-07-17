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
import com.mvp.base.model.bean.WorkmateBean;
import com.mvp.base.model.net.BmobHttpResponse;
import com.mvp.base.model.net.RetrofitHelper;
import com.mvp.base.presenter.contract.cook.WorkmateContract;
import com.mvp.base.utils.GsonUtil;
import com.mvp.base.utils.PreUtils;
import com.mvp.base.utils.RxUtil;
import com.mvp.base.utils.StringUtils;

import java.io.IOException;
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

/**
 * Created by lml on 17/7/11.
 */

public class WorkmatePresenter extends RxPresenter implements WorkmateContract.Presenter{
    @NonNull
    WorkmateContract.View mView ;

    public WorkmatePresenter(@NonNull WorkmateContract.View addTaskView){
        this.mView = addTaskView;
        this.mView.setPresenter(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        getUnDishedWorkmates();
    }

    @Override
    public void getUnDishedWorkmates() {
        Map<String, Object> params = new HashMap<>();
        String json = new Gson().toJson(params) ;
        Subscription rxSubscription = RetrofitHelper.getBmobClouds().getUnDishedWorkmates()
                .compose(RxUtil.<BmobHttpResponse<List<WorkmateBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WorkmateBean>>handleBmobResult())
                .subscribe(new Action1<List<WorkmateBean>>() {
                    @Override
                    public void call(List<WorkmateBean> beans) {
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
    public void postWorkmates(Collection<WorkmateBean> workmateBeens) {

        BatchRequestBean batchrequest = new BatchRequestBean();
        for (WorkmateBean dish : workmateBeens){
            DillItemBean temp = new DillItemBean();
//            temp.setTitle(dish.getTitle());
//            temp.setDishname(dish.getDishname());
//            temp.setAvatar("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3001304778,4021565056&fm=96");
//            temp.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//            temp.setDishpic(dish.getPicurl());
//            temp.setMonth(Calendar.getInstance().get(Calendar.MONTH)+1);
//            temp.setWorkmate_creator("李梦龙");
//            temp.setWorkmate_name("李梦龙");
//            temp.setYear(Calendar.getInstance().get(Calendar.YEAR));

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

        Log.e("WorkmatePresenter", GsonUtil.getJson(batchrequest));

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
    public void clickWorkmate(WorkmateBean workmate) {

    }
}
