package net.litdev.cloudbtplayer.api;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import net.litdev.cloudbtplayer.inter.VolleyHashListResponesListener;
import net.litdev.cloudbtplayer.utils.Constants;

/**
 * 解析hash文件列表
 */
public class APIHashList {

    public static Request send(String hash, VolleyHashListResponesListener listener){
        String url;
        try {
            url = String.format(Constants.PARSE_LIST_1,hash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        StringRequest stringRequest = new StringRequest(url,listener,listener);
        return stringRequest;
    }
}
