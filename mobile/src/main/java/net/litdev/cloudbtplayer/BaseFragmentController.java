package net.litdev.cloudbtplayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * Fragment控制器
 */
public class BaseFragmentController {

    /**
     * Fragment 容器ID
     */
    private int containerId;

    private static BaseFragmentController controller;

    private FragmentManager fm;

    private ArrayList<Fragment> fragments;

    /**
     * 获取控制器实例，单例
     * @param activity
     * @param containerId
     * @param fragmentList
     * @return
     */
    public static BaseFragmentController getInstance(FragmentActivity activity, int containerId, ArrayList<Fragment> fragmentList){
        if(controller == null){
            controller= new BaseFragmentController(activity,containerId, fragmentList);
        }
        return  controller;
    }


    /**
     * 私有构造函数
     * @param activity
     * @param containerId
     * @param fragmentList
     */
    private BaseFragmentController(FragmentActivity activity, int containerId, ArrayList<Fragment> fragmentList){
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        initFragment(fragmentList);
    }

    /**
     * 添加Fragment
     * @param fragmentList
     */
    private void initFragment(ArrayList<Fragment> fragmentList){
        fragments = fragmentList;
        FragmentTransaction tran = fm.beginTransaction();
        for (Fragment fragment:fragmentList){
            tran.add(containerId,fragment);
        }
        tran.commit();
    }


    /**
     * 获取一个Fragment
     * @param position Fragmetn的位置
     * @return
     */
    public Fragment getFragment(int position){
        return  fragments.get(position);
    }

    /**
     * 显示指定的Fragment
     * @param position 指定id
     */
    public void showFragment(int position){
        hideFragment();
        Fragment fragment = fragments.get(position);
        FragmentTransaction tran = fm.beginTransaction();
        tran.show(fragment);
        tran.commit();
    }

    /**
     * 隐藏所有的Fragment
     */
    public void hideFragment(){
        FragmentTransaction tran = fm.beginTransaction();
        for (Fragment fragment:fragments){
            if(fragment != null){
                tran.hide(fragment);
            }
        }
        tran.commit();
    }

    /**
     * 在onDestroy方法中务必调用
     */
    public void clearFragment(){
        controller = null;
    }

}
