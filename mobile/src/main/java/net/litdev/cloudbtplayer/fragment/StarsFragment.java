package net.litdev.cloudbtplayer.fragment;

import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import net.litdev.cloudbtplayer.BaseFragment;
import net.litdev.cloudbtplayer.R;
import net.litdev.cloudbtplayer.adapter.AdapterStarsViewPager;
import net.litdev.cloudbtplayer.widget.PagerSlidingTabStrip;

/**
 * Created by litde on 2016/5/26.
 */
public class StarsFragment extends BaseFragment {

    private PagerSlidingTabStrip tabs;
    private ViewPager viewpager;
    String[] titles = { "收藏","播放记录" };

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_stars,null);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        viewpager.setAdapter(new AdapterStarsViewPager(getChildFragmentManager(),titles));
        tabs.setViewPager(viewpager);
        return view;
    }
}
