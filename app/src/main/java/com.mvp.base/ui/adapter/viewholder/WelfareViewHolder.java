package com.mvp.base.ui.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;

/**
 * Description: 福利
 * Creator: yxc
 * date: 2016/10/24 14:17
 */

public class WelfareViewHolder extends BaseViewHolder<com.mvp.base.model.bean.GankItemBean> {
    ImageView imgPicture;

    public WelfareViewHolder(ViewGroup parent) {
        super(new ImageView(parent.getContext()));
        imgPicture = (ImageView) itemView;
        imgPicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void setData(com.mvp.base.model.bean.GankItemBean data) {
        imgPicture.setBackgroundResource(R.mipmap.default_200);
        ViewGroup.LayoutParams params = imgPicture.getLayoutParams();
        params.height = data.getHeight();
        imgPicture.setLayoutParams(params);
        Glide.with(getContext())
                .load(data.getUrl())
                .into(imgPicture);
    }
}
