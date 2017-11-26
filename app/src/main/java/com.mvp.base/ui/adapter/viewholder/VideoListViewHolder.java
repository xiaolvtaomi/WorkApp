package com.mvp.base.ui.adapter.viewholder;

import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;

/**
 * Description: 影片列表
 * Creator: yxc
 * date: 2016/9/30 11:06
 */

public class VideoListViewHolder extends BaseViewHolder<com.mvp.base.model.bean.VideoType> {
    ImageView imgPicture;
    TextView tv_title;

    public VideoListViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_related);
        imgPicture = $(R.id.img_video);
        tv_title = $(R.id.tv_title);
        imgPicture.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void setData(com.mvp.base.model.bean.VideoType data) {
        tv_title.setText(data.title);
        ViewGroup.LayoutParams params = imgPicture.getLayoutParams();

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels / 3;//宽度为屏幕宽度一半
//        int height = data.getHeight()*width/data.getWidth();//计算View的高度

        params.height = (int) (width * 1.1);
        imgPicture.setLayoutParams(params);
        com.mvp.base.component.ImageLoader.load(getContext(),data.pic,imgPicture);

    }
}
