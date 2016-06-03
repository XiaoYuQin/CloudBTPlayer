package net.litdev.cloudbtplayer.fragment;

import android.view.LayoutInflater;
import android.view.View;

import net.litdev.cloudbtplayer.BaseFragment;
import net.litdev.cloudbtplayer.R;

/**
 * Created by litde on 2016/5/26.
 */
public class SettingFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_setting,null);
        return view;
    }


}
