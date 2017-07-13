package com.mvp.base.ui.adapter.viewholder;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;

/**
 * Description: 专题
 * Creator: yxc
 * date: 2016/9/30 11:06
 */

public class ClassificationViewHolder extends BaseViewHolder<com.mvp.base.model.bean.VideoInfo> {
    ImageView imgPicture;
    TextView tv_title;

    public ClassificationViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_found);
        imgPicture = $(R.id.img_video);
        tv_title = $(R.id.tv_title);
        imgPicture.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void setData(com.mvp.base.model.bean.VideoInfo data) {
        tv_title.setText(data.title);
        ViewGroup.LayoutParams params = imgPicture.getLayoutParams();

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels / 2;//宽度为屏幕宽度一半
//        int height = data.getHeight()*width/data.getWidth();//计算View的高度

        params.height = (int) (width / 1.8);
        imgPicture.setLayoutParams(params);
        if(!TextUtils.isEmpty(data.title) && data.title.contains("新增专题")){
            imgPicture.setBackgroundResource(R.mipmap.default_320_add);
        }else{
            imgPicture.setBackgroundResource(R.mipmap.default_320);
            com.mvp.base.component.ImageLoader.load(getContext(),data.pic,imgPicture);
        }
    }
}
