package com.mvp.base.ui.adapter.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;
import com.mvp.base.component.ImageLoader;
import com.mvp.base.model.bean.TongjiWorkmateBean;
import com.mvp.base.widget.theme.RoundedImageView;


public class TongjiWorkmateViewHolder extends BaseViewHolder<TongjiWorkmateBean> {


    RoundedImageView riv_avatar ;
    View divider;//分割线
    View divider_03;//分割线
    View divider_02;//长线


    public TongjiWorkmateViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_tongji_workmate);
        divider=itemView.findViewById(R.id.item_community_divider);
        riv_avatar=(RoundedImageView)itemView.findViewById(R.id.riv_avatar);
        divider_02=itemView.findViewById(R.id.item_community_divider02);
        divider_03=itemView.findViewById(R.id.item_community_divider03);

    }

    @Override
    public void setData(TongjiWorkmateBean mWorkmateBean) {
        //super.initData(bean);



        if(mWorkmateBean!=null){

            //填充网络数据
            ImageLoader.load(getContext(),mWorkmateBean.workmate.getPicurl(),riv_avatar);

            divider.setVisibility(View.VISIBLE);
            divider_02.setVisibility(View.VISIBLE);
            divider_03.setVisibility(View.GONE);


        }

        //添加点击事件



    }
}
