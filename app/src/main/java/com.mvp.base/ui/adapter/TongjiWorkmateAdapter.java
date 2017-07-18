package com.mvp.base.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.TongjiDishBean;
import com.mvp.base.model.bean.TongjiWorkmateBean;
import com.mvp.base.ui.adapter.viewholder.TongjiDishViewHolder;
import com.mvp.base.ui.adapter.viewholder.TongjiWorkmateViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 统计
 */
public class TongjiWorkmateAdapter extends RecyclerArrayAdapter<TongjiWorkmateBean> {


    public TongjiWorkmateAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TongjiDishViewHolder(parent);
    }


    /**
     * 类型集合，adapter对应的数据集合
     */
    List<TongjiWorkmateBean> superData = new ArrayList<>();


    public void setData(List<TongjiWorkmateBean> dillitems) {
        superData.clear();
        superData.addAll(dillitems);

        addAll(superData);

    }


    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        ((TongjiWorkmateViewHolder) holder).setData(superData.get(position));


    }
}
