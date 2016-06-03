package net.litdev.cloudbtplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.litdev.cloudbtplayer.BaseMyAdapter;
import net.litdev.cloudbtplayer.R;
import net.litdev.cloudbtplayer.bean.BeanHashList;

import java.util.List;

/**
 * Created by litde on 2016/6/1.
 */
public class AdapterHashList extends BaseMyAdapter {

    public AdapterHashList(Context context, List dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeanHashList item = (BeanHashList) getItem(position);

        ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_hash_list,null);
            viewHolder.tv_hash_file_name = (TextView) convertView.findViewById(R.id.tv_hash_file_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_hash_file_name.setText(item.getFile_name());
        return convertView;
    }

    private class ViewHolder{
        public TextView tv_hash_file_name;
    }

}
