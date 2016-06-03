package net.litdev.cloudbtplayer.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import net.litdev.cloudbtplayer.R;
import net.litdev.cloudbtplayer.activity.SearchActivity;
import net.litdev.cloudbtplayer.bean.BeanDBMoveItem;
import net.litdev.cloudbtplayer.bean.BeanHomeHot;
import net.litdev.cloudbtplayer.utils.UtilsToast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by litde on 2016/5/26.
 */
public class AdapterHome extends BaseAdapter {

    private Context context;
    private List<BeanDBMoveItem> datas;
    private ImageLoader imageLoader;

    public AdapterHome(Context context, List<BeanDBMoveItem> datas) {
        this.context = context;
        this.datas = datas;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public BeanDBMoveItem getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_hot, null);
            holder = new ViewHolder();
            holder.iv_home_hot = (ImageView) convertView.findViewById(R.id.iv_home_hot);
            holder.tv_home_title = (TextView) convertView.findViewById(R.id.tv_home_title);
            holder.tv_home_score = (TextView) convertView.findViewById(R.id.tv_home_score);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final BeanDBMoveItem bean = getItem(position);
        GridView gv = (GridView) parent;
        int horizontalSpacing = gv.getHorizontalSpacing();
        int numColumns = gv.getNumColumns();
        int itemWidth = (gv.getWidth() - (numColumns - 1) * horizontalSpacing - gv.getPaddingLeft()
                - gv.getPaddingRight()) / numColumns;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(itemWidth,itemWidth + (itemWidth/3));
        holder.iv_home_hot.setLayoutParams(params);

        imageLoader.displayImage(bean.getCover(),holder.iv_home_hot);
        holder.tv_home_title.setText(bean.getTitle());
        holder.tv_home_score.setText(String.valueOf(bean.getRate()));

        holder.iv_home_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(context, SearchActivity.class);
                intent.putExtra("search_word",bean.getTitle());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        public ImageView iv_home_hot;
        public TextView tv_home_title;
        public TextView tv_home_score;
    }

}
