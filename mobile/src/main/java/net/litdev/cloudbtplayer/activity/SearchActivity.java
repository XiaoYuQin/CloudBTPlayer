package net.litdev.cloudbtplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import net.litdev.cloudbtplayer.BaseActivity;
import net.litdev.cloudbtplayer.R;
import net.litdev.cloudbtplayer.adapter.AdapterSearch;
import net.litdev.cloudbtplayer.adapter.AdapterSearchHostory;
import net.litdev.cloudbtplayer.api.APISearchList;
import net.litdev.cloudbtplayer.bean.BeanBTSearch;
import net.litdev.cloudbtplayer.inter.VolleyBTSearchResponesListener;
import net.litdev.cloudbtplayer.sqlite.DALBTStars;
import net.litdev.cloudbtplayer.sqlite.DALSearchHostory;
import net.litdev.cloudbtplayer.utils.UtilsToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litde on 2016/5/27.
 */
public class SearchActivity extends BaseActivity {

    private TextView tv_search_cancel;
    private EditText et_search;
    private ListView lv_hostory;
    private List<String> datas_search_hostory = new ArrayList<>();
    //网络搜索数据
    private List<BeanBTSearch> datas_search = new ArrayList<>();
    private AdapterSearchHostory adapterSearchHostory;
    private AdapterSearch adapterSearch;
    private View head_search_hostory;
    private View footView;
    private PullToRefreshListView lv_search;
    private ProgressBar pb_loading;
    private TextView tv_clear_hostory;

