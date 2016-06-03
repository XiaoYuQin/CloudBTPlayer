package net.litdev.cloudbtplayer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.litdev.cloudbtplayer.BaseFragment;
import net.litdev.cloudbtplayer.R;

/**
 * Created by litde on 2016/6/2.
 */
public class TabHostoryFragment extends BaseFragment {

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = View.inflate(getActivity(), R.layout.fragment_tab_hostory,null);
        return view;
    }
}
