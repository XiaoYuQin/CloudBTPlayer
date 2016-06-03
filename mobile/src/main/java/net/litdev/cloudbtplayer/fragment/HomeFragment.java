package net.litdev.cloudbtplayer.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;

import net.litdev.cloudbtplayer.BaseFragment;
import net.litdev.cloudbtplayer.R;
import net.litdev.cloudbtplayer.activity.SearchActivity;
import net.litdev.cloudbtplayer.adapter.AdapterHome;
import net.litdev.cloudbtplayer.api.APIDBMovie;
import net.litdev.cloudbtplayer.bean.BeanDBMoveItem;
import net.litdev.cloudbtplayer.bean.BeanDBMovie;
import net.litdev.cloudbtplayer.bean.BeanHomeHot;
import net.litdev.cloudbtplayer.inter.VolleyDBMoveResponseListener;
import net.litdev.cloudbtplayer.sqlite.DALSearchHostory;
import net.litdev.cloudbtplayer.utils.ForbiddenEditText;
import net.litdev.cloudbtplayer.utils.UtilsToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litde on 2016/5/26.
 */
public class HomeFragment extends BaseFragment {

    private EditText et_search;
    private GridView gv_home;
    private AdapterHome adapterHome;
    private List<BeanDBMoveItem> datas;
    private TextView tv_search_cancel;
    private LinearLayout ll_search_box;
    private ProgressBar pb_loading;

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        gv_home = (GridView) view.findViewById(R.id.gv_home);
        et_search = (EditText) view.findViewById(R.id.et_search_key);
        tv_search_cancel = (TextView) view.findViewById(R.id.tv_search_cancel);
        ll_search_box = (LinearLayout) view.findViewById(R.id.ll_search_box);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
        et_search.setCursorVisible(false);
        et_search.setFocusableInTouchMode(false);
        et_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    startActivity(new Intent(mActivity, SearchActivity.class));
                }
                return false;
            }
        });
//        gv_home.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                final int y = (int) event.getY();
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//
//                        break;
//                }
//                return false;
//            }
//        });
        return view;
    }


    @Override
    protected void initData() {
        datas = new ArrayList<>();
        adapterHome = new AdapterHome(mActivity, datas);
        gv_home.setAdapter(adapterHome);
        loadData();
    }

    private void loadData() {
        Request request = APIDBMovie.send(new VolleyDBMoveResponseListener() {
            @Override
            public void onSuccess(BeanDBMovie beanDBMovie) {
                for (BeanDBMoveItem item : beanDBMovie.getSubjects()) {
                    datas.add(item);
                }
                pb_loading.setVisibility(View.GONE);
                adapterHome.notifyDataSetChanged();
            }
        });
        mQueue.add(request);
    }


}
