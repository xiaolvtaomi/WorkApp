package com.mvp.base.ui.adapter.viewholder;

import android.support.v4.util.Pair;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;
import com.mvp.base.component.ImageLoader;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.WorkmateBean;


public class WorkmateViewHolder extends BaseViewHolder<WorkmateBean> {


    ImageView imgPicture, iv_status;
    TextView tv_title;
    LinearLayout ll_container;

    OnWorkmateClickListener mOnWorkmateClickListener = null ;
    public interface OnWorkmateClickListener{

        boolean clickWorkmate(WorkmateBean workmateBean);
    }

    public WorkmateViewHolder(ViewGroup parent, OnWorkmateClickListener mOnWorkmateClickListener ) {
        super(parent, R.layout.item_related);
        ll_container = $(R.id.ll_container);
        imgPicture = $(R.id.img_video);
        iv_status = $(R.id.iv_status);
        tv_title = $(R.id.tv_title);
        imgPicture.setScaleType(ImageView.ScaleType.FIT_XY);
        if(mOnWorkmateClickListener != null){
            this.mOnWorkmateClickListener = mOnWorkmateClickListener ;
        }
    }

    @Override
    public void setData(WorkmateBean data){

    }
    public void setData(final WorkmateBean data, boolean selected) {
        tv_title.setText(""+data.getName());
        ViewGroup.LayoutParams params = imgPicture.getLayoutParams();

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels / 3;//宽度为屏幕宽度一半
//        int height = data.getHeight()*width/data.getWidth();//计算View的高度

        params.height = (int) (width * 1.0);
        imgPicture.setLayoutParams(params);
        ImageLoader.load(getContext(),data.getPicurl(),imgPicture);
        ll_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnWorkmateClickListener != null){
                    mOnWorkmateClickListener.clickWorkmate(data);
                }
            }
        });


        if(selected){
            iv_status.setVisibility(View.VISIBLE);
        }else{
            iv_status.setVisibility(View.GONE);
        }

    }



}
