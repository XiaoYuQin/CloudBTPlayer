package net.litdev.cloudbtplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.litdev.cloudbtplayer.fragment.TabHostoryFragment;
import net.litdev.cloudbtplayer.fragment.TabStarsFragment;

/**
 * Created by litde on 2016/6/2.
 */
public class AdapterStarsViewPager extends FragmentPagerAdapter {
    private String[] _titles;
    private TabStarsFragment tabStarsFragment;
    private TabHostoryFragment tabHostoryFragment;


    public AdapterStarsViewPager(FragmentManager fm, String[] titles) {
        super(fm);
        _titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return _titles[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if(tabStarsFragment == null){
                    tabStarsFragment = new TabStarsFragment();
                }
                return tabStarsFragment;
            case 1:
                if(tabHostoryFragment == null){
                    tabHostoryFragment = new TabHostoryFragment();
                }
                return tabHostoryFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return _titles.length;
    }
}
