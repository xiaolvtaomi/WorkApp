package com.mvp.base.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mvp.base.model.bean.VideoType;
import com.mvp.base.ui.adapter.viewholder.MineHistoryVideoListViewHolder;

public class MineHistoryVideoListAdapter extends RecyclerArrayAdapter<VideoType> {

    public MineHistoryVideoListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineHistoryVideoListViewHolder(parent);
    }

}
