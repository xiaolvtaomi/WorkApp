package com.mvp.base.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mvp.base.model.bean.DillItemBean;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.TongjiDishBean;
import com.mvp.base.ui.adapter.viewholder.TongjiDishViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 统计
 */
public class TongjiDishAdapter extends RecyclerArrayAdapter<TongjiDishBean> {


    public TongjiDishAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TongjiDishViewHolder(parent);
    }


    /**
     * 类型集合，adapter对应的数据集合
     */
    List<TongjiDishBean> superData = new ArrayList<>();
    Map<String, DishBean> mapdata = new HashMap<>();

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
                        .getDishname())) {
                    superData.get(j).setCountnum(superData.get(j).getCountnum
                            () + 1);
                    hasinclude = true;
                    break;
                }
            }
            if (!hasinclude) {
                TongjiDishBean temp = new TongjiDishBean(dishes.get(i));
                superData.add(temp);
            }
        }


        addAll(superData);

    }


    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        ((TongjiDishViewHolder) holder).setData(superData.get(position));


    }
}
