package com.mvp.base.ui.adapter;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.HomeTopCellBean;
import com.mvp.base.ui.adapter.viewholder.ViewHolder_01;
import com.mvp.base.ui.adapter.viewholder.ViewHolder_02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DishAdapter extends RecyclerArrayAdapter<Pair<Integer, Object>> implements ViewHolder_02.OnDishClickListener {

    /**
     * 类型集合，adapter对应的数据集合
     */
    List<Pair<Integer, Object>> superData = new ArrayList<>();
    final int TYPE_TOPCELL = 1;
    final int TYPE_DISHGROUP = 2;



    Map<String, DishBean> selectedDishes = new HashMap<>();

    @Override
    public boolean clickDish(DishBean dish) {
        if(selectedDishes.containsKey(dish.getDishname())){
            selectedDishes.remove(dish.getDishname());
        }else{
            selectedDishes.put(dish.getDishname(), dish);
        }
        notifyDataSetChanged();
        return true ;
    }

    /**
     * 返回已经选择的菜
     * @return
     */
    public Collection<DishBean> getSelectedDishes(){
        return selectedDishes.values();
    }

    public DishAdapter(Context context) {
        super(context);
    }

    public void setData(List<DishBean> dishes){
        superData.clear();

        superData.add(new Pair<Integer, Object>(TYPE_TOPCELL, null));

        for (int i = 0 ; i< dishes.size() ;i++){
            dishes.get(i).setHasTitle(
                    i==0 ||
                            !dishes.get(i).getTitle().equals(dishes.get(i-1).getTitle()));
        }



        for (int i = 0 ; i< dishes.size() ;i+=2){
            Pair<DishBean, DishBean> objectObjectPair = null;

            if(i+1 < dishes.size()) {
                if (dishes.get(i).isHasTitle() && !dishes.get(i + 1).isHasTitle()) {

                    objectObjectPair = new Pair<>(dishes.get(i), dishes.get(i
                            + 1));
                    superData.add(new Pair<Integer, Object>(TYPE_DISHGROUP, objectObjectPair));

                } else if (dishes.get(i).isHasTitle() && dishes.get(i + 1).isHasTitle()) {
                    objectObjectPair = new Pair<>(dishes.get(i), null);
                    superData.add(new Pair<Integer, Object>(TYPE_DISHGROUP, objectObjectPair));

                    Pair<DishBean, DishBean> objectObjectPair_new = new Pair<>(dishes.get(i + 1), null);
                    superData.add(new Pair<Integer, Object>(TYPE_DISHGROUP, objectObjectPair_new));

                } else if (!dishes.get(i).isHasTitle() && dishes.get(i + 1).isHasTitle()) {
                    objectObjectPair = new Pair<>(dishes.get(i), null);
                    superData.add(new Pair<Integer, Object>(TYPE_DISHGROUP, objectObjectPair));

                    Pair<DishBean, DishBean> objectObjectPair_new = new Pair<>(dishes.get(i + 1), null);
                    superData.add(new Pair<Integer, Object>(TYPE_DISHGROUP, objectObjectPair_new));

                } else if (!dishes.get(i).isHasTitle() && !dishes.get(i + 1).isHasTitle()) {
                    objectObjectPair = new Pair<>(dishes.get(i), dishes.get(i + 1));
                    superData.add(new Pair<Integer, Object>(TYPE_DISHGROUP, objectObjectPair));
                }
            }else{
                objectObjectPair = new Pair<>(dishes.get(i), null);
                superData.add(new Pair<Integer, Object>(TYPE_DISHGROUP, objectObjectPair));
            }


        }

        addAll(superData);

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_TOPCELL:
                return new ViewHolder_01(parent);
            case TYPE_DISHGROUP:
                return new ViewHolder_02(parent, this);
        }
        return new ViewHolder_01(parent);
    }


    @Override
    public int getViewType(int position) {
        return superData.get(position).first;
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        switch (superData.get(position).first) {
            case TYPE_TOPCELL:
                ((ViewHolder_01) holder).setData((android.util.Pair<HomeTopCellBean, HomeTopCellBean>) superData.get(position).second);
                break;
            case TYPE_DISHGROUP:
                boolean b_second = ((Pair<DishBean, DishBean>) superData.get(position).second).second == null? false:
                        selectedDishes.containsKey(((Pair<DishBean, DishBean>) superData.get(position).second).second.getDishname());
                ((ViewHolder_02) holder).setData((Pair<DishBean, DishBean>) superData.get(position).second,
                selectedDishes.containsKey(((Pair<DishBean, DishBean>) superData.get(position).second).first.getDishname()),
                b_second
                );
                break;
        }
    }


}
