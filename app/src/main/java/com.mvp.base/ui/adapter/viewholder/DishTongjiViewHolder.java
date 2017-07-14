package com.mvp.base.ui.adapter.viewholder;

import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;
import com.mvp.base.component.ImageLoader;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.DishTongjiBean;


public class DishTongjiViewHolder extends BaseViewHolder<DishTongjiBean> {


    View divider;//分割线
    View divider_03;//分割线
    TextView tvTitle;
    TextView tv_count;
    ImageView ivFirst;
    View divider_02;//长线
    RelativeLayout rl_item_community_first;
    TextView tv_item_community_title_first;
  //  TextView tv_item_community_num_first;
    ImageView tv_item_community_num_first;


    public DishTongjiViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_dish_tongji);
        divider=itemView.findViewById(R.id.item_community_divider);
        tvTitle= (TextView) itemView.findViewById(R.id.tv_item_community_title);
        tv_count= (TextView) itemView.findViewById(R.id.tv_count);
        divider_02=itemView.findViewById(R.id.item_community_divider02);
        divider_03=itemView.findViewById(R.id.item_community_divider03);
        ivFirst= (ImageView) itemView.findViewById(R.id.iv_item_community_first);
        rl_item_community_first= (RelativeLayout) itemView.findViewById(R.id.rl_item_community_first);
        tv_item_community_title_first= (TextView) itemView.findViewById(R.id.tv_item_community_title_first);
        tv_item_community_num_first= (ImageView) itemView.findViewById(R.id.tv_item_community_num_first);

    }

    @Override
    public void setData(DishTongjiBean mDishTongjiBean) {
        //super.initData(bean);



        if(mDishTongjiBean!=null){

            //填充网络数据
            ImageLoader.load(getContext(),mDishTongjiBean.picurl,ivFirst);
            tv_item_community_title_first.setText(mDishTongjiBean.dishname);
            if (mDishTongjiBean.spicylevel==0){
                tv_item_community_num_first.setImageResource(R.mipmap.none);
            }
            else if (mDishTongjiBean.spicylevel==1){
                tv_item_community_num_first.setImageResource(R.mipmap.one);
            }
            else if (mDishTongjiBean.spicylevel==2){
                tv_item_community_num_first.setImageResource(R.mipmap.two);
            }
            else if (mDishTongjiBean.spicylevel==3){
                tv_item_community_num_first.setImageResource(R.mipmap.three);
            }
            else {
                tv_item_community_num_first.setImageResource(R.mipmap.none);
            }
            if(mDishTongjiBean.hasTitle){//需要显示标头
                divider.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(mDishTongjiBean.title);
                divider_02.setVisibility(View.VISIBLE);
                divider_03.setVisibility(View.GONE);

            }else{//不需要显示标头
                divider.setVisibility(View.GONE);
                tvTitle.setVisibility(View.GONE);
                divider_02.setVisibility(View.INVISIBLE);
                divider_03.setVisibility(View.GONE);

            }

            tv_count.setText("* "+mDishTongjiBean.countnum);


        }

        //添加点击事件



    }
}
