package com.mvp.base.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mvp.base.R;
import com.mvp.base.model.bean.DishBean;
import com.mvp.base.utils.ScreenUtil;

/**
 * 
 * @Description 已选的控件
 */
public class BookedView extends LinearLayout {
    LinearLayout ll_booked ;
    RoundedImageView riv_post ;

	interface OnBookedListener{
		void clickBookedView(View view, String tag);
		void clickPost(View view);
	}
	private OnBookedListener mOnBookedListener ;
	public void setOnBookedListener(OnBookedListener mOnBookedListener){
		if(mOnBookedListener != null){
			this.mOnBookedListener = mOnBookedListener ;
		}
	}

	private static final int PADDING_HOR = 10;// 水平方向padding
	private static final int PADDING_VERTICAL = 10;// 垂直方向padding
	private static final int SIDE_MARGIN = 10;// 左右间距
	private static final int TEXT_MARGIN = 10;

	/**
	 * @param context
	 */
	public BookedView(Context context) {
		super(context);
        initView();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public BookedView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        initView();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public BookedView(Context context, AttributeSet attrs) {
		super(context, attrs);
        initView();
	}

    final int BOOKED_TYPE_DISH = 10 ;
    final int BOOKED_TYPE_SOUP = 12 ;
    final int BOOKED_TYPE_MAIN = 11 ;
    final int TAG_KEY_TYPE = 0 ;
    int num_dish = 2, num_main = 1, num_soup = 1 ;
    public void refreshType(int num_dish, int num_main, int num_soup){
        this.num_dish = num_dish ;
        this.num_main = num_main ;
        this.num_soup = num_soup ;
//        invalidate();

        if(ll_booked != null){
            ll_booked.removeAllViews();

            LayoutParams lp = new LayoutParams(ScreenUtil.dip2px(getContext(), 48), LayoutParams.MATCH_PARENT);
            lp.leftMargin = ScreenUtil.dip2px(getContext(), 12);
            float radius = ScreenUtil.dip2px(getContext(), 48) ;
            for (int i = 0 ; i< num_dish ;i++){
                RoundedImageView bookedview = new RoundedImageView(getContext());
                bookedview.setImageResource(R.mipmap.ic_launcher);
                bookedview.setBorderColor(Color.WHITE);
                bookedview.setCornerRadius(radius);
                bookedview.setTag(TAG_KEY_TYPE, BOOKED_TYPE_DISH);
                bookedview.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mOnBookedListener != null){
                            mOnBookedListener.clickBookedView(v, String.valueOf(v.getTag(TAG_KEY_TYPE)));
                        }
                    }
                });
                ll_booked.addView(bookedview, lp);
            }
            for (int i = 0 ; i< num_main ;i++){
                RoundedImageView bookedview = new RoundedImageView(getContext());
                bookedview.setImageResource(R.mipmap.ic_launcher);
                bookedview.setBorderColor(Color.WHITE);
                bookedview.setCornerRadius(radius);
                bookedview.setTag(TAG_KEY_TYPE, BOOKED_TYPE_MAIN);
                bookedview.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mOnBookedListener != null){
                            mOnBookedListener.clickBookedView(v, String.valueOf(v.getTag(TAG_KEY_TYPE)));
                        }
                    }
                });
                ll_booked.addView(bookedview, lp);
            }
            for (int i = 0 ; i< num_soup ;i++){
                RoundedImageView bookedview = new RoundedImageView(getContext());
                bookedview.setImageResource(R.mipmap.ic_launcher);
                bookedview.setBorderColor(Color.WHITE);
                bookedview.setCornerRadius(radius);
                bookedview.setTag(TAG_KEY_TYPE, BOOKED_TYPE_SOUP);
                bookedview.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mOnBookedListener != null){
                            mOnBookedListener.clickBookedView(v, String.valueOf(v.getTag(TAG_KEY_TYPE)));
                        }
                    }
                });
                ll_booked.addView(bookedview, lp);
            }

            invalidate();
        }
    }

	public void initView(){
        LayoutInflater.from(getContext()).inflate( R.layout.view_booked, this, true);
        riv_post = (RoundedImageView) findViewById(R.id.riv_post);
        ll_booked = (LinearLayout) findViewById(R.id.ll_booked);

        riv_post.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnBookedListener != null){
                    mOnBookedListener.clickPost(v);
                }
            }
        });
    }

}
