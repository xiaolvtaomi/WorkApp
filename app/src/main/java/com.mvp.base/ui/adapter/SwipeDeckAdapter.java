package com.mvp.base.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mvp.base.R;
import com.mvp.base.component.ImageLoader;
import com.mvp.base.model.bean.DoodleBean;
import com.mvp.base.utils.JumpUtil;
import com.mvp.base.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: SwipeDeckAdapter
 * Creator: yxc
 * date: 2016/9/30 11:10
 */
public class SwipeDeckAdapter extends BaseAdapter {

    private List<DoodleBean> data;
    private Context context;
    private LayoutInflater inflater;

    public SwipeDeckAdapter(List<DoodleBean> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void refresh(List<DoodleBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.load(context, data.get(position).getImg_small(), holder.offerImage);
        String intro = "\t\t\t" + StringUtils.removeOtherCode(TextUtils
                .isEmpty(data.get(position).getInfo_long()) ? data.get
                (position).getInfo_long_en() : data.get(position)
                .getInfo_long());
        holder.tvIntroduction.setText(intro);
        holder.tv_title.setText(data.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.offer_image)
        RoundedImageView offerImage;
        @BindView(R.id.tv_introduction)
        TextView tvIntroduction;
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.tv_title)
        TextView tv_title;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



}