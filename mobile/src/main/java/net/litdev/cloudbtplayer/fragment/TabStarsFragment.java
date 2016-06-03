package net.litdev.cloudbtplayer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.litdev.cloudbtplayer.BaseFragment;
import net.litdev.cloudbtplayer.R;
import net.litdev.cloudbtplayer.adapter.AdapterStarsList;
import net.litdev.cloudbtplayer.bean.BeanBTSearch;
import net.litdev.cloudbtplayer.sqlite.DALBTStars;
import net.litdev.cloudbtplayer.utils.UtilsToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litde on 2016/6/2.
 */
public class TabStarsFragment extends BaseFragment {

    private ListView lv_stars;
    private TextView tv_no_stars;
    private List<BeanBTSearch> datas =new ArrayList<>();
    private AdapterStarsList adapter;
    private SwipeRefreshLayout swipe_refresh;

    @Override
    protected View initView(LayoutInflater inflater) {
        mActivity = getActivity();
        View view = View.inflate(getActivity(), R.layout.fragment_tab_stars,null);
        lv_stars = (ListView) view.findViewById(R.id.lv_stars);
        tv_no_stars = (TextView) view.findViewById(R.id.tv_no_stars);
        adapter = new AdapterStarsList(mActivity,datas);
        lv_stars.setAdapter(adapter);
        lv_stars.setOnItemLongClickListener(new StarsListener());

        //下拉刷新
        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                swipe_refresh.setRefreshing(false);
                UtilsToast.show(mActivity,"更新完毕");
            }
        });
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        //swipe_refresh.setProgressViewOffset(true, 50, 200);
        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        //swipe_refresh.setSize(SwipeRefreshLayout.LARGE);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        swipe_refresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        // 通过 setEnabled(false) 禁用下拉刷新
        //mySwipeRefreshLayout.setEnabled(false);

        // 设定下拉圆圈的背景
        swipe_refresh.setProgressBackgroundColorSchemeResource(R.color.GreenColor);
        //通过 setRefreshing(false) 和 setRefreshing(true) 来手动调用刷新的动画。

        return view;
    }

    @Override
    protected void initData() {
        DALBTStars dal = new DALBTStars(mActivity);
        datas.clear();
        for (BeanBTSearch bean: dal.getList()){
            if(!datas.contains(bean))
                datas.add(bean);
        }
        if(datas.size() == 0){
            show_no_data();
            return;
        }else{
            show_zc();
            adapter.notifyDataSetChanged();
        }
    }

    private class StarsListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            showPopupMenu(view,position);
            return true;
        }



    }

    /**
     * 显示PopupMenu
     * @param position
     */
    private void showPopupMenu(View v,final int position) {
        PopupMenu popupMenu = new PopupMenu(mActivity,v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_stars_del,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_stars_del:
                        DALBTStars dal = new DALBTStars(mActivity);
                        dal.remove(datas.get(position).getHash());
                        initData();
                        UtilsToast.show(mActivity,"删除成功");
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }


    /**
     * 没有数据
     */
    private void show_no_data(){
        lv_stars.setVisibility(View.GONE);
        tv_no_stars.setVisibility(View.VISIBLE);
    }

    /**
     * 显示正常
     */
    private void show_zc(){
        lv_stars.setVisibility(View.VISIBLE);
        tv_no_stars.setVisibility(View.GONE);
    }

}
