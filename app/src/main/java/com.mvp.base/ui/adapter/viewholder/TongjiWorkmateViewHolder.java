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
    ImageView iv_dish0;
    ImageView iv_dish1;
    ImageView iv_dish2;
    ImageView iv_dish3;
    ImageView iv_dish4;
    ImageView iv_dish5;
    TextView  staple_foodname;
    TextView  dish_onename;
    TextView  dish_twoname;
    TextView  dish_threename;
    TextView  dish_fourname;
    TextView  dish_fivename;
    TextView tv_name;

    public TongjiWorkmateViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_tongji_workmate);
        divider=itemView.findViewById(R.id.item_community_divider);
        riv_avatar=(RoundedImageView)itemView.findViewById(R.id.riv_avatar);
        divider_02=itemView.findViewById(R.id.item_community_divider02);
        divider_03=itemView.findViewById(R.id.item_community_divider03);
        iv_dish0= (ImageView) itemView.findViewById(R.id.iv_dish0);
        iv_dish1=(ImageView) itemView.findViewById(R.id.iv_dish1);
        iv_dish2=(ImageView) itemView.findViewById(R.id.iv_dish2);
        iv_dish3=(ImageView) itemView.findViewById(R.id.iv_dish3);
        iv_dish4=(ImageView) itemView.findViewById(R.id.iv_dish4);
        iv_dish5=(ImageView) itemView.findViewById(R.id.iv_dish5);
        staple_foodname= (TextView) itemView.findViewById(R.id.tv_dishname0);
        dish_onename= (TextView) itemView.findViewById(R.id.tv_dishname1);
        dish_twoname= (TextView) itemView.findViewById(R.id.tv_dishname2);
        dish_threename= (TextView) itemView.findViewById(R.id.tv_dishname3);
        dish_fourname= (TextView) itemView.findViewById(R.id.tv_dishname4);
        dish_fivename= (TextView) itemView.findViewById(R.id.tv_dishname5);
        tv_name= (TextView) itemView.findViewById(R.id.tv_name);


    }

    @Override
    public void setData(TongjiWorkmateBean mWorkmateBean) {
        //super.initData(bean);
        if(mWorkmateBean!=null){
            //填充网络数据
            ImageLoader.load(getContext(),mWorkmateBean.workmate.getPicurl(),riv_avatar);
            tv_name.setText(mWorkmateBean.workmate.getName());
//            ImageLoader.load(getContext(),mWorkmateBean.workmate.getPicurl(),iv_dish0);
//            ImageLoader.load(getContext(),mWorkmateBean.workmate.getPicurl(),riv_avatar);
//            ImageLoader.load(getContext(),mWorkmateBean.workmate.getPicurl(),riv_avatar);
//            ImageLoader.load(getContext(),mWorkmateBean.workmate.getPicurl(),riv_avatar);
//            ImageLoader.load(getContext(),mWorkmateBean.workmate.getPicurl(),riv_avatar);
//            ImageLoader.load(getContext(),mWorkmateBean.workmate.getPicurl(),riv_avatar);
            }
            divider.setVisibility(View.VISIBLE);
            divider_02.setVisibility(View.VISIBLE);
            divider_03.setVisibility(View.GONE);




        //添加点击事件



    }
}
