package com.mvp.base.ui.adapter.viewholder;

import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;
import com.mvp.base.model.bean.HomeTopCellBean;
import com.mvp.base.utils.JumpUtil;


public class WorkmateUnknowViewHolder extends BaseViewHolder<Pair<HomeTopCellBean, HomeTopCellBean>> {

	public WorkmateUnknowViewHolder(ViewGroup parent) {
		super(parent, R.layout.item_community_top);

        itemView.findViewById(R.id.rl_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.go2DishManageActivity(getContext());
            }
        });
        itemView.findViewById(R.id.rl_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.go2WorkmateListActivity(getContext(), "代点");
            }
        });


	}


    @Override
    public void setData(Pair<HomeTopCellBean, HomeTopCellBean> data) {

    }
}
