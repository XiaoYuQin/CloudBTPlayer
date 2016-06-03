package net.litdev.cloudbtplayer.api;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import net.litdev.cloudbtplayer.inter.VolleyBTSearchResponesListener;
import net.litdev.cloudbtplayer.utils.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 资源搜索
 */
public class APISearchList {

    public static Request send(String search_word, int page, VolleyBTSearchResponesListener listener){
        String url;
        try {
            url = String.format(Constants.URL_WORD_SEARCH, URLEncoder.encode(search_word,"UTF-8"),page);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        page = page <= 0 ? 1 : page;

        StringRequest stringRequest = new StringRequest(url,listener,listener);
        return stringRequest;
    }
}
