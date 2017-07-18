package com.mvp.base.ui.adapter;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mvp.base.model.bean.WorkmateBean;
import com.mvp.base.model.bean.HomeTopCellBean;
import com.mvp.base.ui.adapter.viewholder.ViewHolder_01;
import com.mvp.base.ui.adapter.viewholder.ViewHolder_02;
import com.mvp.base.ui.adapter.viewholder.WorkmateUnknowViewHolder;
import com.mvp.base.ui.adapter.viewholder.WorkmateViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WorkmateAdapter extends RecyclerArrayAdapter<Pair<Integer, Object>> implements WorkmateViewHolder.OnWorkmateClickListener {

    /**
     * 类型集合，adapter对应的数据集合
     */
    List<Pair<Integer, Object>> superData = new ArrayList<>();
    final int TYPE_TOPCELL = 1;
    final int TYPE_WorkmateGROUP = 2;



    Map<String, WorkmateBean> selectedWorkmates = new HashMap<>();

    

    /**
     * 返回已经选择的ren
     * @return
     */
    public Collection<WorkmateBean> getselectedWorkmates(){
        return selectedWorkmates.values();
    }

    public WorkmateAdapter(Context context) {
        super(context);
    }

    public void setData(List<WorkmateBean> workmates){
        superData.clear();

//        superData.add(new Pair<Integer, Object>(TYPE_TOPCELL, null));

        for (int i = 0 ; i< workmates.size() ;i++){
            superData.add(new Pair<Integer, Object>(TYPE_WorkmateGROUP, workmates.get(i)));
        }


        addAll(superData);

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_TOPCELL:
                return new WorkmateUnknowViewHolder(parent);
            case TYPE_WorkmateGROUP:
                return new WorkmateViewHolder(parent, this);
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
                ((WorkmateUnknowViewHolder) holder).setData((android.util.Pair<HomeTopCellBean, HomeTopCellBean>) superData.get(position).second);
                break;
            case TYPE_WorkmateGROUP:
                ((WorkmateViewHolder) holder).setData((WorkmateBean) superData.get(position).second);
                break;
        }
    }
    
    
    @Override
    public boolean clickWorkmate(WorkmateBean workmateBean) {
        if(selectedWorkmates.containsKey(workmateBean.getName())){
            selectedWorkmates.remove(workmateBean.getName());
        }else{
            selectedWorkmates.put(workmateBean.getName(), workmateBean);
        }
        notifyDataSetChanged();
        return true ;
    }
}
