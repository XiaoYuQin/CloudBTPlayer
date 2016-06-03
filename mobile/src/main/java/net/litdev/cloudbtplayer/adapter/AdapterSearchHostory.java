package net.litdev.cloudbtplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.litdev.cloudbtplayer.BaseMyAdapter;
import net.litdev.cloudbtplayer.R;

import java.util.List;

/**
 * Created by litde on 2016/5/27.
 */
public class AdapterSearchHostory<String> extends BaseMyAdapter {

    public AdapterSearchHostory(Context context, List dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_search_hostory,null);
            holder.tv_search_hostory= (TextView) convertView.findViewById(R.id.tv_search_hostory);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        java.lang.String str = (java.lang.String) getItem(position);
        holder.tv_search_hostory.setText(str);
        return  convertView;
    }

    public class ViewHolder {
        public TextView tv_search_hostory;
    }

}
