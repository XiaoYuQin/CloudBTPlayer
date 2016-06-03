package net.litdev.cloudbtplayer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Fragment 基类
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected RequestQueue mQueue;
    protected TextView tv_title_bar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater);
        this.mActivity = getActivity();
        tv_title_bar = (TextView) view.findViewById(R.id.tv_title_bar);
        mQueue = Volley.newRequestQueue(mActivity);
        initData();
        return view;
    }

    /**
     * 加载View
     *
     * @param inflater
     * @return
     */
    protected abstract View initView(LayoutInflater inflater);

    /**
     * 加载数据,需要子类就重写
     */
    protected void initData() {

    }

}