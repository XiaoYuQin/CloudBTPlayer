package net.litdev.cloudbtplayer.inter;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.litdev.cloudbtplayer.bean.BeanBTSearch;
import net.litdev.cloudbtplayer.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by litde on 2016/5/27.
 */
public abstract class VolleyBTSearchResponesListener implements Response.Listener<String>,Response.ErrorListener {

    @Override
    public void onResponse(String s) {
        Log.d("Volley","请求返回："+s);
        Document doc = Jsoup.parse(s,Constants.URL_HOST);
        Elements nodes = doc.getElementsByClass("search-ret-item");
        List<BeanBTSearch> bt_list = new ArrayList<>();
        for(Element item : nodes){
            BeanBTSearch bt = new BeanBTSearch();
            String title = item.getElementsByClass("item-title").first().select("a[title]").attr("title");
            String torrent = item.getElementsByClass("magnet").first().attr("href");
            String hash = "";
            String size = item.getElementsByClass("item-meta-info-value").get(0).text();
            String date = item.getElementsByClass("item-meta-info-value").get(2).text().split(" ")[0];
            String hot = item.getElementsByClass("item-meta-info-value").get(3).text();

            if(!TextUtils.isEmpty(torrent)){
                Matcher m = Pattern.compile(Constants.REGEX_HASH).matcher(torrent);
                while (m.find()){
                    hash = m.group().replace("btih:","").replace("&","");
                }
            }
            bt.setTitle(title);
            bt.setDate(date);
            bt.setHash(hash);
            bt.setHot(hot);
            bt.setSize(size);
            bt_list.add(bt);
        }
        onSuccess(bt_list);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.d("Volley","请求错误："+volleyError.getMessage());
    }

    public abstract void onSuccess(List<BeanBTSearch> bt_list);

}