    private final int curSize = 10;
    //当前第几页
    private int curPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }


    private void initView() {
        et_search = (EditText) findViewById(R.id.et_search_key);
        tv_search_cancel = (TextView) findViewById(R.id.tv_search_cancel);
        tv_search_cancel.setVisibility(View.VISIBLE);
        lv_hostory = (ListView) findViewById(R.id.lv_hostory);
        lv_search = (PullToRefreshListView) findViewById(R.id.lv_search);
        footView = View.inflate(this, R.layout.footview_loading, null);
        //搜索
        et_search.setOnEditorActionListener(new EditorActionListener());
        //监听输入
        et_search.addTextChangedListener(new EditChangedListener());
        tv_search_cancel.setOnClickListener(new CacelSearch());

        pb_loading = (ProgressBar) findViewById(R.id.pb_loading);

        //历史纪录列表
        head_search_hostory = View.inflate(this, R.layout.head_search_hostory, null);
        tv_clear_hostory = (TextView) head_search_hostory.findViewById(R.id.tv_clear_hostory);
        lv_hostory.addHeaderView(head_search_hostory);
        adapterSearchHostory = new AdapterSearchHostory(this, datas_search_hostory);
        lv_hostory.setAdapter(adapterSearchHostory);
        lv_hostory.setOnItemClickListener(new HostoryListener());
        tv_clear_hostory.setOnClickListener(new ClearHostoryListener());


        //网络搜索列表
        adapterSearch = new AdapterSearch(this, datas_search);
        lv_search.setAdapter(adapterSearch);

        ILoadingLayout startLables = lv_search.getLoadingLayoutProxy(true, false);
        startLables.setPullLabel("下拉刷新...");//刚下拉时,显示的提示
        startLables.setRefreshingLabel("正在载入...");//刷新时
        startLables.setReleaseLabel("放开刷新...");//// 下拉到一定距离时，显示的提示

        //下拉刷新
        lv_search.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadSearchData(1);
            }
        });
        //最后一项显示时自动加载下一页
        lv_search.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                loadSearchData(curPage + 1);
            }
        });

        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //UtilsToast.show(SearchActivity.this,"点击的位置："+datas_search.get(position-1).getSize());
                et_search.setFocusableInTouchMode(false);
                Intent intent = new Intent(SearchActivity.this, PopupPlayerList.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean_bt", datas_search.get(position - 1));
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_open,0);
            }
        });
        //长按收藏
        lv_search.getRefreshableView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    showPopupMenu(view,position-1);
                    return true;
                }else{
                    return  false;
                }
            }
        });

        et_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    et_search.setFocusableInTouchMode(true);
                }
                return false;
            }
        });

        String search_word = getIntent().getStringExtra("search_word");
        if (!TextUtils.isEmpty(search_word)) {
            et_search.setText(search_word);
            et_search.setSelection(et_search.getText().length());
            initSearch();
            et_search.setFocusableInTouchMode(false);
        } else {
            initSearchHostory();
        }
    }



    /**
     * 软键盘回车触发
     */
    private class EditorActionListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                initSearch();
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return true;
            } else {
                UtilsToast.show(SearchActivity.this, "没有触发搜索");
                return false;
            }

        }
    }

    /**
     * 清空搜索历史
     */
    private class ClearHostoryListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            UtilsToast.show(SearchActivity.this,"点击了清空");
            DALSearchHostory dal =new DALSearchHostory(SearchActivity.this);
            dal.removeALL();
            datas_search_hostory.clear();
            adapterSearchHostory.notifyDataSetChanged();
        }
    }

    /**
     * 搜索历史点击
     */
    private class HostoryListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position != 0){
                et_search.setText(datas_search_hostory.get(position - 1));
                et_search.setSelection(et_search.getText().length());
                initSearch();
            }
        }
    }

    /**
     * 取消搜索
     */
    private class CacelSearch implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //startActivity(new Intent(SearchActivity.this,MainActivity.class));
            finish();
        }
    }

    /**
     * 文本框输入监听器
     */
    private class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            initSearchHostory();
            loadSearchHostory(s.toString());
        }
    }

    /**
     * 显示收藏Popup窗口
     */
    private void showPopupMenu(View v,final int position){
        PopupMenu popup = new PopupMenu(SearchActivity.this,v);
        popup.getMenuInflater().inflate(R.menu.popup_stars,popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_stars:
                        DALBTStars dal = new DALBTStars(SearchActivity.this);
                        int result = dal.add(datas_search.get(position));
                        if(result == DALBTStars.STARS_STATE_EXISTS){
                            UtilsToast.show(SearchActivity.this,"已经收藏过了");
                        }else{
                            UtilsToast.show(SearchActivity.this,"收藏成功");
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    /**
     * 加载搜索
     */
    private void initSearch() {
        lv_search.setVisibility(View.VISIBLE);
        lv_hostory.setVisibility(View.GONE);
        loadSearchData(1);
    }

    /**
     * 加载搜索的数据
     */
    private void loadSearchData(final int page) {
        String search = et_search.getText().toString().trim();
        if (TextUtils.isEmpty(search)) {
            UtilsToast.show(SearchActivity.this, "请输入搜索关键字");
            return;
        }

        if (page == 1) {
            lv_search.setRefreshing();
        }

        pb_loading.setVisibility(datas_search.size() > 0 ? View.GONE : View.VISIBLE);

        DALSearchHostory dal = new DALSearchHostory(this);
        dal.add(search);

        VolleyBTSearchResponesListener listener = new VolleyBTSearchResponesListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                super.onErrorResponse(volleyError);
                pb_loading.setVisibility(View.GONE);
                UtilsToast.show(SearchActivity.this, "搜索失败");
                lv_search.onRefreshComplete();
            }

            @Override
            public void onSuccess(List<BeanBTSearch> bt_list) {

                curPage = page;
                if (curPage == 1) {
                    pb_loading.setVisibility(View.GONE);
                    datas_search.clear();
                }

                for (BeanBTSearch item : bt_list) {
                    if (!datas_search.contains(item)) {
                        datas_search.add(item);
                    }
                }

                adapterSearch.notifyDataSetChanged();

                if (bt_list.size() != 0) {
                    addFootView(lv_search, footView);
                } else {
                    UtilsToast.show(SearchActivity.this, "没有更多数据了");
                    removeFootView(lv_search, footView);
                }
                lv_search.onRefreshComplete();
            }
        };

        mQueue.add(APISearchList.send(search, page, listener));
    }

    /**
     * 加载搜索历史纪录
     */
    private void initSearchHostory() {
        lv_hostory.setVisibility(View.VISIBLE);
        lv_search.setVisibility(View.GONE);

        loadSearchHostory("");
    }

    /**
     * 加载历史纪录数据
     */
    private void loadSearchHostory(String search_key) {
        DALSearchHostory dal = new DALSearchHostory(SearchActivity.this);
        datas_search_hostory.clear();
        for (String str : dal.getList(search_key)) {
            if (!datas_search_hostory.contains(str)) {
                datas_search_hostory.add(str);
            }
        }
        adapterSearchHostory.notifyDataSetChanged();
    }


    private void addFootView(PullToRefreshListView plv, View footView) {
        ListView lv = plv.getRefreshableView();
        if (lv.getFooterViewsCount() == 1) {
            lv.addFooterView(footView);
        }
    }

    private void removeFootView(PullToRefreshListView plv, View footView) {
        ListView lv = plv.getRefreshableView();
        if (lv.getFooterViewsCount() > 1) {
            lv.removeFooterView(footView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
