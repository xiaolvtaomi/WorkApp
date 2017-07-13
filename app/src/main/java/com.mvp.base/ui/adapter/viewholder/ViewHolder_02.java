package com.mvp.base.ui.adapter.viewholder;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mvp.base.R;
import com.mvp.base.component.ImageLoader;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.model.bean.DishGroupBean;
import com.mvp.base.ui.adapter.DishAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ViewHolder_02 extends BaseViewHolder<Pair<DishBean,DishBean>> {


    View divider;//分割线
    View divider_03;//分割线
    TextView tvTitle;
    ImageView ivSecond;
    ImageView ivSecond_selected;
    ImageView ivFirst;
    ImageView ivFirst_selected;
    View divider_02;//长线
    RelativeLayout rl_item_community_second;
    RelativeLayout rl_item_community_first;
    TextView tv_item_community_title_first;
    TextView tv_item_community_title_second;
    TextView tv_item_community_num_first;
    TextView tv_item_community_num_second;

    DishBean first;
    DishBean second;

    OnDishClickListener mOnDishClickListener = null ;
    public interface OnDishClickListener{
        /**
         *
         * @param dish
         * @return
         */
        boolean clickDish(DishBean dish);
    }


    public ViewHolder_02(ViewGroup parent, OnDishClickListener mOnDishClickListener) {
        super(parent, R.layout.item_community_other);
        this.mOnDishClickListener = mOnDishClickListener ;
        divider=itemView.findViewById(R.id.item_community_divider);
        tvTitle= (TextView) itemView.findViewById(R.id.tv_item_community_title);
        divider_02=itemView.findViewById(R.id.item_community_divider02);
        divider_03=itemView.findViewById(R.id.item_community_divider03);
        ivSecond= (ImageView) itemView.findViewById(R.id.iv_item_community_second);
        ivFirst= (ImageView) itemView.findViewById(R.id.iv_item_community_first);
        ivFirst_selected= (ImageView) itemView.findViewById(R.id.iv_first_selected);
        ivSecond_selected= (ImageView) itemView.findViewById(R.id.iv_second_selected);
        rl_item_community_second= (RelativeLayout) itemView.findViewById(R.id.rl_item_community_second);
        rl_item_community_first= (RelativeLayout) itemView.findViewById(R.id.rl_item_community_first);
        tv_item_community_title_first= (TextView) itemView.findViewById(R.id.tv_item_community_title_first);
        tv_item_community_title_second= (TextView) itemView.findViewById(R.id.tv_item_community_title_second);
        tv_item_community_num_first= (TextView) itemView.findViewById(R.id.tv_item_community_num_first);
        tv_item_community_num_second= (TextView) itemView.findViewById(R.id.tv_item_community_num_second);

    }

    @Override
    public void setData(Pair<DishBean,DishBean> beanpair){

    }

    public void setData(Pair<DishBean,DishBean> beanpair, boolean b_first, boolean b_second) {
        //super.initData(bean);

        final Pair<DishBean,DishBean> objectObjectPair= beanpair;

        if(objectObjectPair!=null){

            first = (DishBean) objectObjectPair.first;
            //填充网络数据
            ImageLoader.load(getContext(),first.picurl,ivFirst);
            tv_item_community_title_first.setText(first.dishname);
            tv_item_community_num_first.setText("辣椒");
            if(first.hasTitle){//需要显示标头
                divider.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(first.title);
                divider_02.setVisibility(View.VISIBLE);
                divider_03.setVisibility(View.GONE);

            }else{//不需要显示标头
                divider.setVisibility(View.GONE);
                tvTitle.setVisibility(View.GONE);
                divider_02.setVisibility(View.INVISIBLE);
                divider_03.setVisibility(View.GONE);

            }


            if(objectObjectPair.second==null){//第二个数据为空
               // ivSecond.setVisibility(View.GONE);
                if(rl_item_community_second!=null)
                rl_item_community_second.setVisibility(View.INVISIBLE);
            }else {//第二个数据不为空

                second = (DishBean) objectObjectPair.second;
                if(rl_item_community_second!=null)
                rl_item_community_second.setVisibility(View.VISIBLE);
                //填充网络数据
                ImageLoader.load(getContext(),second.picurl,ivSecond);
                tv_item_community_title_second.setText(second.dishname);
                tv_item_community_num_second.setText("辣椒");
            }


            if(b_first){
                ivFirst_selected.setVisibility(View.VISIBLE);
            }else{
                ivFirst_selected.setVisibility(View.GONE);

            }

            if(b_second){
                ivSecond_selected.setVisibility(View.VISIBLE);

            }else{
                ivSecond_selected.setVisibility(View.GONE);

            }

        }

        //添加点击事件
        if(beanpair.first != null) {
            rl_item_community_first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnDishClickListener != null){
                        mOnDishClickListener.clickDish(objectObjectPair.first);
                    }
                }
            });
        }
        if(beanpair.second != null) {
            rl_item_community_second.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mOnDishClickListener != null){
                        mOnDishClickListener.clickDish(objectObjectPair.second);
                    }
                }
            });
        }


    }
}
