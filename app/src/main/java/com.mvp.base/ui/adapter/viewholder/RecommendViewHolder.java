package com.mvp.base.ui.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;

/**
 * Description: RecommendViewHolder
 * Creator: yxc
 * date: 2016/9/21 9:53
 */

public class RecommendViewHolder extends BaseViewHolder<com.mvp.base.model.bean.VideoInfo> {
    ImageView imgPicture;
    TextView tv_title;

    public RecommendViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_video);
        imgPicture = $(R.id.img_video);
        tv_title = $(R.id.tv_title);
        imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void setData(com.mvp.base.model.bean.VideoInfo data) {
        tv_title.setText(data.title);
        com.mvp.base.component.ImageLoader.load(getContext(),data.pic,imgPicture);
    }
}
