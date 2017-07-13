package com.mvp.base.ui.adapter.viewholder;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;
import com.mvp.base.model.bean.HomeTopCellBean;



public class ViewHolder_01 extends BaseViewHolder<Pair<HomeTopCellBean, HomeTopCellBean>> {

	public ViewHolder_01(ViewGroup parent) {
		super(parent, R.layout.item_community_top);
	}


    @Override
    public void setData(Pair<HomeTopCellBean, HomeTopCellBean> data) {

    }
}
