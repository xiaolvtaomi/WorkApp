package com.mvp.base.ui.adapter;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mvp.base.model.bean.CollectionBean;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.DishTongjiBean;
import com.mvp.base.model.bean.HomeTopCellBean;
import com.mvp.base.ui.adapter.viewholder.DishTongjiViewHolder;
import com.mvp.base.ui.adapter.viewholder.ViewHolder_01;
import com.mvp.base.ui.adapter.viewholder.ViewHolder_02;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 统计
 */
public class ClassificationAdapter extends RecyclerArrayAdapter<DishTongjiBean> {


    public ClassificationAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DishTongjiViewHolder(parent);
    }


    /**
     * 类型集合，adapter对应的数据集合
     */
    List<DishTongjiBean> superData = new ArrayList<>();


    public void setData(List<DillItemBean> dillitems) {
        superData.clear();

        ArrayList<DishBean> dishes = new ArrayList<>();
        for(DillItemBean dillitem : dillitems){
            dishes.add(dillitem.getDish());
        }
        for (int i = 0; i < dishes.size(); i++) {
            dishes.get(i).setHasTitle(
                    i == 0 ||
                            !dishes.get(i).getTitle().equals(dishes.get(i -
                                    1).getTitle()));
        }

        for (int i = 0; i < dishes.size(); i++) {
            boolean hasinclude = false;
            for (int j = 0; j < superData.size(); j++) {
                if (dishes.get(i).getDishname().equals(superData.get(j)
                        .getTitle())) {
                    superData.get(j).setCountnum(superData.get(j).getCountnum
                            () + 1);
                    hasinclude = true;
                    break;
                }
            }
            if (!hasinclude) {
                DishTongjiBean temp = new DishTongjiBean(dishes.get(i));
                superData.add(temp);
            }
        }


        addAll(superData);

    }


    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        ((DishTongjiViewHolder) holder).setData(superData.get(position));


    }
}
