package net.litdev.cloudbtplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.litdev.cloudbtplayer.BaseMyAdapter;
import net.litdev.cloudbtplayer.R;
import net.litdev.cloudbtplayer.bean.BeanBTSearch;

import java.util.List;

/**
 * Created by litde on 2016/6/3.
 */
public class AdapterStarsList extends BaseMyAdapter {

    public AdapterStarsList(Context context, List dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_stars_list,null);
            viewHolder.tv_stars_title = (TextView) convertView.findViewById(R.id.tv_stars_title);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BeanBTSearch bean = (BeanBTSearch) getItem(position);
        viewHolder.tv_stars_title.setText(bean.getTitle());
        return convertView;
    }

    private class ViewHolder{
        public TextView tv_stars_title;
    }

}
