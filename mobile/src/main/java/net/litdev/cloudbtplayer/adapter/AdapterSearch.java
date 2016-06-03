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
 * Created by litde on 2016/5/27.
 */
public class AdapterSearch extends BaseMyAdapter {

    public AdapterSearch(Context context, List dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder= new ViewHolder();
            convertView = View.inflate(context, R.layout.item_search,null);
            holder.tv_search_date = (TextView) convertView.findViewById(R.id.tv_search_date);
            holder.tv_search_hot = (TextView) convertView.findViewById(R.id.tv_search_hot);
            holder.tv_search_size = (TextView) convertView.findViewById(R.id.tv_search_size);
            holder.tv_search_title = (TextView) convertView.findViewById(R.id.tv_search_title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        BeanBTSearch bean = (BeanBTSearch) getItem(position);
        holder.tv_search_date.setText(bean.getDate());
        holder.tv_search_title.setText(bean.getTitle());
        holder.tv_search_size.setText(bean.getSize());
        holder.tv_search_hot.setText("热度:"+bean.getHot());

        return  convertView;
    }

    class ViewHolder{
        public TextView tv_search_title;
        public TextView tv_search_date;
        public TextView tv_search_size;
        public TextView tv_search_hot;
    }

}
