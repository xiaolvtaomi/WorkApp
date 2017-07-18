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
    //辣度图片
    //  TextView tv_item_community_num_first;
    ImageView tv_item_community_num_first;
    //  TextView tv_item_community_num_second;
    ImageView tv_item_community_num_second;


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
 //       ivFirst_selected= (ImageView) itemView.findViewById(R.id.iv_first_seleted);
        ivSecond_selected= (ImageView) itemView.findViewById(R.id.iv_second_seleted);
        rl_item_community_second= (RelativeLayout) itemView.findViewById(R.id.rl_item_community_second);
        rl_item_community_first= (RelativeLayout) itemView.findViewById(R.id.rl_item_community_first);
        tv_item_community_title_first= (TextView) itemView.findViewById(R.id.tv_item_community_title_first);
        tv_item_community_title_second= (TextView) itemView.findViewById(R.id.tv_item_community_title_second);
        tv_item_community_num_first= (ImageView) itemView.findViewById(R.id.tv_item_community_num_first);
        tv_item_community_num_second= (ImageView) itemView.findViewById(R.id.tv_item_community_num_second);

    }

    @Override
    public void setData(Pair<DishBean,DishBean> beanpair){

    }

    /**
     * 点菜界面用的填充数据的方法
     * @param beanpair
     * @param b_first
     * @param b_second
     */
    public void setData(Pair<DishBean,DishBean> beanpair, boolean b_first, boolean b_second) {
        //super.initData(bean);

        final Pair<DishBean,DishBean> objectObjectPair= beanpair;

        if(objectObjectPair!=null){

            first = (DishBean) objectObjectPair.first;
            //填充网络数据
            ImageLoader.load(getContext(),first.picurl,ivFirst);
            tv_item_community_title_first.setText(first.dishname);
            //辣度图片选择
            if (first.spicylevel==0){
                tv_item_community_num_first.setImageResource(R.mipmap.none);
            }
            else if (first.spicylevel==1){
                tv_item_community_num_first.setImageResource(R.mipmap.one);
            }
            else if (first.spicylevel==2){
                tv_item_community_num_first.setImageResource(R.mipmap.two);
            }
            else if (first.spicylevel==3){
                tv_item_community_num_first.setImageResource(R.mipmap.three);
            }
            else {
                tv_item_community_num_first.setImageResource(R.mipmap.none);
            }
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
                //辣度图片选择
                if (first.spicylevel==0){
                    tv_item_community_num_second.setImageResource(R.mipmap.none);
                }
                else if (first.spicylevel==1){
                    tv_item_community_num_second.setImageResource(R.mipmap.one);
                }
                else if (first.spicylevel==2){
                    tv_item_community_num_second.setImageResource(R.mipmap.two);
                }
                else if (first.spicylevel==3){
                    tv_item_community_num_second.setImageResource(R.mipmap.three);
                }
                else {
                    tv_item_community_num_second.setImageResource(R.mipmap.none);
                }
            }


            if(b_first){
                // ivFirst_selected.setVisibility(View.VISIBLE);
                rl_item_community_first.setBackgroundResource(R.mipmap.pitch_on);
            }else{
              //   ivFirst_selected.setVisibility(View.GONE);
                rl_item_community_first.setBackgroundResource(R.mipmap.back_pic);
            }

            if(b_second){
            //    ivSecond_selected.setVisibility(View.VISIBLE);
                rl_item_community_second.setBackgroundResource(R.mipmap.pitch_on);

            }else{
            //    ivSecond_selected.setVisibility(View.GONE);
                rl_item_community_second.setBackgroundResource(R.mipmap.back_pic);

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




    /**
     * 配菜界面用的填充数据的方法
     * @param beanpair
     * @param b_first
     * @param b_second
     */
    public void setDataManage(Pair<DishBean,DishBean> beanpair, boolean b_first, boolean b_second) {
        //super.initData(bean);

        final Pair<DishBean,DishBean> objectObjectPair= beanpair;

        if(objectObjectPair!=null){

            first = (DishBean) objectObjectPair.first;
            //填充网络数据
            ImageLoader.load(getContext(),first.picurl,ivFirst);
            tv_item_community_title_first.setText(first.dishname);
            //辣度图片选择
            if (first.spicylevel==0){
                tv_item_community_num_first.setImageResource(R.mipmap.none);
            }
            else if (first.spicylevel==1){
                tv_item_community_num_first.setImageResource(R.mipmap.one);
            }
            else if (first.spicylevel==2){
                tv_item_community_num_first.setImageResource(R.mipmap.two);
            }
            else if (first.spicylevel==3){
                tv_item_community_num_first.setImageResource(R.mipmap.three);
            }
            else {
                tv_item_community_num_first.setImageResource(R.mipmap.none);
            }
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
                //辣度图片选择
                if (first.spicylevel==0){
                    tv_item_community_num_second.setImageResource(R.mipmap.none);
                }
                else if (first.spicylevel==1){
                    tv_item_community_num_second.setImageResource(R.mipmap.one);
                }
                else if (first.spicylevel==2){
                    tv_item_community_num_second.setImageResource(R.mipmap.two);
                }
                else if (first.spicylevel==3){
                    tv_item_community_num_second.setImageResource(R.mipmap.three);
                }
                else {
                    tv_item_community_num_second.setImageResource(R.mipmap.none);
                }
            }


            if(b_first){
                // ivFirst_selected.setVisibility(View.VISIBLE);
                rl_item_community_first.setBackgroundResource(R.mipmap.pitch_on);
                rl_item_community_first.setAlpha(1.0f);
            }else{
                //   ivFirst_selected.setVisibility(View.GONE);
                rl_item_community_first.setBackgroundResource(R.mipmap.back_pic);
                rl_item_community_first.setAlpha(0.5f);
            }

            if(b_second){
                //    ivSecond_selected.setVisibility(View.VISIBLE);
                rl_item_community_second.setBackgroundResource(R.mipmap.pitch_on);
                rl_item_community_second.setAlpha(1.0f);

            }else{
                //    ivSecond_selected.setVisibility(View.GONE);
                rl_item_community_second.setBackgroundResource(R.mipmap.back_pic);
                rl_item_community_second.setAlpha(0.5f);

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
