package net.litdev.cloudbtplayer.inter;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.litdev.cloudbtplayer.bean.BeanBTSearch;
import net.litdev.cloudbtplayer.bean.BeanHashList;
import net.litdev.cloudbtplayer.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by litde on 2016/5/27.
 */
public abstract class VolleyHashListResponesListener implements Response.Listener<String>,Response.ErrorListener {

    @Override
    public void onResponse(String s) {
        Log.d("Volley","请求返回："+s);
        //%E7%96%AF%E7%8B%82%E5%8A%A8%E7%89%A9%E5%9F%8E.Zootopia.2016.HD1080P.X264.AAC.English%26Mandarin.CHS.Mp4Ba.mp4|6
        List<BeanHashList> response_list = new ArrayList<>();
        try {
            String [] list = s.split("</br>");
            for (String item:list){
                if(!TextUtils.isEmpty(item)){
                    String [] str_bean = item.split("\\|");
                    BeanHashList bean =new BeanHashList();
                    bean.setFile_index(str_bean[1]);
                    bean.setFile_name(URLDecoder.decode(str_bean[0],"utf-8"));
                    response_list.add(bean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        onSuccess(response_list);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.d("Volley","请求错误："+volleyError.getMessage());
    }

    public abstract void onSuccess(List<BeanHashList> list);

}
