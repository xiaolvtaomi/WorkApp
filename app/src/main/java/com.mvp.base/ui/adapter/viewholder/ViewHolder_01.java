package com.mvp.base.ui.adapter.viewholder;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;
import com.mvp.base.model.bean.HomeTopCellBean;
import com.mvp.base.utils.JumpUtil;


public class ViewHolder_01 extends BaseViewHolder<Pair<HomeTopCellBean, HomeTopCellBean>> {

	public ViewHolder_01(ViewGroup parent) {
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

            }
        });


	}


    @Override
    public void setData(Pair<HomeTopCellBean, HomeTopCellBean> data) {

    }
}
