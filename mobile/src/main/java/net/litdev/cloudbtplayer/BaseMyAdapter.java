package net.litdev.cloudbtplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by litde on 2016/5/27.
 */
public class BaseMyAdapter<E> extends BaseAdapter {
    protected Context context;
    protected List<E> datas;

    public BaseMyAdapter(Context context,List<E> dataList){
        this.context = context;
        this.datas = dataList;
    }

    @Override
    public int getCount() {
        return datas== null ? 0 : datas.size();
    }

    @Override
    public E getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //重写
        return null;
    }
}
