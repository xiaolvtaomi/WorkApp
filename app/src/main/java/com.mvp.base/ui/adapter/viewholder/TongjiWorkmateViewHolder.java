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
    TextView  tv_dishname0;
    TextView  tv_dishname1;
    TextView  tv_dishname2;
    TextView  tv_dishname3;
    TextView  tv_dishname4;
    TextView  tv_dishname5;
    TextView tv_name;
    RelativeLayout rl_item0;
    RelativeLayout rl_item1;
    RelativeLayout rl_item2;
    RelativeLayout rl_item3;
    RelativeLayout rl_item4;
    RelativeLayout rl_item5;

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
        tv_dishname0= (TextView) itemView.findViewById(R.id.tv_dishname0);
        tv_dishname1= (TextView) itemView.findViewById(R.id.tv_dishname1);
        tv_dishname2= (TextView) itemView.findViewById(R.id.tv_dishname2);
        tv_dishname3= (TextView) itemView.findViewById(R.id.tv_dishname3);
        tv_dishname4= (TextView) itemView.findViewById(R.id.tv_dishname4);
        tv_dishname5= (TextView) itemView.findViewById(R.id.tv_dishname5);
        rl_item0= (RelativeLayout) itemView.findViewById(R.id.rl_item0);
        rl_item1= (RelativeLayout) itemView.findViewById(R.id.rl_item1);
        rl_item2= (RelativeLayout) itemView.findViewById(R.id.rl_item2);
        rl_item3= (RelativeLayout) itemView.findViewById(R.id.rl_item3);
        rl_item4= (RelativeLayout) itemView.findViewById(R.id.rl_item4);
        rl_item5= (RelativeLayout) itemView.findViewById(R.id.rl_item5);
        tv_name= (TextView) itemView.findViewById(R.id.tv_name);


    }

    @Override
    public void setData(TongjiWorkmateBean mWorkmateBean) {
        //super.initData(bean);
        if(mWorkmateBean!=null){
            //填充网络数据
            ImageLoader.load(getContext(),mWorkmateBean.workmate.getAvatar(),riv_avatar);
            tv_name.setText(mWorkmateBean.workmate.getName());
            for (int i = 0; i < mWorkmateBean.dishes.size(); i++) {
                if (i==0){
                    rl_item0.setVisibility(View.VISIBLE);
                    ImageLoader.load(getContext(),mWorkmateBean.dishes.get(i).getDishpic(),iv_dish0);
                    tv_dishname0 .setText(mWorkmateBean.dishes.get(i).getDishname());
                }
               else if (i==1){
                    rl_item1.setVisibility(View.VISIBLE);
                    ImageLoader.load(getContext(),mWorkmateBean.dishes.get(i).getDishpic(),iv_dish1);
                    tv_dishname1 .setText(mWorkmateBean.dishes.get(i).getDishname());
                }
                else if (i==2){
                    rl_item2.setVisibility(View.VISIBLE);
                    ImageLoader.load(getContext(),mWorkmateBean.dishes.get(i).getDishpic(),iv_dish2);
                    tv_dishname2 .setText(mWorkmateBean.dishes.get(i).getDishname());
                }
                else if (i==3){
                    rl_item3.setVisibility(View.VISIBLE);
                    ImageLoader.load(getContext(),mWorkmateBean.dishes.get(i).getDishpic(),iv_dish3);
                    tv_dishname3 .setText(mWorkmateBean.dishes.get(i).getDishname());
                }
                else if (i==4){
                    rl_item4.setVisibility(View.VISIBLE);
                    ImageLoader.load(getContext(),mWorkmateBean.dishes.get(i).getDishpic(),iv_dish4);
                    tv_dishname4 .setText(mWorkmateBean.dishes.get(i).getDishname());
                }
                else if (i==5){
                    rl_item5.setVisibility(View.VISIBLE);
                    ImageLoader.load(getContext(),mWorkmateBean.dishes.get(i).getDishpic(),iv_dish5);
                    tv_dishname5 .setText(mWorkmateBean.dishes.get(i).getDishname());
                }

            }
        }
        divider.setVisibility(View.VISIBLE);
        divider_02.setVisibility(View.VISIBLE);
        divider_03.setVisibility(View.GONE);




        //添加点击事件



    }
}
