package net.litdev.cloudbtplayer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.handmark.pulltorefresh.library.internal.Utils;

import net.litdev.cloudbtplayer.BaseActivity;
import net.litdev.cloudbtplayer.R;
import net.litdev.cloudbtplayer.adapter.AdapterHashList;
import net.litdev.cloudbtplayer.api.APIHashFileIndex;
import net.litdev.cloudbtplayer.api.APIHashList;
import net.litdev.cloudbtplayer.bean.BeanBTSearch;
import net.litdev.cloudbtplayer.bean.BeanHashList;
import net.litdev.cloudbtplayer.bean.BeanHashPlayer;
import net.litdev.cloudbtplayer.inter.VolleyHashFileResponesListener;
import net.litdev.cloudbtplayer.inter.VolleyHashListResponesListener;
import net.litdev.cloudbtplayer.utils.UtilsToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by litde on 2016/5/30.
 */
public class PopupPlayerList extends BaseActivity {

    private List<BeanHashList> datas = new ArrayList<>();
    private AdapterHashList adapter;
    private ListView lv_player;
    private ProgressBar pb_loading;
    /**
     * 搜索的数据对象
     */
    private BeanBTSearch beanBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_player_list);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
        p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的0.8
        p.width = (int) (d.getWidth()); // 宽度设置为屏幕的0.8
        p.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(p);

        beanBT = (BeanBTSearch) getIntent().getSerializableExtra("bean_bt");
        if (beanBT == null) {
            UtilsToast.show(this, "参数错误");
            finish();
        }
        initView();

        initData();
    }

    private void initView() {
        lv_player = (ListView) findViewById(R.id.lv_player);
        pb_loading = (ProgressBar) findViewById(R.id.pb_loading);
        adapter = new AdapterHashList(this,datas);
        lv_player.setAdapter(adapter);
        lv_player.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BeanHashList bean = datas.get(position);
                Dialog dialog = new Dialog(PopupPlayerList.this,R.style.FullDialog);
                dialog.setContentView(new ProgressBar(PopupPlayerList.this));
                dialog.show();
                player(bean,dialog);
            }
        });
    }

    private void initData() {
        pb_loading.setVisibility(View.VISIBLE);
        lv_player.setVisibility(View.GONE);

        Request request = APIHashList.send(beanBT.getHash(), new VolleyHashListResponesListener() {
            @Override
            public void onSuccess(List<BeanHashList> list) {
                pb_loading.setVisibility(View.GONE);
                lv_player.setVisibility(View.VISIBLE);
                if(list.size()>0){
                    datas.clear();
                    for(BeanHashList item:list){
                        datas.add(item);
                    }
                    adapter.notifyDataSetChanged();
                }else{
                    UtilsToast.show(PopupPlayerList.this,"解析失败");
                    finish();
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                super.onErrorResponse(volleyError);
                pb_loading.setVisibility(View.GONE);
                lv_player.setVisibility(View.VISIBLE);
                UtilsToast.show(PopupPlayerList.this,volleyError.getMessage());
                finish();
            }
        });
        mQueue.add(request);
    }

    /**
     * 播放
     * @param bean
     * @param dialog
     */
    private void player(BeanHashList bean, final Dialog dialog){
        Request request = APIHashFileIndex.send(beanBT.getHash(), bean.getFile_index(), new VolleyHashFileResponesListener() {
            @Override
            public void onSuccess(BeanHashPlayer bean) {
                dialog.dismiss();
                //UtilsToast.show(PopupPlayerList.this,"解析地址："+bean.getPlayer_url()+",cookie："+bean.getCookie());
                Map<String,String> head = new HashMap<>();
                head.put("Cookie",bean.getCookie());//"FTN5K="+
                //JCFullScreenActivity.toActivity(PopupPlayerList.this,bean.getPlayer_url(), JCVideoPlayerStandard.class,head);
                Intent intent =new Intent(PopupPlayerList.this,PlayerActivity.class);
                intent.putExtra("path",bean.getPlayer_url());
                intent.putExtra("cookie",bean.getCookie());
                startActivity(intent);
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                super.onErrorResponse(volleyError);
                UtilsToast.show(PopupPlayerList.this,"error："+volleyError.getMessage());
                dialog.dismiss();
            }
        });
        mQueue.add(request);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_close,0);
    }
}
